package di

import org.kodein.di.DI

val dashboardModule = DI.Module("dashboardModule") {
    importAll(dashboardPresentationModule, dashboardDataModule)
}