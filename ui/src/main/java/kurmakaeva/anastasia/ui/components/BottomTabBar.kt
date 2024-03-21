package kurmakaeva.anastasia.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BottomTabBar(
    onTapButtonOne: () -> Unit,
    onTapButtonTwo: () -> Unit,
    onTapButtonThree: () -> Unit
) {
    BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
        val itemWidth = this@BoxWithConstraints.maxWidth.div(3)
        BottomAppBar(
            modifier = Modifier
                .height(48.dp)
                .background(MaterialTheme.colorScheme.background)
                .align(Alignment.Center)
        ) {
            Button(
                onClick = { onTapButtonOne() },
                modifier = Modifier
                    .padding(horizontal = 1.dp)
                    .width(itemWidth)
                    .align(Alignment.CenterVertically),
                shape = RoundedCornerShape(4.dp)
            ) {
                Icon(imageVector = Icons.AutoMirrored.Default.List, contentDescription = "Saved")
            }
            Button(
                onClick = { onTapButtonTwo() },
                modifier = Modifier
                    .padding(horizontal = 1.dp)
                    .width(itemWidth)
                    .align(Alignment.CenterVertically),
                shape = RoundedCornerShape(4.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
            Button(
                onClick = { onTapButtonThree() },
                modifier = Modifier
                    .padding(horizontal = 1.dp)
                    .width(itemWidth)
                    .align(Alignment.CenterVertically),
                shape = RoundedCornerShape(4.dp)
            ) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete daily data")
            }
        }
    }
}

@Preview
@Composable
private fun BottomTabBarPreview() {
    BottomTabBar(
        onTapButtonOne = { /* preview only */ },
        onTapButtonTwo = { /* preview only */ },
        onTapButtonThree = { /* preview only */ }
    )
}