package theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Typography
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

//https://developer.apple.com/design/human-interface-guidelines/foundations/color/

@Immutable
class AppColors(
    val primary: Color,

    //default
    val red: Color,
    val orange: Color,
    val yellow: Color,
    val green: Color,
    val mint: Color,
    val teal: Color,
    val cyan: Color,
    val blue: Color,
    val indigo: Color,
    val purple: Color,
    val pink: Color,
    val brown: Color,

    val gray: Color,
    val gray2: Color,
    val gray3: Color,
    val gray4: Color,
    val gray5: Color,
    val gray6: Color,

    val forceWhitePrimary: Color,
    val forceWhiteSecondary: Color,
    val forceWhiteTertiary: Color,

    val forceDarkPrimary: Color,
    val forceDarkSecondary: Color,
    val forceDarkTertiary: Color ,
    val forceDarkQuaternary: Color,

    //background
    val backgroundPrimary: Color,
    val backgroundSecondary: Color,
    val backgroundTertiary: Color,
    val backgroundElevatedPrimary: Color,
    val backgroundElevatedSecondary: Color,
    val backgroundElevatedTertiary: Color,

    //label
    val labelPrimary: Color,
    val labelSecondary: Color,
    val labelTertiary: Color,
    val labelQuaternary: Color,

    val transparent: Color,

    val isDark: Boolean
)

@Composable
fun AppTheme(
    lightColors: AppColors = lightColors(),
    darkColors: AppColors = darkColors(),
    typography: Typography = AppTheme.typography,
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    //val colors = if (isDarkTheme) darkColors else lightColors
    CompositionLocalProvider(
        LocalColors provides darkColors,
        LocalTypography provides typography
    ) {
        ProvideTextStyle(value = typography.body1) {
            content()
        }
    }
}

object AppTheme {
    val colors: AppColors
        @Composable
        get() = LocalColors.current

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}