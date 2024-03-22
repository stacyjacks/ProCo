package kurmakaeva.anastasia.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BottomTabBar(
    items: List<String>,
    icons: List<ImageVector>,
    actions: List<() -> Unit>
) {
    BoxWithConstraints {
        val itemWidth = this.maxWidth.div(items.size)
        BottomAppBar(
            modifier = Modifier
                .height(48.dp)
                .background(MaterialTheme.colorScheme.background)
                .align(Alignment.Center)
        ) {
            Row {
                items.forEachIndexed { index, item ->
                    Button(
                        onClick = actions[index],
                        modifier = Modifier
                            .padding(horizontal = 1.dp)
                            .width(itemWidth)
                            .align(Alignment.CenterVertically),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Icon(imageVector = icons[index], contentDescription = item)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun BottomTabBarPreview() {
    BottomTabBar(
        items = listOf("Saved", "Add", "Delete daily data"),
        icons = listOf(Icons.AutoMirrored.Filled.List, Icons.Default.Add, Icons.Default.Delete),
        actions = listOf({}, {}, {}),
    )
}