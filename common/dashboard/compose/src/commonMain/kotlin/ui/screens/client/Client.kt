package ui.screens.client

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import model.ClientUpdateMetrics
import widgets.Button

@Composable
fun Client(
    filesScanProcess: Boolean = false,
    clientUpdateMetricsProcess: ClientUpdateMetrics? = null,
    onUpdateClient: () -> Unit = {}
) {
    val settingsIcon = painterResource("vector/icons/ic_settings.svg")

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxHeight()
    ) {
        Button(
            icon = settingsIcon,
            backgroundColor = Color(65,62,64),
            modifier = Modifier.padding(start = 24.dp).size(40.dp)
        )
        Button(
            text = "ИГРАТЬ",
            backgroundGradient = Brush.horizontalGradient(
                colors = listOf(
                    Color(80, 200, 47),
                    Color(25, 177, 78)
                )
            ),
            isLoading = filesScanProcess,
            isEnabled = !filesScanProcess,
            modifier = Modifier.padding(start = 8.dp).width(110.dp)
        ) {
            onUpdateClient()
        }

        if (clientUpdateMetricsProcess == null) {
            ClientUpToDate()
        } else {
            ClientUpdate(clientUpdateMetricsProcess)
        }
    }
}