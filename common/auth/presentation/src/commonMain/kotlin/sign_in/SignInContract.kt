package sign_in

import screen_model.UiEffect
import screen_model.UiEvent
import screen_model.UiState

class SignInContract {

    sealed class Event : UiEvent {
        data class OnLoginInput(val data: String): Event()
        data class OnPasswordInput(val data: String): Event()
        object OnRememberMeChecked: Event()

        object OnSignInClicked: Event()
        object OnVkSignInClicked: Event()
        object OnGoogleSignInClicked: Event()

        object OnNavigateToDashboard: Event()
    }

    data class State(
        val login: String = "",
        val password: String = "",
        val remember: Boolean = false,
        val signInProgress: Boolean = false,
        val vkSignInProgress: Boolean = false,
        val googleSignInProgress: Boolean = false
    ) : UiState

    sealed class Effect: UiEffect {
        object ShowToastSocialsSignInNotYetDone: Effect()
    }

}