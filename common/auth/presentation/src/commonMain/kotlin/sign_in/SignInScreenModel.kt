package sign_in

import baseUrl
import cafe.adriel.voyager.core.model.coroutineScope
import di.Inject
import domain
import extentions.handleErrors
import extentions.launchSafe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import local.persistent.PersistentStorage
import repository.UserRepository
import screen_model.BaseScreenModel

class SignInScreenModel(
    private val userRepository: UserRepository = Inject.instance(),
    private val persistentStorage: PersistentStorage = Inject.instance()
): BaseScreenModel<SignInContract.Event, SignInContract.State, SignInContract.Effect>(
    initialState = SignInContract.State()
) {
    override fun handleEvent(event: SignInContract.Event) {
        when(event) {
            is SignInContract.Event.OnLoginInput -> setState { copy(login = event.data) }
            is SignInContract.Event.OnPasswordInput -> setState { copy(password = event.data) }
            is SignInContract.Event.OnRememberMeChecked -> switchRememberMe()

            is SignInContract.Event.OnSignInClicked -> signIn()
            is SignInContract.Event.OnVkSignInClicked -> vkSignIn()
            is SignInContract.Event.OnGoogleSignInClicked -> googleSignIn()
            is SignInContract.Event.OnNavigateToDashboard -> {  }
        }
    }

    init {
        persistentStorage.settingsFlow.onEach {
            val remember = it[PersistentStorage.REMEMBER].toBoolean()
            when {
                remember != state.value.remember ->
                    setState { copy(remember = remember) }
            }
        }.handleErrors(::onError).launchIn(coroutineScope)

        persistentStorage.cookiesFlow.map { it[baseUrl] ?: listOf() }.onEach { list ->
            if (list.find { cookie -> cookie.name == "Access-Token" } != null) {
                setEvent(SignInContract.Event.OnNavigateToDashboard)
            }
        }.handleErrors(::onError).launchIn(coroutineScope)
    }

    private fun switchRememberMe() = coroutineScope.launchSafe(
        context = Dispatchers.IO,
        onError = ::onError
    ) {
        val value = persistentStorage.getBoolean(PersistentStorage.REMEMBER) ?: false
        persistentStorage.storeSetting(PersistentStorage.REMEMBER, !value)
    }

    private fun signIn() = coroutineScope.launchSafe(
        onError = ::onError,
        onComplete = { setState { copy(signInProgress = false) } }
    ) {
        setState { copy(signInProgress = true) }

        userRepository.signIn(
            email = state.value.login,
            password = state.value.password
        )
    }

    private fun vkSignIn() = setEffect { SignInContract.Effect.ShowToastSocialsSignInNotYetDone }

    private fun googleSignIn() = setEffect { SignInContract.Effect.ShowToastSocialsSignInNotYetDone }
}