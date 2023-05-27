package ui.screens.menu.clients

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import model.ClientItem
import theme.AppTheme

@Composable
fun MenuClientItem(
    item: ClientItem,
    modifier: Modifier = Modifier,
) {

    val clientCover = painterResource("drawable/client_cover.png")

    Row(
        modifier = modifier
            .defaultMinSize(minHeight = 48.dp)
            .background(AppTheme.colors.backgroundElevatedSecondary, RoundedCornerShape(5.dp))
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = clientCover,
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
            Text(
                item.clientName,
                color = AppTheme.colors.labelPrimary,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        ServerStatus(enabled = true)
    }
}