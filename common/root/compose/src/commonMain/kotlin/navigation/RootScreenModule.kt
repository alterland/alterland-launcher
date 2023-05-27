package navigation

import cafe.adriel.voyager.core.registry.screenModule
import ui.screens.container.AuthContainerScreen
import ui.screens.container.DashboardScreen

val rootScreenModule = screenModule {
    register<RootScreenProvider.Auth> { AuthContainerScreen() }
    register<RootScreenProvider.Dashboard> { DashboardScreen() }
}