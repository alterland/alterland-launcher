import json.serializationModule
import ktor.ktorModule
import local.localModule
import org.kodein.di.DI

val coreModule = DI.Module("coreModule") {
    importAll(serializationModule, ktorModule, localModule)
}