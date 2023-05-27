package container

import screen_model.UiEffect
import screen_model.UiEvent
import screen_model.UiState

class AuthContainerContract {

    sealed class Event : UiEvent {
        object OnNavigateToDashboard: Event()
    }

    class State: UiState

    sealed class Effect: UiEffect
}