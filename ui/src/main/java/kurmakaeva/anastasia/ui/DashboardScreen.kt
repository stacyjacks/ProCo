package kurmakaeva.anastasia.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kurmakaeva.anastasia.ui.components.BottomTabBar
import kurmakaeva.anastasia.ui.components.ProgressBar
import kurmakaeva.anastasia.ui.components.TopBarTitle
import kurmakaeva.anastasia.ui.theme.ProCoTheme
import kurmakaeva.anastasia.ui.viewmodel.DashboardViewModel

@OptIn(ExperimentalFoundationApi::class)
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
                items = listOf(
                    stringResource(id = R.string.savedTitle),
                    stringResource(id = R.string.addInputTitle),
                    stringResource(id = R.string.deleteContentDesc)
                ),
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
                    goalText = stringResource(
                        id = goalString(viewModel.current.div(viewModel.goal))
                    ),
                    onClick = { onClickProgress() }
                )
                LazyVerticalGrid(columns = GridCells.Fixed(4)) {
                    items(viewModel.input.size) { index ->
                        Text(
                            text = viewModel.input[index].input.toString(),
                            modifier = Modifier
                                .padding(horizontal = 32.dp, vertical = 8.dp)
                                .combinedClickable(
                                    onLongClick = {
                                        viewModel.deleteSingleEntry(viewModel.input[index].id)
                                    },
                                    onClick = {}
                                )
                        )
                    }
                }

                if (viewModel.input.isEmpty()) {
                    Text(
                        text = stringResource(id = R.string.dashboardEmptyState),
                        modifier = Modifier.padding(horizontal = 32.dp, vertical = 8.dp)
                    )
                }
            }
        }
    )
}

private fun goalString(current: Float): Int {
    return when {
        current == 0.0f || current.isNaN() -> R.string.empty
        current < 0.5f -> R.string.progressCheerFirst
        current >= 0.5f && current < 0.75f -> R.string.progressCheerSecond
        current >= 0.75f && current < 1.0f -> R.string.progressCheerThird
        else -> R.string.progressCheerFinish
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
                goalText = stringResource(id = goalString(80.0f.div(100))),
                onClick = { /* preview only */ }
            )
        }
    }
}