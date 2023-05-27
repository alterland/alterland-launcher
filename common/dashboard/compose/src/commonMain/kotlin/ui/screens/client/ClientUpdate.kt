package ui.screens.client

import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import model.ClientUpdateMetrics

@Composable
fun ClientUpdate(
    clientUpdateMetrics: ClientUpdateMetrics = ClientUpdateMetrics()
) {
    LinearProgressIndicator(clientUpdateMetrics.percent)
    Text("${clientUpdateMetrics.timeLeft.toString()} с")
}