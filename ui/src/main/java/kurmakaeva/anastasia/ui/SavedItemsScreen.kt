package kurmakaeva.anastasia.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kurmakaeva.anastasia.domain.entities.SavedEntity
import kurmakaeva.anastasia.ui.components.ItemWithSwipeToDelete
import kurmakaeva.anastasia.ui.components.TopBarTitle
import kurmakaeva.anastasia.ui.theme.Purple40
import kurmakaeva.anastasia.ui.theme.Typography
import kurmakaeva.anastasia.ui.viewmodel.SavedItemsViewModel

@Composable
fun SavedItemsScreen(
    onNavigateToAdd: () -> Unit,
    viewModel: SavedItemsViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Scaffold(
            topBar = { TopBarTitle(screen = ScreenType.Saved) },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { onNavigateToAdd() },
                    shape = CircleShape,
                    elevation = FloatingActionButtonDefaults.elevation(12.dp),
                    containerColor = Purple40,
                    contentColor = Color.White,
                    content = {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "")
                    }
                )
            },
            floatingActionButtonPosition = FabPosition.End,
            content = { paddingValues ->
                SavedList(
                    list = viewModel.savedItems,
                    onDelete = { viewModel.deleteSavedItem(it) },
                    onAddSavedItemToInput = { viewModel.addSavedItemToInput(it) },
                    paddingValues = paddingValues
                )

                if (viewModel.savedItems.isEmpty()) {
                    Text(
                        text = stringResource(id = R.string.savedEmptyState),
                        modifier = Modifier
                            .padding(paddingValues)
                            .padding(horizontal = 32.dp, vertical = 64.dp)
                    )
                }
            }
        )
    }
}

@Composable
fun SavedList(
    list: List<SavedEntity>,
    onDelete: (Long) -> Unit,
    onAddSavedItemToInput: (Int) -> Unit,
    paddingValues: PaddingValues
) {
    LazyColumn(
        state = rememberLazyListState(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
    ) {
        items(list.size, key = { list[it].id }) { index ->
            val context = LocalContext.current

            ItemWithSwipeToDelete(
                item = list[index],
                onDelete = {
                    onDelete(it.id)
                }
            ) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = it.name,
                                style = Typography.bodySmall
                            )
                            Text(
                                text = it.grams.toString() + stringResource(id = R.string.grams),
                                style = Typography.bodyLarge
                            )
                        }
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = stringResource(id = R.string.add),
                            modifier = Modifier
                                .background(Color.Transparent)
                                .padding(4.dp)
                                .clickable {
                                    onAddSavedItemToInput(index)
                                    Toast
                                        .makeText(
                                            context,
                                            R.string.toastAddedMessage,
                                            Toast.LENGTH_SHORT
                                        )
                                        .show()
                                }
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewSavedScreen() {
    SavedList(
        list = listOf(
            SavedEntity(
                id = 0,
                name = "Protein shake",
                grams = 30.0f
            ),
            SavedEntity(
                id = 1,
                name = "Burger",
                grams = 15.0f
            )
        ),
        onDelete = {},
        onAddSavedItemToInput = {},
        paddingValues = PaddingValues()
    )
}