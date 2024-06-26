package kurmakaeva.anastasia.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import kurmakaeva.anastasia.data.db.model.Input

@Dao
interface InputDao {
    @Query("SELECT * FROM input_table ORDER BY time ASC")
    fun getAllInput(): Flow<List<Input>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addInput(input: Input)

    @Query("DELETE FROM input_table where id = :inputId")
    suspend fun deleteInputById(vararg inputId: Long)

    @Delete
    suspend fun resetAllInput(inputList: List<Input>)
}