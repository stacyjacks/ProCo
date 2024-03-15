package kurmakaeva.anastasia.data.repository

import kurmakaeva.anastasia.data.db.dao.InputDao
import kurmakaeva.anastasia.domain.irepository.InputRepository
import kurmakaeva.anastasia.domain.entities.InputEntity
import javax.inject.Inject

class LiveInputRepository @Inject constructor(
    private val inputDao: InputDao
): InputRepository {
    override suspend fun getAllInput(): List<InputEntity> {
        return inputDao.getAllInput().map { it.toEntity() }
    }

    override suspend fun addInput(input: InputEntity) {
        inputDao.addInput(input.toDao())
    }

    override suspend fun deleteInput(inputId: Long) {
        TODO("Not yet implemented")
    }

}