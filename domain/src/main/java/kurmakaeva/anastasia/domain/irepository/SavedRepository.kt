package kurmakaeva.anastasia.domain.irepository

import kurmakaeva.anastasia.domain.entities.SavedEntity

interface SavedRepository {
    suspend fun getAllSaved(): List<SavedEntity>
    suspend fun addSaved(saved: SavedEntity)
    suspend fun deleteSaved(savedId: Long)
}