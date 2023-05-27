package ui.screens.recovery

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.kodein.rememberScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import recovery.RecoveryScreenModel

class RecoveryScreen: Screen {

    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel<RecoveryScreenModel>()
        val state by screenModel.state.collectAsState()

        val navigator = LocalNavigator.currentOrThrow

        Recovery(
            state = state,
            setEvent = { e -> screenModel.setEvent(e) },
            navigateBack = { navigator.pop() }
        )
    }
}