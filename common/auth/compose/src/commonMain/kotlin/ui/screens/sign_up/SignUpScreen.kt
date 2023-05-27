package ui.screens.sign_up

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.kodein.rememberScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import sign_up.SignUpScreenModel

class SignUpScreen: Screen {

    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel<SignUpScreenModel>()
        val state by screenModel.state.collectAsState()

        val navigator = LocalNavigator.currentOrThrow

        SignUp(
            state = state,
            setEvent = { e -> screenModel.setEvent(e) },
            navigateBack = { navigator.pop() }
        )
    }
}