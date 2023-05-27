package container

import baseUrl
import cafe.adriel.voyager.core.model.coroutineScope
import di.Inject
import extentions.launchSafe
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import local.persistent.PersistentStorage
import model.ClientItem
import repository.ClientRepository
import repository.UserRepository
import screen_model.BaseScreenModel

class DashboardScreenModel(
    private val userRepository: UserRepository = Inject.instance(),
    private val clientRepository: ClientRepository = Inject.instance(),
    private val persistentStorage: PersistentStorage = Inject.instance()
) : BaseScreenModel<DashboardContract.Event, DashboardContract.State, DashboardContract.Effect>(
    initialState = DashboardContract.State()
) {
    override fun handleEvent(event: DashboardContract.Event) {
        when(event) {
            is DashboardContract.Event.OnSignOutClicked -> signOut()
            else -> {}
        }
    }

    init {
        subscribeToCookies()
        getUser()
        getClients()
    }

    private fun getUser() = coroutineScope.launchSafe(onError = ::onError, onComplete = { setState { copy() } }) {
        val user = userRepository.getUser()
        setState { copy(nickName = user.nickname) }
    }

    private fun getClients() = coroutineScope.launchSafe(::onError) {
        val clients = clientRepository.getClients()
        if (clients.isNotEmpty()) {
            val firstClient = clients.first()
            val clientItems = clients.map { ClientItem(it) }
            setState { copy(currentClient = firstClient, clients = clientItems) }
        }
    }

    private fun signOut() = coroutineScope.launchSafe(::onError) {
        userRepository.signOut()
    }

    private fun subscribeToCookies() {
        coroutineScope.launch {
            persistentStorage.cookiesFlow.map { map ->
                map[baseUrl] ?: listOf()
            }.collect { list ->
                if (list.find { cookie -> cookie.name == "Access-Token" } == null) {
                    setEvent(DashboardContract.Event.OnNavigateToAuth)
                }
            }
        }
    }
}