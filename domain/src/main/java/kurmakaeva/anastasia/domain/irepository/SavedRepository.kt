package kurmakaeva.anastasia.domain.irepository

import kotlinx.coroutines.flow.Flow
import kurmakaeva.anastasia.domain.entities.SavedEntity

interface SavedRepository {
    fun getAllSaved(): Flow<List<SavedEntity>>
    suspend fun addSaved(saved: SavedEntity)
    suspend fun deleteSaved(savedId: Long)
}