package kurmakaeva.anastasia.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kurmakaeva.anastasia.ui.components.BottomTabBar
import kurmakaeva.anastasia.ui.components.ProgressBar
import kurmakaeva.anastasia.ui.components.TopBarTitle
import kurmakaeva.anastasia.ui.theme.ProCoTheme
import kurmakaeva.anastasia.ui.viewmodel.DashboardViewModel

@Composable
fun DashboardScreen(
    onNavigateToSaved: () -> Unit,
    onNavigateToAdd: () -> Unit,
    onClickProgress: () -> Unit,
    viewModel: DashboardViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            TopBarTitle(screen = ScreenType.Dashboard)
        },
        bottomBar = {
            BottomTabBar(
                items = listOf("Saved", "Add input", "Delete daily data"),
                icons = listOf(
                    Icons.AutoMirrored.Default.List,
                    Icons.Default.Add,
                    Icons.Default.Delete
                ),
                actions = listOf(
                    { onNavigateToSaved() },
                    { onNavigateToAdd() },
                    { viewModel.resetDailyData() }
                )
            )
        },
        content = {
            Column(modifier = Modifier.padding(it)) {
                ProgressBar(
                    goal = viewModel.goal,
                    current =
                    if (viewModel.current > viewModel.goal) viewModel.goal
                    else viewModel.current,
                    goalText = goalText(viewModel.current.div(viewModel.goal)),
                    onClick = { onClickProgress() }
                )
                LazyVerticalGrid(columns = GridCells.Fixed(4)) {
                    items(viewModel.input.size) { index ->
                        Text(
                            text = viewModel.input[index].input.toString(),
                            modifier = Modifier.padding(horizontal = 32.dp, vertical = 8.dp)
                        )
                    }
                }

                if (viewModel.input.isEmpty()) {
                    Text(
                        text = "No data. Go grab a bite.",
                        modifier = Modifier.padding(horizontal = 32.dp, vertical = 8.dp)
                    )
                }
            }
        }
    )
}

private fun goalText(current: Float): String {
    return when {
        current == 0.0f -> ""
        current < 0.5f -> "You're off to a good start!"
        current >= 0.5f && current < 0.75f -> "Nice! Keep going."
        current >= 0.75f && current < 1.0f -> "Almost there!"
        else -> "Well done! Come back tomorrow."
    }
}

@Preview
@Composable
fun DashboardPreview() {
    ProCoTheme {
        Column {
            TopBarTitle(screen = ScreenType.Dashboard)
            ProgressBar(
                goal = 80.0f,
                current = 80.0f,
                goalText = goalText(80.0f.div(100)),
                onClick = { /* preview only */ }
            )
        }
    }
}