package kurmakaeva.anastasia.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BottomTabBar(
    onNavigateToLinkOne: () -> Unit,
    onNavigateToLinkTwo: () -> Unit
) {
    BoxWithConstraints {
        BottomAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(MaterialTheme.colorScheme.background)
        ) {
            Button(
                onClick = { onNavigateToLinkOne() },
                modifier = Modifier
                    .padding(1.dp)
                    .width(this@BoxWithConstraints.maxWidth.div(2))
                    .align(Alignment.CenterVertically),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(text = "Saved")
            }
            Button(
                onClick = { onNavigateToLinkTwo() },
                modifier = Modifier
                    .padding(1.dp)
                    .width(this@BoxWithConstraints.maxWidth.div(2))
                    .align(Alignment.CenterVertically),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(text = "+")
            }
        }
    }
}