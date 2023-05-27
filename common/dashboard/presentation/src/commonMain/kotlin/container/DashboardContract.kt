package container

import model.ClientItem
import screen_model.UiEffect
import screen_model.UiEvent
import screen_model.UiState

class DashboardContract {
    sealed class Event : UiEvent {
        object OnSignOutClicked: Event()
        object OnNavigateToAuth: Event()
    }

    data class State(
        val currentClient: String? = null,
        val nickName: String = "",
        val clients: List<ClientItem> = listOf()
    ): UiState

    sealed class Effect: UiEffect
}