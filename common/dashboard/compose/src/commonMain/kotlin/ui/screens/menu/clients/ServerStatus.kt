package ui.screens.menu.clients

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import theme.AppTheme

@Composable
fun ServerStatus(
    enabled: Boolean? = null,
    modifier: Modifier = Modifier
) {
    val serverStatusCircle = painterResource("vector/icons/ic_server_status.svg")

    val statusColor = when(enabled) {
        true -> AppTheme.colors.green
        false -> AppTheme.colors.red
        null -> AppTheme.colors.gray
    }

    Image(
        painter = serverStatusCircle,
        contentDescription = null,
        colorFilter = ColorFilter.tint(statusColor)
    )
}