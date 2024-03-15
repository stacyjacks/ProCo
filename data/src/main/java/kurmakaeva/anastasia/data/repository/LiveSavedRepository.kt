package kurmakaeva.anastasia.data.repository

import dagger.hilt.android.scopes.ViewModelScoped
import kurmakaeva.anastasia.data.db.SavedDao
import kurmakaeva.anastasia.domain.SavedRepository
import kurmakaeva.anastasia.domain.entities.SavedEntity
import javax.inject.Inject

@ViewModelScoped
class LiveSavedRepository @Inject constructor(
    private val savedDao: SavedDao
): SavedRepository {
    override suspend fun getAllSaved(): List<SavedEntity> {
        return savedDao.getAllSaved().map { it.toEntity() }
    }

    override suspend fun addSaved(saved: SavedEntity) {
        savedDao.addSaved(saved.toDao())
    }

    override suspend fun deleteSaved(savedId: Long) {
        savedDao.deleteSavedById(savedId)
    }
}