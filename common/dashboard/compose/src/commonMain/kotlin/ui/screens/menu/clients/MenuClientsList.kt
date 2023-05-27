package ui.screens.menu.clients

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import model.ClientItem

@Composable
fun MenuClientsList(
    clients: List<ClientItem> = listOf(),
    modifier: Modifier = Modifier
) {
    Column {
        clients.forEach { client ->
            MenuClientItem(
                client,
                modifier = modifier
            )
        }
    }
}