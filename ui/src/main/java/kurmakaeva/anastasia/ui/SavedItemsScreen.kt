package kurmakaeva.anastasia.ui

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kurmakaeva.anastasia.domain.entities.SavedEntity
import kurmakaeva.anastasia.ui.components.BottomTabBar
import kurmakaeva.anastasia.ui.components.ItemWithSwipeToDelete
import kurmakaeva.anastasia.ui.components.TopBarTitle
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
            bottomBar = {
                BottomTabBar(
                    items = listOf(stringResource(id = R.string.addPresetTitle)),
                    icons = listOf(Icons.Default.Add),
                    actions = listOf { onNavigateToAdd() }
                )
            },
            content = {
                SavedList(
                    list = viewModel.savedItems,
                    viewModel = viewModel,
                    paddingValues = it
                )

                if (viewModel.savedItems.isEmpty()) {
                    Text(
                        text = stringResource(id = R.string.savedEmptyState),
                        modifier = Modifier
                            .padding(it)
                            .padding(horizontal = 32.dp, vertical = 64.dp)
                    )
                }
            }
        )
    }
}

@Composable
fun SavedList(list: List<SavedEntity>, viewModel: SavedItemsViewModel, paddingValues: PaddingValues) {
    LazyColumn(
        state = rememberLazyListState(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
    ) {
        items(list.size, key = { list[it].id }) { index ->
            ItemWithSwipeToDelete(
                item = list[index],
                onDelete = {
                    viewModel.deleteSavedItem(list[index].id)
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
                                .clickable { viewModel.addSavedItemToInput(index) }
                        )
                    }
                }
            }
        }
    }
}