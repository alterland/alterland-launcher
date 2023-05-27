package ktor

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

internal val ktorModule = DI.Module("ktorModule") {
    bind<io.ktor.client.HttpClient>() with singleton { HttpClient(instance()).client }
}