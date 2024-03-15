package kurmakaeva.anastasia.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kurmakaeva.anastasia.data.db.model.Input

@Dao
interface InputDao {
    @Query("SELECT * FROM input_table ORDER BY time ASC")
    suspend fun getAllInput(): List<Input>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addInput(input: Input)

    @Query("DELETE FROM input_table where id = :inputId")
    suspend fun deleteInputById(vararg inputId: Long)
}