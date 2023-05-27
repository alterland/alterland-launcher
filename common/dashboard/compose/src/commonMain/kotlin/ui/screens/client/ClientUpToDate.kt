package ui.screens.client

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import theme.AppTheme

@Composable
fun ClientUpToDate() {
    val cloudOk = painterResource("vector/icons/ic_cloud_ok.svg")

    Image(
        painter = cloudOk,
        contentDescription = null,
        modifier = Modifier.padding(start = 16.dp)
    )
    Column(modifier = Modifier.padding(start = 12.dp)) {
        Text(
            "СОСТОЯНИЕ ОБНОВЛЕНИЙ",
            color = AppTheme.colors.labelPrimary
        )
        Text(
            "Все обновления установлены",
            color = AppTheme.colors.labelTertiary
        )
    }
}