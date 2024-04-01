package kurmakaeva.anastasia.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kurmakaeva.anastasia.domain.entities.InputEntity
import kurmakaeva.anastasia.domain.irepository.SavedRepository
import kurmakaeva.anastasia.domain.entities.SavedEntity
import kurmakaeva.anastasia.domain.irepository.InputRepository
import java.util.Calendar
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class SavedItemsViewModel @Inject constructor(
    private val savedRepository: SavedRepository,
    private val inputRepository: InputRepository
): ViewModel() {

    var savedItems by mutableStateOf<List<SavedEntity>>(emptyList())
        private set

    init {
        getAllSaved()
    }

    private fun getAllSaved() {
        viewModelScope.launch {
            kotlin.runCatching {
                savedRepository.getAllSaved().collect {
                    savedItems = it
                }
            }.onFailure {
                Log.i("RoomDb failed", it.message.orEmpty())
            }
        }
    }

    fun deleteSavedItem(id: Long) {
        viewModelScope.launch {
            try {
                savedRepository.deleteSaved(id)
            } catch (e: Exception) {
                e.localizedMessage
            }
        }
    }

    fun addSavedItemToInput(index: Int) {
        viewModelScope.launch {
            inputRepository.addInput(
                InputEntity(
                    id = Random.nextLong(),
                    input = savedItems[index].grams,
                    time = Calendar.getInstance().time.toString()
                )
            )
        }
    }
}