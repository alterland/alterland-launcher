package extentions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

fun CoroutineScope.launchSafe(
    onError: (Throwable) -> Unit,
    onSuccess: () -> Unit = {},
    onComplete: () -> Unit = {},
    context: CoroutineContext = Dispatchers.Default,
    action: suspend CoroutineScope.() -> Unit) = launch(context) {
        try {
            action(this)
            onSuccess()
        } catch (e: Throwable) {
            onError(e)
        } finally {
            onComplete()
        }
    }