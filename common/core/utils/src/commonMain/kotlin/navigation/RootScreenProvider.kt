package navigation

import cafe.adriel.voyager.core.registry.ScreenProvider

sealed class RootScreenProvider : ScreenProvider {
    object Auth : RootScreenProvider()
    object Dashboard : RootScreenProvider()
}