package kurmakaeva.anastasia.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kurmakaeva.anastasia.domain.irepository.SavedRepository
import kurmakaeva.anastasia.domain.entities.SavedEntity
import javax.inject.Inject

@HiltViewModel
class SavedItemsViewModel @Inject constructor(
    private val savedRepository: SavedRepository
): ViewModel() {

    var savedItems by mutableStateOf<List<SavedEntity>>(emptyList())
        private set

    init {
        getAllSaved()
    }

    private fun getAllSaved() {
        viewModelScope.launch {
            savedItems = savedRepository.getAllSaved()
        }
    }

    fun deleteSavedItem(index: Int) {
        viewModelScope.launch {
            try {
                savedRepository.deleteSaved(savedItems[index].id)
            } catch (e: Exception) {
                e.localizedMessage
            }
        }
    }
}