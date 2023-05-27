package ui.screens.container

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.FadeTransition
import container.DashboardContract
import theme.AppTheme
import ui.screens.client_info.ClientInfoScreen
import ui.screens.container.miniprofile.MiniProfile
import ui.screens.menu.clients.MenuClientsList

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Dashboard(
    state: DashboardContract.State,
    setEvent: (e: DashboardContract.Event) -> Unit
) {
    val avatar = painterResource("drawable/avatar_rofl.png")

    Row(Modifier.fillMaxWidth()) {
        Column(Modifier
            .fillMaxHeight()
            .fillMaxWidth(0.25f)
            .background(AppTheme.colors.backgroundSecondary)
        ) {
            Column(
                modifier = Modifier.padding(top = 20.dp).fillMaxHeight().fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                MenuClientsList(
                    clients = state.clients,
                    modifier = Modifier.padding(start = 9.dp, top = 12.dp, end = 11.dp)
                )
                MiniProfile(
                    nickName = state.nickName,
                    avatar = avatar,
                    modifier = Modifier.padding(start = 16.dp, bottom = 16.dp, end = 11.dp)
                ) {
                    setEvent(DashboardContract.Event.OnSignOutClicked)
                }
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Navigator(ClientInfoScreen()) { navigator ->
                FadeTransition(navigator)
            }
        }
    }
}
