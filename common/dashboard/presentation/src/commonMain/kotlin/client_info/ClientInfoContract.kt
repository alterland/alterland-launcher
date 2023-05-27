package client_info

import model.ClientUpdateMetrics
import screen_model.UiEffect
import screen_model.UiEvent
import screen_model.UiState

class ClientInfoContract {
    sealed class Event : UiEvent {
        object OnPlayClientClicked: Event()
        object OnChangeDownloadStatusClicked: Event()
    }

    data class State(
        val filesScanProcess: Boolean = false,
        val clientUpdateMetricsProcess: ClientUpdateMetrics? = null
    ): UiState

    sealed class Effect: UiEffect
}