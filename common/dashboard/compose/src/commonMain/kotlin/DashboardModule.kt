import cafe.adriel.voyager.core.registry.ScreenProvider
import cafe.adriel.voyager.core.registry.screenModule
import ui.screens.client_info.ClientInfoScreen
import ui.screens.container.DashboardScreen

sealed class DashboardScreenProvider : ScreenProvider {
    object Dashboard: DashboardScreenProvider()
    object ClientInfo: DashboardScreenProvider()
}

val dashboardScreenModule = screenModule {
    register<DashboardScreenProvider.Dashboard> { DashboardScreen() }
    register<DashboardScreenProvider.ClientInfo> { ClientInfoScreen() }
}