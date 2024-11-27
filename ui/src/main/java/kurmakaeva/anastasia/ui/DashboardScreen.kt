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
import kurmakaeva.anastasia.domain.entities.InputEntity
import kurmakaeva.anastasia.ui.components.AlertDialog
import kurmakaeva.anastasia.ui.components.BottomTabBar
import kurmakaeva.anastasia.ui.components.ProgressBar
import kurmakaeva.anastasia.ui.components.TopBarTitle
import kurmakaeva.anastasia.ui.theme.ProCoTheme
import kurmakaeva.anastasia.ui.theme.smallPurpleBold
import kurmakaeva.anastasia.ui.viewmodel.DashboardViewModel

@Composable
fun DashboardScreen(
    onNavigateToSaved: () -> Unit,
    onNavigateToAdd: () -> Unit,
    onClickProgress: () -> Unit,
    viewModel: DashboardViewModel = hiltViewModel()
) {
    DashboardView(
        onNavigateToSaved = onNavigateToSaved,
        onNavigateToAdd = onNavigateToAdd,
        onClickProgress = onClickProgress,
        onResetDailyData = { viewModel.resetDailyData() },
        onDeleteEntry = { viewModel.deleteSingleEntry(viewModel.input[it].id) },
        goal = viewModel.goal,
        current = viewModel.current,
        input = viewModel.input
    )
}

@Composable
private fun DashboardView(
    onNavigateToSaved: () -> Unit,
    onNavigateToAdd: () -> Unit,
    onClickProgress: () -> Unit,
    onResetDailyData: () -> Unit,
    onDeleteEntry: (Int) -> Unit,
    goal: Float,
    current: Float,
    input: List<InputEntity>
) {
    Scaffold(
        modifier = Modifier.padding(bottom = 8.dp),
        topBar = {
            TopBarTitle(screen = ScreenType.Dashboard)
        },
        bottomBar = {
            DashBottomBar(
                onNavigateToSaved = onNavigateToSaved,
                onNavigateToAdd = onNavigateToAdd,
                resetDailyData = onResetDailyData
            )
        },
        content = { paddingValues ->
            val openDialog = rememberSaveable { mutableStateOf(false) }
            val selectedItem = rememberSaveable { mutableIntStateOf(0) }

            DeleteEntryDialog(
                onConfirm = {
                    onDeleteEntry(selectedItem.intValue)
                    openDialog.value = false
                },
                onDismiss = {
                    openDialog.value = false
                },
                openDialog = openDialog.value
            )

            Column(modifier = Modifier.padding(paddingValues)) {
                ProgressBar(
                    goal = goal,
                    current =
                    if (current > goal) goal
                    else current,
                    goalText = stringResource(
                        id = goalString(current.div(goal))
                    ),
                    onClick = { onClickProgress() }
                )
                LazyVerticalGrid(
                    columns = GridCells.FixedSize(80.dp),
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    items(input.size) { index ->
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
                                text = "${input[index].input} gr",
                                textAlign = TextAlign.Center,
                                style = smallPurpleBold
                            )
                        }
                    }
                }

                if (input.isEmpty()) {
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
private fun DeleteEntryDialog(
    openDialog: Boolean,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
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
private fun DashboardPreview() {
    ProCoTheme {
        DashboardView(
            onNavigateToSaved = {},
            onNavigateToAdd = {},
            onClickProgress = {},
            onResetDailyData = {},
            onDeleteEntry = {},
            goal = 100f,
            current = 50f,
            input = listOf()
        )
    }
}