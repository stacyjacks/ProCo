package kurmakaeva.anastasia.data.repository

import kurmakaeva.anastasia.data.db.dao.GoalDao
import kurmakaeva.anastasia.domain.irepository.GoalRepository
import kurmakaeva.anastasia.domain.entities.GoalDataEntity
import javax.inject.Inject

class LiveGoalRepository @Inject constructor(
    private val goalDao: GoalDao
): GoalRepository {
    override suspend fun getGoalData(): GoalDataEntity {
        return goalDao.getGoalData().toEntity()
    }

    override suspend fun addGoalData(goalData: GoalDataEntity) {
        goalDao.addGoalData(goalData.toDao())
    }

    override suspend fun updateCurrent(goalData: GoalDataEntity) {
        goalDao.updateCurrent(goalData.toDao())
    }

    override suspend fun resetCurrent(goalData: GoalDataEntity) {
        goalDao.resetCurrent(goalData.current)
    }

    override suspend fun resetAll(goalData: GoalDataEntity) {
        goalDao.resetAll(goalData.toDao())
    }

}