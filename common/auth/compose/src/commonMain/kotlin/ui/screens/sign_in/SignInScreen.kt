package ui.screens.sign_in

import androidx.compose.runtime.*
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.kodein.rememberScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import sign_in.SignInScreenModel

class SignInScreen: Screen {

    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel<SignInScreenModel>()
        val state by screenModel.state.collectAsState()

        val navigator = LocalNavigator.currentOrThrow
        val recoveryScreen = rememberScreen(AuthScreenProvider.Recovery)
        val signUpScreen = rememberScreen(AuthScreenProvider.SignUp)

        SignIn(
            state = state,
            setEvent = { e -> screenModel.setEvent(e) },
            navigateToRecovery = { navigator.push(recoveryScreen) },
            navigateToSignUp = { navigator.push(signUpScreen) }
        )
    }
}