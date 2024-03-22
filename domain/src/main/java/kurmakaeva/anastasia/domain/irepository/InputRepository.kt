package kurmakaeva.anastasia.domain.irepository

import kotlinx.coroutines.flow.Flow
import kurmakaeva.anastasia.domain.entities.InputEntity

interface InputRepository {
    fun getAllInput(): Flow<List<InputEntity>>
    suspend fun addInput(input: InputEntity)
    suspend fun deleteInput(inputId: Long)
    suspend fun resetAllInput(inputList: List<InputEntity>)
}