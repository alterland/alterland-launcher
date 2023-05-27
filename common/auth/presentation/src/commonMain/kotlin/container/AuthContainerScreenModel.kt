package container

import baseUrl
import cafe.adriel.voyager.core.model.coroutineScope
import di.Inject
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import local.persistent.PersistentStorage
import screen_model.BaseScreenModel

class AuthContainerScreenModel(
    persistentStorage: PersistentStorage = Inject.instance()
): BaseScreenModel<AuthContainerContract.Event, AuthContainerContract.State, AuthContainerContract.Effect>(
    initialState = AuthContainerContract.State()
) {
    override fun handleEvent(event: AuthContainerContract.Event) {}

    init {
        coroutineScope.launch {
            persistentStorage.cookiesFlow.map { map ->
                map[baseUrl] ?: listOf()
            }.collect { list ->
                if (list.find { cookie -> cookie.name == "Access-Token" } != null) {
                    setEvent(AuthContainerContract.Event.OnNavigateToDashboard)
                }
            }
        }
    }
}