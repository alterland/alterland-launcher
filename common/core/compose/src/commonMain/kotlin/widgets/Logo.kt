package widgets

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import theme.AppTheme

@Composable
fun Logo(
    textOnly: Boolean = false,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        if (!textOnly) {
            Image(
                painter = painterResource("drawable/logo_house.png"),
                contentDescription = null,
                Modifier.height(53.dp)
            )
            Image(
                painter = painterResource("drawable/logo_divider.svg"),
                contentDescription = null,
                Modifier.padding(start = 18.dp, top = 3.dp, end = 17.dp)
            )
        }
        Image(
            painter = painterResource("drawable/logo_name.svg"),
            contentDescription = null,
            modifier = Modifier.padding(top = 4.dp),
            colorFilter = ColorFilter.tint(AppTheme.colors.labelPrimary)
        )
    }
}

@Composable
@Preview
private fun LogoPreview() {
    Logo(true)
}
