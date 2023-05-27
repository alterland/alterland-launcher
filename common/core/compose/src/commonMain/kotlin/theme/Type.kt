package theme

import androidx.compose.material.Typography
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.unit.sp

val defaultFontFamily = FontFamily(
    Font(
        resource = "font/inter_dynamic.ttf"
    )
)

val LocalTypography = staticCompositionLocalOf {
    Typography(
        body1 = TextStyle(
            fontFamily = defaultFontFamily,
            fontSize = 14.sp,
        ),
    )
}