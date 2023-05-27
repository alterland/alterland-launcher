package di

import org.kodein.di.DI

val authModule = DI.Module("authModule") {
    importAll(authPresentationModule, authDataModule)
}