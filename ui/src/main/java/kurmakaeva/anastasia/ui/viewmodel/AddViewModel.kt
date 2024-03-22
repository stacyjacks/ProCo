package kurmakaeva.anastasia.ui.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kurmakaeva.anastasia.domain.entities.GoalDataEntity
import kurmakaeva.anastasia.domain.entities.InputEntity
import kurmakaeva.anastasia.domain.irepository.SavedRepository
import kurmakaeva.anastasia.domain.entities.SavedEntity
import kurmakaeva.anastasia.domain.irepository.GoalRepository
import kurmakaeva.anastasia.domain.irepository.InputRepository
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val savedRepository: SavedRepository,
    private val goalRepository: GoalRepository,
    private val inputRepository: InputRepository
): ViewModel() {

    var savedItem by mutableStateOf(SavedEntity(0L, "", 0.0f))
        private set

    var input by mutableStateOf(InputEntity(0L, 0.0f, ""))
        private set

    var goal by mutableStateOf(GoalDataEntity(0.0f, 0.0f))

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

    fun addInput() {
        viewModelScope.launch {
            inputRepository.addInput(input)
        }
    }

    fun onGoalAmountChanged(amount: Float) {
        goal = goal.copy(goal = amount)
    }

    fun onSavedAmountChanged(amount: Float) {
        savedItem = savedItem.copy(grams = amount)
    }

    fun onInputAmountChanged(amount: Float) {
        input = input.copy(
            input = amount,
            time = Calendar.getInstance().time.toString()
        )
    }

    fun onNameChanged(name: String) {
        savedItem = savedItem.copy(name = name)
    }
}