package ui.screens.container

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.kodein.rememberScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import container.AuthContainerContract
import container.AuthContainerScreenModel
import navigation.RootScreenProvider

class AuthContainerScreen: Screen {

    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel<AuthContainerScreenModel>()
        val event by screenModel.event.collectAsState(null)

        val navigator = LocalNavigator.currentOrThrow

        val dashboardScreen = rememberScreen(RootScreenProvider.Dashboard)

        when(event) {
            is AuthContainerContract.Event.OnNavigateToDashboard -> {
                navigator.push(dashboardScreen)
            }
           else -> {}
        }

        AuthContainer()
    }

}