package kurmakaeva.anastasia.ui.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kurmakaeva.anastasia.domain.irepository.SavedRepository
import kurmakaeva.anastasia.domain.entities.SavedEntity
import javax.inject.Inject

@HiltViewModel
class AddSavedItemViewModel @Inject constructor(
    private val savedRepository: SavedRepository
): ViewModel() {

    var savedItem by mutableStateOf(SavedEntity(0L, "", 0.0f))
        private set

    fun addSavedItem() {
        viewModelScope.launch {
            savedRepository.addSaved(savedItem)
        }
    }

    fun onAmountChanged(amount: Float) {
        savedItem = savedItem.copy(grams = amount)
    }

    fun onNameChanged(name: String) {
        savedItem = savedItem.copy(name = name)
    }
}