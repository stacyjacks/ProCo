package kurmakaeva.anastasia.ui.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kurmakaeva.anastasia.domain.entities.GoalDataEntity
import kurmakaeva.anastasia.domain.irepository.SavedRepository
import kurmakaeva.anastasia.domain.entities.SavedEntity
import kurmakaeva.anastasia.domain.irepository.GoalRepository
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val savedRepository: SavedRepository,
    private val goalRepository: GoalRepository
): ViewModel() {

    var savedItem by mutableStateOf(SavedEntity(0L, "", 0.0f))
        private set

    private var goal by mutableStateOf(GoalDataEntity(0.0f, 0.0f))

    fun addSavedItem() {
        viewModelScope.launch {
            savedRepository.addSaved(savedItem)
        }
    }

    fun addGoal() {
        viewModelScope.launch {
            goalRepository.addGoalData(goal)
        }
    }

    fun onAmountChanged(amount: Float) {
        goal = goal.copy(goal = amount)
        savedItem = savedItem.copy(grams = amount)
    }

    fun onNameChanged(name: String) {
        savedItem = savedItem.copy(name = name)
    }
}