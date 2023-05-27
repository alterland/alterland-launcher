package local

import local.persistent.PersistentStorage
import local.persistent.PersistentStorageImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton


internal val localModule = DI.Module("localModule") {
    bind<PersistentStorage>() with singleton {
        PersistentStorageImpl()
    }
}