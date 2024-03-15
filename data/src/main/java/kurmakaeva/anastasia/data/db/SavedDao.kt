package kurmakaeva.anastasia.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import java.util.*

@Dao
interface SavedDao {
    @Query("SELECT * FROM saved_table ORDER BY name ASC")
    suspend fun getAllSaved(): List<Saved>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSaved(saved: Saved)

    @Query("DELETE FROM saved_table where id = :savedId")
    suspend fun deleteSavedById(vararg savedId: Long)
}