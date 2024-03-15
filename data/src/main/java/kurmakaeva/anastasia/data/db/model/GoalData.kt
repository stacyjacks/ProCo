package kurmakaeva.anastasia.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "goal_table")
data class GoalData(
    @PrimaryKey val current: Float,
    @ColumnInfo val goal: Float
)
