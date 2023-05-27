package recovery

import cafe.adriel.voyager.core.model.coroutineScope
import di.Inject
import extentions.launchSafe
import repository.UserRepository
import screen_model.BaseScreenModel

class RecoveryScreenModel(
    private val userRepository: UserRepository = Inject.instance()
): BaseScreenModel<RecoveryContract.Event, RecoveryContract.State, RecoveryContract.Effect>(
    initialState = RecoveryContract.State()
) {
    override fun handleEvent(event: RecoveryContract.Event) {
        when(event) {
            is RecoveryContract.Event.OnEmailInput -> setState { copy(email = event.data) }
            is RecoveryContract.Event.OnResetPasswordClicked -> resetPassword()
        }
    }

    private fun resetPassword() = coroutineScope.launchSafe(
        onError = ::onError,
        onComplete = { setState { copy(sendCodeProgress = false) } }
    ) {
        setState { copy(sendCodeProgress = true) }
        userRepository.resetPassword(state.value.email)
    }
}