package ui.screens.client_info

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.kodein.rememberScreenModel
import client_info.ClientInfoScreenModel

class ClientInfoScreen: Screen {

    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel<ClientInfoScreenModel>()
        val state by screenModel.state.collectAsState()

        ClientInfo()
    }
}