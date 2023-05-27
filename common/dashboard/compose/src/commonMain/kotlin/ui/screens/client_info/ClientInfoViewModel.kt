//package ru.alterland.launcher.ui.screens.main.client_info
//
//import kotlinx.coroutines.async
//import ru.alterland.launcher.ui.base.MVIViewModel
//import ui.screens.client.ClientItem
//import ru.alterland.launcher.usecase.client.CheckClientUpdatesUseCase
//import ru.alterland.launcher.usecase.client.GetDownloadMetricsFlowUseCase
//import ru.alterland.launcher.usecase.client.LaunchClientUseCase
//import ru.alterland.launcher.usecase.client.UpdateClientUseCase
//import ru.alterland.launcher.util.launchSafe
//
//class ClientInfoViewModel(
//    private val updateClientUseCase: UpdateClientUseCase,
//    private val checkClientUpdatesUseCase: CheckClientUpdatesUseCase,
//    private val launchClientUseCase: LaunchClientUseCase,
//    private val getDownloadMetricsFlowUseCase: GetDownloadMetricsFlowUseCase,
//) : MVIViewModel<ClientInfoContract.Event, ClientInfoContract.State, ClientInfoContract.Effect>() {
//
//    private var client: ClientItem? = null
//
//    override fun createInitialState() = ClientInfoContract.State()
//
//    override fun handleEvent(event: ClientInfoContract.Event) {
//        when(event) {
//            ClientInfoContract.Event.OnChangeDownloadStatusClicked -> TODO()
//            ClientInfoContract.Event.OnPlayClientClicked -> TODO()
//        }
//    }
//
//    fun init(client: ClientItem) {
//        this.client = client
//    }
//
//    private fun checkClientUpdates(clientName: String) = viewModelScope.launchSafe(::onError) {
//        val updates = checkClientUpdatesUseCase.execute(CheckClientUpdatesUseCase.Request(clientName))
//    }
//
//    private fun updateClient() = viewModelScope.launchSafe(
//        onError = { onError(it) },
//        onComplete = {
//            setState { copy(filesScanProcess = false, clientUpdateMetricsProcess = null) }
//        }
//    ) {
//        val clientName = client?.clientName ?: return@launchSafe
//        setState { copy(filesScanProcess = true) }
//        val downloadMetrics = async {
//            val flow = getDownloadMetricsFlowUseCase.execute(GetDownloadMetricsFlowUseCase.Request())
//
//            flow.collect {
//                setState { copy(clientUpdateMetricsProcess = it) }
//            }
//        }
//        val downloadProcess = async {
//            updateClientUseCase.execute(UpdateClientUseCase.Request(clientName))
//            downloadMetrics.cancel()
//        }
//        downloadMetrics.await()
//        downloadProcess.await()
//    }
//
//    private fun launchClient() = viewModelScope.launchSafe(::onError) {
//        launchClientUseCase.execute(LaunchClientUseCase.Request())
//    }
//}