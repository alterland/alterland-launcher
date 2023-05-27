package di

import container.AuthContainerScreenModel
import org.kodein.di.DI
import org.kodein.di.bindProvider
import recovery.RecoveryScreenModel
import sign_in.SignInScreenModel
import sign_up.SignUpScreenModel

val authPresentationModule = DI.Module("authPresentationModule") {
    bindProvider { AuthContainerScreenModel() }
    bindProvider { SignInScreenModel() }
    bindProvider { SignUpScreenModel() }
    bindProvider { RecoveryScreenModel() }
}