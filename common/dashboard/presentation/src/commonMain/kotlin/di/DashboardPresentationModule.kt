package di

import client_info.ClientInfoScreenModel
import container.DashboardScreenModel
import di.Inject.instance
import org.kodein.di.DI
import org.kodein.di.bindProvider

val dashboardPresentationModule = DI.Module("dashboardPresentationModule") {
    bindProvider { DashboardScreenModel() }
    bindProvider { ClientInfoScreenModel(instance()) }
}