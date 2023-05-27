package widgets

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import theme.AppTheme

@Composable
fun CheckBox(
    checked: Boolean = false,
    text: String = "",
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val icCheck = painterResource("vector/icons/ic_checkbox_check.svg")
    val size = 14
    val shape = RoundedCornerShape((size/2).dp)

    Row(modifier = modifier.clickable(onClick = onClick)) {
        if (checked) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(size.dp)
                    .background(AppTheme.colors.primary, shape)
            ) {
                Image(
                    painter = icCheck,
                    contentDescription = null,
                    modifier = Modifier.size(7.dp)
                )
            }
        } else {
            Box(
                modifier = Modifier
                    .size(size.dp)
                    .border(BorderStroke(1.5.dp, AppTheme.colors.labelSecondary), shape)
            ) {}
        }
        Text(
            text = text,
            fontSize = 12.sp,
            color = AppTheme.colors.labelSecondary,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}