package kurmakaeva.anastasia.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kurmakaeva.anastasia.ui.ScreenType
import kurmakaeva.anastasia.ui.theme.Typography

@Composable
fun TopBarTitle(screen: ScreenType) {
    Text(
        text = stringResource(id = screen.title),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 32.dp),
        textAlign = TextAlign.Center,
        style = Typography.titleLarge
    )
}