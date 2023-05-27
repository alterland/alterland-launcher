package navigation

import baseUrl
import cafe.adriel.voyager.core.model.coroutineScope
import di.Inject
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import local.persistent.PersistentStorage
import screen_model.BaseScreenModel

class RootScreenModel(
    persistentStorage: PersistentStorage = Inject.instance()
): BaseScreenModel<RootScreenContract.Event, RootScreenContract.State, RootScreenContract.Effect>(RootScreenContract.State) {

    private val _sharedFlow = MutableSharedFlow<RootScreenContract.Event>()
    val sharedFlow: SharedFlow<RootScreenContract.Event> = _sharedFlow

    init {
        coroutineScope.launch {
            persistentStorage.cookiesFlow
                .collect { map ->
                val list = map[baseUrl] ?: listOf()
                if (list.find { cookie -> cookie.name == "Access-Token" } != null) {
                    _sharedFlow.emit(RootScreenContract.Event.OnNavigateToDashboard)
                } else {
                    _sharedFlow.emit(RootScreenContract.Event.OnNavigateToAuth)
                }
            }
        }
    }

    override fun handleEvent(event: RootScreenContract.Event) {}
}