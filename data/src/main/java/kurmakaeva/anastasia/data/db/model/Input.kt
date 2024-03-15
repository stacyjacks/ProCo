package kurmakaeva.anastasia.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "input_table")
data class Input(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo val input: Float,
    @ColumnInfo val time: String
)
