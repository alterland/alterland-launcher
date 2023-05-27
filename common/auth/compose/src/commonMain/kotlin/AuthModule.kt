import cafe.adriel.voyager.core.registry.ScreenProvider
import cafe.adriel.voyager.core.registry.screenModule
import ui.screens.recovery.RecoveryScreen
import ui.screens.sign_in.SignInScreen
import ui.screens.sign_up.SignUpScreen

sealed class AuthScreenProvider : ScreenProvider {
    object SignIn : AuthScreenProvider()
    object SignUp : AuthScreenProvider()
    object Recovery : AuthScreenProvider()
}

val authScreenModule = screenModule {
    register<AuthScreenProvider.SignIn> { SignInScreen() }
    register<AuthScreenProvider.SignUp> { SignUpScreen() }
    register<AuthScreenProvider.Recovery> { RecoveryScreen() }
}