package sign_up

import base.Lce
import screen_model.UiEffect
import screen_model.UiEvent
import screen_model.UiState

class SignUpContract {
    sealed class Event : UiEvent {
        data class OnInitialLoginSet(val data: String): Event()
        data class OnNickInput(val data: String): Event()
        data class OnEmailInput(val data: String): Event()
        data class OnPasswordInput(val data: String): Event()

        object OnSignUpClicked: Event()
        object OnVkSignUpClicked: Event()
        object OnGoogleSignUpClicked: Event()
    }

    data class State(
        val nickName: String = "",
        val email: String = "",
        val password: String = "",
        val checkNickQuery: Lce<Boolean?>? = null,
        val signUpProgress: Boolean = false,
        val vkSignUpProgress: Boolean = false,
        val googleSignUpProgress: Boolean = false,
    ) : UiState

    sealed class Effect: UiEffect {
        object ShowToastSocialsSignInNotYetDone: Effect()
    }
}