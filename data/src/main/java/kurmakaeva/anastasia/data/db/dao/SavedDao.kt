package kurmakaeva.anastasia.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import kurmakaeva.anastasia.data.db.model.Saved
import java.util.*

@Dao
interface SavedDao {
    @Query("SELECT * FROM saved_table ORDER BY name ASC")
    fun getAllSaved(): Flow<List<Saved>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSaved(saved: Saved)

    @Query("DELETE FROM saved_table where id = :savedId")
    suspend fun deleteSavedById(vararg savedId: Long)
}