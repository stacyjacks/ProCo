package kurmakaeva.anastasia.domain.irepository

import kurmakaeva.anastasia.domain.entities.InputEntity

interface InputRepository {
    suspend fun getAllInput(): List<InputEntity>
    suspend fun addInput(input: InputEntity)
    suspend fun deleteInput(inputId: Long)
}