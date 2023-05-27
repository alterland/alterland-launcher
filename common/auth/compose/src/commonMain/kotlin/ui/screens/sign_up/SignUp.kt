package ui.screens.sign_up

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import base.Lce
import sign_up.SignUpContract
import theme.AppTheme
import widgets.Button
import widgets.Input
import widgets.InputType
import widgets.SocialButton

@Composable
fun SignUp(
    state: SignUpContract.State,
    setEvent: (e: SignUpContract.Event) -> Unit,
    navigateBack: () -> Unit
) {

    fun isIOInProgress() = state.signUpProgress || state.vkSignUpProgress || state.googleSignUpProgress

    val elementsPadding = 22.dp

    val icNickOk = painterResource("vector/icons/ic_check.svg")
    val icNickBusy = painterResource("vector/icons/ic_dont.svg")
    val icEmail = painterResource("vector/icons/ic_email.svg")
    val icPassword = painterResource("vector/icons/ic_password.svg")
    val icVk = painterResource("vector/icons/ic_vk.svg")
    val icGoogle = painterResource("vector/icons/ic_google.svg")

    Column {
        Input(
            hint = "Ник",
            text = state.nickName,
            icon = painterResource("vector/icons/ic_login.svg"),
            enabled = !isIOInProgress(),
            isLoading = state.checkNickQuery is Lce.Loading,
            endIcon = when(state.checkNickQuery) {
                is Lce.Content -> when((state.checkNickQuery as Lce.Content<Boolean?>).data) {
                    true -> icNickBusy
                    false -> icNickOk
                    null -> null
                }
                else -> null
            }
        ) { setEvent(SignUpContract.Event.OnNickInput(it)) }
        Input(
            hint = "Email",
            text = state.email,
            icon = icEmail,
            enabled = !isIOInProgress(),
            modifier = Modifier.padding(top = elementsPadding),
        ) { setEvent(SignUpContract.Event.OnEmailInput(it)) }
        Input(
            hint = "Пароль",
            text = state.password,
            icon = icPassword,
            type = InputType.PASSWORD,
            enabled = !isIOInProgress(),
            modifier = Modifier.padding(top = elementsPadding),
        ) { setEvent(SignUpContract.Event.OnPasswordInput(it)) }
        Button(
            text = "Продолжить",
            isLoading = state.signUpProgress,
            modifier = Modifier.fillMaxWidth().padding(top = elementsPadding)
        ) { setEvent(SignUpContract.Event.OnSignUpClicked) }
        SocialButton(
            "Создать через Вконтакте",
            icVk,
            isEnabled = !isIOInProgress(),
            isLoading = state.vkSignUpProgress,
            modifier = Modifier.padding(top = elementsPadding),
        ) { setEvent(SignUpContract.Event.OnVkSignUpClicked) }
        SocialButton(
            "Создать через Google",
            icGoogle,
            isEnabled = !isIOInProgress(),
            isLoading = state.googleSignUpProgress,
            modifier = Modifier.padding(top = elementsPadding/2),
        ) { setEvent(SignUpContract.Event.OnGoogleSignUpClicked) }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = elementsPadding),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "Назад",
                color = AppTheme.colors.labelSecondary,
                fontSize = 12.sp,
                modifier = Modifier.clickable(onClick = navigateBack)
            )
        }
    }
}

@Preview
@Composable
fun SignUpPreview() {
    SignUp(SignUpContract.State(), {}, {})
}