package screen_model

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class BaseScreenModel<Event : UiEvent, State : UiState, Effect : UiEffect>(
    initialState: State
): StateScreenModel<State>(initialState) {
    private val _event : MutableSharedFlow<Event> = MutableSharedFlow()
    val event = _event.asSharedFlow()

    private val _effect : Channel<Effect> = Channel() //for one-time actions like toasts
    val effect = _effect.receiveAsFlow()

    init {
        event.onEach { e ->
            handleEvent(e)
        }.launchIn(coroutineScope)
    }

    abstract fun handleEvent(event: Event)

    fun setEvent(event : Event) =
        coroutineScope.launch { _event.emit(event) }

    protected inline fun setState(reduce: State.() -> State) {
        mutableState.value = state.value.reduce()
    }

    protected fun setEffect(builder: () -> Effect) =
        coroutineScope.launch { _effect.send(builder()) }

    fun onError(throwable: Throwable) {
        println("Error: $throwable")
    }
}