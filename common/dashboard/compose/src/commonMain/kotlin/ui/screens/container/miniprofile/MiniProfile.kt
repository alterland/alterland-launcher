package ui.screens.container.miniprofile

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import theme.AppTheme
import widgets.LoadableItem
import widgets.loadables.LoadableImage
import widgets.loadables.LoadableText

@Composable
fun MiniProfile(
    nickName: String,
    avatar: Painter = painterResource("drawable/avatar_temp.png"),
    modifier: Modifier = Modifier,
    onExit: () -> Unit = {}
) {
    val exitIcon = painterResource("vector/icons/ic_exit.svg")

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth()
    ) {
        Row {
            LoadableItem(
                isLoading = nickName.isEmpty(),
                placeholder = {
                    LoadableImage(size = 32.dp)
                },
                content = {
                    Image(
                        painter = avatar,
                        contentScale = ContentScale.Crop,
                        contentDescription = null,
                        modifier = Modifier.clip(CircleShape).size(32.dp)
                    )
                }
            )
            Column (
                modifier = Modifier.padding(start = 8.dp)
            ) {
                LoadableItem(
                    isLoading = nickName.isEmpty(),
                    placeholder = {
                        LoadableText()
                        LoadableText(11.sp, 50.dp, Modifier.padding(top = 2.dp))
                    },
                    content = {
                        Text(
                            nickName,
                            fontSize = 13.sp,
                            color = AppTheme.colors.labelPrimary
                        )
                        Text(
                            "Игрок",
                            fontSize = 10.sp,
                            color = AppTheme.colors.labelSecondary,
                            modifier = Modifier.padding(top = 2.dp)
                        )
                    }
                )
            }
        }
        Image(
            painter = exitIcon,
            contentDescription = null,
            modifier = Modifier.padding(start = 8.dp).size(20.dp).clickable { onExit() }
        )
    }
}

@Composable
@Preview
private fun MiniProfilePreview() {
    MiniProfile("Aviator737")
}