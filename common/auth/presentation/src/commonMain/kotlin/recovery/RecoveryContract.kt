package recovery

import screen_model.UiEffect
import screen_model.UiEvent
import screen_model.UiState

class RecoveryContract {

    sealed class Event : UiEvent {
        data class OnEmailInput(val data: String): Event()
        object OnResetPasswordClicked: Event()
    }

    data class State(
        val email: String = "",
        val sendCodeProgress: Boolean = false,
    ): UiState

    sealed class Effect: UiEffect
}