package kurmakaeva.anastasia.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kurmakaeva.anastasia.ui.components.AlertDialog
import kurmakaeva.anastasia.ui.components.BottomTabBar
import kurmakaeva.anastasia.ui.components.ProgressBar
import kurmakaeva.anastasia.ui.components.TopBarTitle
import kurmakaeva.anastasia.ui.theme.ProCoTheme
import kurmakaeva.anastasia.ui.theme.smallPurpleBold
import kurmakaeva.anastasia.ui.theme.themeGradient
import kurmakaeva.anastasia.ui.viewmodel.DashboardViewModel

@Composable
fun DashboardScreen(
    onNavigateToSaved: () -> Unit,
    onNavigateToAdd: () -> Unit,
    onClickProgress: () -> Unit,
    viewModel: DashboardViewModel = hiltViewModel()
) {
    Scaffold(
        modifier = Modifier
            .padding(bottom = 8.dp)
            .background(MaterialTheme.colorScheme.background),
        topBar = {
            TopBarTitle(screen = ScreenType.Dashboard)
        },
        bottomBar = {
            DashBottomBar(
                onNavigateToSaved = onNavigateToSaved,
                onNavigateToAdd = onNavigateToAdd,
                resetDailyData = { viewModel.resetDailyData() }
            )
        },
        content = {
            val openDialog = rememberSaveable { mutableStateOf(false) }
            val selectedItem = rememberSaveable { mutableIntStateOf(0) }

            DeleteEntryDialog(
                onConfirm = {
                    viewModel.deleteSingleEntry(viewModel.input[selectedItem.intValue].id)
                    openDialog.value = false
                },
                onDismiss = {
                    openDialog.value = false
                },
                openDialog = openDialog.value
            )

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
                LazyVerticalGrid(
                    columns = GridCells.FixedSize(80.dp),
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    items(viewModel.input.size) { index ->
                        Box(
                            modifier = Modifier
                                .clickable {
                                    openDialog.value = true
                                    selectedItem.intValue = index
                                }
                                .height(80.dp)
                                .padding(8.dp)
                                .background(shape = CircleShape, color = Color.White),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "${viewModel.input[index].input} gr",
                                textAlign = TextAlign.Center,
                                style = smallPurpleBold
                            )
                        }
                    }
                }

                if (viewModel.input.isEmpty()) {
                    Text(
                        text = stringResource(id = R.string.dashboardEmptyState),
                        modifier = Modifier
                            .padding(horizontal = 32.dp, vertical = 8.dp)
                    )
                }
            }
        }
    )
}

@Composable
private fun DashBottomBar(
    onNavigateToSaved: () -> Unit,
    onNavigateToAdd: () -> Unit,
    resetDailyData: () -> Unit
) {
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
            { resetDailyData() }
        )
    )
}

@Composable
fun DeleteEntryDialog(openDialog: Boolean, onConfirm: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        icon = Icons.Default.Warning,
        title = stringResource(id = R.string.deleteEntryDialogTitle),
        text = stringResource(id = R.string.deleteConfirmation),
        show = openDialog,
        onConfirm = { onConfirm() },
        onDismiss = { onDismiss() }
    )
}

@Preview
@Composable
fun DashboardPreview() {
    ProCoTheme {
        Scaffold(
            modifier = Modifier.background(themeGradient),
            topBar = {
                TopBarTitle(screen = ScreenType.Dashboard)
            },
            bottomBar = {
                DashBottomBar({}, {}, {})
            },
            content = {
                Column(modifier = Modifier.padding(it)) {
                    ProgressBar(
                        goal = 100.0f,
                        current = 80.0f,
                        goalText = stringResource(id = goalString(80.0f.div(100))),
                        onClick = { /* preview only */ }
                    )
                }
            }
        )
    }
}