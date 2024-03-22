package kurmakaeva.anastasia.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import kurmakaeva.anastasia.data.db.model.GoalData

@Dao
interface GoalDao {
    @Query("SELECT * FROM goal_table")
    fun getGoalData(): Flow<GoalData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGoalData(goalData: GoalData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCurrent(goalData: GoalData)

    @Query("DELETE FROM goal_table where current = :current")
    suspend fun resetCurrent(vararg current: Float)

    @Delete
    suspend fun resetAll(goalData: GoalData)
}