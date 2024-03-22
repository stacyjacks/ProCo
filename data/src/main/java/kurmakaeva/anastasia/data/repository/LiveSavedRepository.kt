package kurmakaeva.anastasia.data.repository

import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kurmakaeva.anastasia.data.db.dao.SavedDao
import kurmakaeva.anastasia.domain.irepository.SavedRepository
import kurmakaeva.anastasia.domain.entities.SavedEntity
import javax.inject.Inject

@ViewModelScoped
class LiveSavedRepository @Inject constructor(
    private val savedDao: SavedDao
): SavedRepository {
    override fun getAllSaved(): Flow<List<SavedEntity>> {
        return savedDao.getAllSaved().map { saved ->
            saved.map { it.toEntity() }
        }
    }

    override suspend fun addSaved(saved: SavedEntity) {
        savedDao.addSaved(saved.toDao())
    }

    override suspend fun deleteSaved(savedId: Long) {
        savedDao.deleteSavedById(savedId)
    }
}