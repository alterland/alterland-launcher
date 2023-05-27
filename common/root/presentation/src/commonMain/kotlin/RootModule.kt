import navigation.RootScreenModel
import org.kodein.di.DI
import org.kodein.di.bindProvider

val rootPresentationModule = DI.Module("rootPresentationModule") {
    bindProvider { RootScreenModel() }
}