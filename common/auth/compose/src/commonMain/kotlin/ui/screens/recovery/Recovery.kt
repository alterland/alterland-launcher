package ui.screens.recovery

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import recovery.RecoveryContract
import theme.AppTheme
import widgets.Button
import widgets.Input

@Composable
fun Recovery(
    state: RecoveryContract.State,
    setEvent: (e: RecoveryContract.Event) -> Unit,
    navigateBack: () -> Unit
) {

    val top = 18.dp

    Column {
        Input(
            text = state.email,
            hint = "Email",
            icon = painterResource("vector/icons/ic_login.svg"),
            modifier = Modifier.padding(top = top),
        ) { setEvent(RecoveryContract.Event.OnEmailInput(it)) }
        Button(
            text = "Продолжить",
            isEnabled = !state.sendCodeProgress,
            isLoading = state.sendCodeProgress,
            modifier = Modifier.fillMaxWidth().padding(top = top)
        ){  setEvent(RecoveryContract.Event.OnResetPasswordClicked) }
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = top),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                "Назад",
                color = AppTheme.colors.labelSecondary,
                fontSize = 12.sp,
                modifier = Modifier.clickable(onClick = navigateBack)
            )
        }
    }
}