package kurmakaeva.anastasia.domain.irepository

import kotlinx.coroutines.flow.Flow
import kurmakaeva.anastasia.domain.entities.GoalDataEntity

interface GoalRepository {
    fun getGoalData(): Flow<GoalDataEntity>
    suspend fun addGoalData(goalData: GoalDataEntity)
    suspend fun updateCurrent(goalData: GoalDataEntity)
    suspend fun resetCurrent(goalData: GoalDataEntity)
    suspend fun resetAll(goalData: GoalDataEntity)
}