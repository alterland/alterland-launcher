package ui.screens.client_info

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import theme.AppTheme
import widgets.AppleCircularProgressIndicator
import widgets.Button

@Composable
fun ClientInfo() {

    val settingsIcon = painterResource("vector/icons/ic_settings.svg")

    var loading by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxHeight().fillMaxWidth()) {
        Image(
            painter = painterResource("drawable/role-play-bg.jpeg"),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0, 0, 0, 0x4D),
                            Color(0, 0, 0, 0x00),
                        )
                    )
                )
            )
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0, 0, 0, 0x00),
                            Color(0, 0, 0, 0x4D)
                        )
                    )
                )
            )
        }
        Column(
            modifier = Modifier.align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource("vector/rp_logo.svg"),
                contentDescription = null,
                modifier = Modifier.padding(bottom = 14.dp)
            )
            Text(
                "Захватывающие виды, невероятная архитектура,\nуникальный геймплей.",
                color = AppTheme.colors.forceWhitePrimary,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 14.dp)
            )
            Row(modifier = Modifier.padding(bottom = 20.dp)) {
                if (loading) {
                    AppleCircularProgressIndicator(
                        modifier = Modifier.size(34.dp),
                        color = Color(255,255,255,0x40),
                        strokeWidth = 3.dp
                    )
                } else {
//                Button(
//                    icon = settingsIcon,
//                    backgroundColor = Color(65, 62, 64),
//                    modifier = Modifier.padding(start = 24.dp).size(34.dp)
//                )
                    Button(
                        text = "Играть",
                        backgroundColor = Color(52, 120, 246),
                        isLoading = false,
                        isEnabled = true,
                        modifier = Modifier.padding(start = 8.dp).width(153.dp).height(34.dp)
                    ) {
                        loading = true
                    }
                }
            }
        }
    }
}