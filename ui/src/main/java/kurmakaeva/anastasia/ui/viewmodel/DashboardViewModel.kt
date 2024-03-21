package kurmakaeva.anastasia.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kurmakaeva.anastasia.domain.entities.GoalDataEntity
import kurmakaeva.anastasia.domain.entities.InputEntity
import kurmakaeva.anastasia.domain.irepository.GoalRepository
import kurmakaeva.anastasia.domain.irepository.InputRepository
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val goalRepository: GoalRepository,
    private val inputRepository: InputRepository
): ViewModel() {
    var goal by mutableStateOf(0.0f)
        private set

    var current by mutableStateOf(0.0f)
        private set

    var input by mutableStateOf<List<InputEntity>>(emptyList())
        private set

    init {
        getGoalData()
        getCurrentInput()
    }

    private fun getGoalData() {
        viewModelScope.launch {
            kotlin.runCatching {
                goalRepository.getGoalData()
            }.onSuccess {
                goal = it.goal
                current = it.current
            }.onFailure {
                Log.i("RoomDb failed", it.message.orEmpty())
            }
        }
    }

    private fun getCurrentInput() {
        viewModelScope.launch {
            kotlin.runCatching {
                inputRepository.getAllInput()
            }.onSuccess {
                input = it
                it.forEach { input ->
                    current = current.plus(input.input)
                }
            }.onFailure {
                Log.i("RoomDb failed", it.message.orEmpty())
            }
        }
    }

    fun resetDailyData() {
        viewModelScope.launch {
            inputRepository.resetAllInput(input)
            goalRepository.resetCurrent(GoalDataEntity(current, goal))
        }
    }
}