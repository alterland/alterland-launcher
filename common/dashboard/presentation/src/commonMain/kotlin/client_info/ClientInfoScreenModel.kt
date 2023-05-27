package client_info

import cafe.adriel.voyager.core.model.coroutineScope
import extentions.launchSafe
import kotlinx.coroutines.async
import model.ClientItem
import repository.ClientRepository
import screen_model.BaseScreenModel

class ClientInfoScreenModel(
    private val clientRepository: ClientRepository
) : BaseScreenModel<ClientInfoContract.Event, ClientInfoContract.State, ClientInfoContract.Effect>(
    initialState = ClientInfoContract.State()
) {

    private var client: ClientItem? = null

    override fun handleEvent(event: ClientInfoContract.Event) {
        when(event) {
            is ClientInfoContract.Event.OnChangeDownloadStatusClicked -> TODO()
            is ClientInfoContract.Event.OnPlayClientClicked -> TODO()
        }
    }

    fun init(client: ClientItem) {
        this.client = client
    }

    private fun checkClientUpdates(clientName: String) = coroutineScope.launchSafe(::onError) {
        val updates = clientRepository.checkClientUpdates(clientName)
    }

    private fun updateClient() = coroutineScope.launchSafe(
        onError = { onError(it) },
        onComplete = {
            setState { copy(filesScanProcess = false, clientUpdateMetricsProcess = null) }
        }
    ) {
        val clientName = client?.clientName ?: return@launchSafe
        setState { copy(filesScanProcess = true) }
        val downloadMetrics = async {
            //val flow = clientRepository.downloadMetrics

//            flow.collect {
//                //setState { copy(clientUpdateMetricsProcess = it) }
//            }
        }
        val downloadProcess = async {
            clientRepository.updateClient(clientName)
            downloadMetrics.cancel()
        }
        downloadMetrics.await()
        downloadProcess.await()
    }

    private fun launchClient() = coroutineScope.launchSafe(::onError) {
        val clientName = client?.clientName ?: return@launchSafe
        clientRepository.launchClient(clientName)
    }
}