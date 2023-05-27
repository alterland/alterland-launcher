package di

import api.ClientApi
import di.Inject.instance
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.provider
import org.kodein.di.singleton
import repository.ClientRepository
import repository.ClientRepositoryImpl

val dashboardDataModule = DI.Module("dashboardDataModule") {
    bind<ClientRepository>() with singleton {
        ClientRepositoryImpl(instance())
    }

    bind<ClientApi>() with provider {
        ClientApi(instance())
    }
}