import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.navigator.Navigator
import di.Inject
import navigation.RootScreen
import navigation.rootScreenModule
import org.kodein.di.compose.withDI

@Composable
fun Application() {
    ScreenRegistry {
        rootScreenModule()
        authScreenModule()
        dashboardScreenModule()
    }

    withDI(Inject.di.di) {
        Navigator(screen = RootScreen())
    }
}