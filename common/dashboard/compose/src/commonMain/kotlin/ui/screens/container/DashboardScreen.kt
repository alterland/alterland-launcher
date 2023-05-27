package ui.screens.container

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.kodein.rememberScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import container.DashboardContract
import container.DashboardScreenModel

class DashboardScreen: Screen {

    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel<DashboardScreenModel>()
        val state by screenModel.state.collectAsState()
        val event by screenModel.event.collectAsState(null)

        val navigator = LocalNavigator.currentOrThrow

        when(event) {
            is DashboardContract.Event.OnNavigateToAuth -> {
                navigator.popAll()
            }
            else -> {}
        }

        Dashboard(
            state = state,
            setEvent = { e -> screenModel.setEvent(e) }
        )
    }
}