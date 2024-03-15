package kurmakaeva.anastasia.domain.irepository

import kurmakaeva.anastasia.domain.entities.GoalDataEntity

interface GoalRepository {
    suspend fun getGoalData(): GoalDataEntity
    suspend fun addGoalData(goalData: GoalDataEntity)
    suspend fun updateCurrent(goalData: GoalDataEntity)
    suspend fun resetCurrent(goalData: GoalDataEntity)
    suspend fun resetAll(goalData: GoalDataEntity)
}