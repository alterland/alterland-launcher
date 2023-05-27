import di.Inject
import di.authModule
import di.dashboardModule
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.direct
import org.kodein.di.singleton

object PlatformSDK {

    fun init(
        configuration: PlatformConfiguration
    ) {
        val rootModule = DI.Module("rootModule") {
            bind<PlatformConfiguration>() with singleton { configuration }
        }

        Inject.createDependencies(
            DI {
                importAll(
                    rootModule,
                    coreModule,
                    authModule,
                    dashboardModule
                )
            }.direct
        )
    }
}