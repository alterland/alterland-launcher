package ui.screens.container

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.FadeTransition
import theme.AppTheme
import ui.screens.sign_in.SignInScreen
import widgets.Logo

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AuthContainer() {
    Image(
        painter = painterResource("drawable/background_auth.jpg"),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxHeight()
    )
    Row(Modifier.fillMaxWidth()) {
        Column(Modifier
            .fillMaxHeight()
            .weight(1f)
        ) {
        }
        Column(
            Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.54f)
                .background(AppTheme.colors.backgroundSecondary)
                .padding(
                    start = 70.dp,
                    end = 70.dp,
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Logo(modifier = Modifier.padding(top = 50.dp, bottom = 26.dp))

            Navigator(SignInScreen()) { navigator ->
                FadeTransition(navigator)
            }
        }
    }
}
