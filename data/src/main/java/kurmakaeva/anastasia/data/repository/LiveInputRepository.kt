package kurmakaeva.anastasia.data.repository

import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kurmakaeva.anastasia.data.db.dao.InputDao
import kurmakaeva.anastasia.domain.irepository.InputRepository
import kurmakaeva.anastasia.domain.entities.InputEntity
import javax.inject.Inject

@ViewModelScoped
class LiveInputRepository @Inject constructor(
    private val inputDao: InputDao
): InputRepository {
    override fun getAllInput(): Flow<List<InputEntity>> {
        return inputDao.getAllInput().map { input ->
            input.map { it.toEntity() }
        }
    }

    override suspend fun addInput(input: InputEntity) {
        inputDao.addInput(input.toDao())
    }

    override suspend fun deleteInput(inputId: Long) {
        TODO("Not yet implemented")
    }

    override suspend fun resetAllInput(inputList: List<InputEntity>) {
        inputDao.resetAllInput(inputList.map { it.toDao() })
    }

}