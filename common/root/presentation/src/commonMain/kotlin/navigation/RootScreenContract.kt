package navigation

import screen_model.UiEffect
import screen_model.UiEvent
import screen_model.UiState

class RootScreenContract {
    sealed class Event : UiEvent {
        object OnNavigateToAuth: Event()
        object OnNavigateToDashboard: Event()
    }

    object State : UiState

    sealed class Effect: UiEffect
}