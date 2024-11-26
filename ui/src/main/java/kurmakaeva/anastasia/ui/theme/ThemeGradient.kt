package kurmakaeva.anastasia.ui.theme

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val themeGradient = Brush.linearGradient(
    colors = listOf(Color.Transparent, Purple40),
    start = Offset.Zero,
    end = Offset(0f, Float.POSITIVE_INFINITY)
)