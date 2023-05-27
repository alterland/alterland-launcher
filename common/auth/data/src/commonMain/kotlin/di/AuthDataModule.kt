package di

import api.IdentityApi
import di.Inject.instance
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.provider
import org.kodein.di.singleton
import repository.UserRepository
import repository.UserRepositoryImpl

val authDataModule = DI.Module("authDataModule") {
    bind<UserRepository>() with singleton {
        UserRepositoryImpl(instance())
    }

    bind<IdentityApi>() with provider {
        IdentityApi(instance())
    }
}