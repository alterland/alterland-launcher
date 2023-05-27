package sign_up

import base.Lce
import cafe.adriel.voyager.core.model.coroutineScope
import di.Inject
import extentions.handleErrors
import extentions.launchSafe
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import repository.UserRepository
import screen_model.BaseScreenModel

class SignUpScreenModel(
    private val userRepository: UserRepository = Inject.instance()
): BaseScreenModel<SignUpContract.Event, SignUpContract.State, SignUpContract.Effect>(
    initialState = SignUpContract.State()
) {
    override fun handleEvent(event: SignUpContract.Event) {
        when(event) {
            is SignUpContract.Event.OnInitialLoginSet -> onInitialLoginSet(event.data)

            is SignUpContract.Event.OnNickInput -> onNickInput(event.data)
            is SignUpContract.Event.OnEmailInput -> setState { copy(email = event.data) }
            is SignUpContract.Event.OnPasswordInput -> setState { copy(password = event.data) }

            is SignUpContract.Event.OnSignUpClicked -> signUp()
            is SignUpContract.Event.OnVkSignUpClicked -> vkSignUp()
            is SignUpContract.Event.OnGoogleSignUpClicked -> googleSignUp()
        }
    }

    private val searchNickFlow = MutableSharedFlow<String>(1, 0, BufferOverflow.DROP_OLDEST)

    init {
        searchNickFlow.debounce(1300).onEach {
            setState { copy(checkNickQuery = Lce.Loading()) }
            if (it.isNotEmpty()) {
                setState { copy(checkNickQuery = Lce.Loading()) }
                val result = userRepository.checkNick(state.value.nickName)
                setState { copy(checkNickQuery = Lce.Content(result)) }
            }
        }.handleErrors {
            setState { copy(checkNickQuery = Lce.Content(true)) }
            onError(it)
        }.launchIn(coroutineScope)
    }

    private fun onInitialLoginSet(login: String = "", password: String = "") {
        if (login.contains("@")) {
            setState { copy(email = login) }
        } else {
            onNickInput(login)
        }
        setState { copy(password = password) }
    }

    private fun onNickInput(text: String) {
        setState { copy(nickName = text) }
        searchNickFlow.tryEmit(text)
    }

    private fun signUp() = coroutineScope.launchSafe(
        onError = ::onError,
        onComplete = { setState { copy(signUpProgress = false) } }
    ) {
        setState { copy(signUpProgress = true) }

        val result = userRepository.signUp(
            state.value.nickName,
            state.value.email,
            state.value.password,
        )
    }

    private fun vkSignUp() = setEffect { SignUpContract.Effect.ShowToastSocialsSignInNotYetDone }

    private fun googleSignUp() = setEffect { SignUpContract.Effect.ShowToastSocialsSignInNotYetDone }
}