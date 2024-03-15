package kurmakaeva.anastasia.data.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kurmakaeva.anastasia.domain.irepository.GoalRepository
import kurmakaeva.anastasia.domain.irepository.InputRepository
import kurmakaeva.anastasia.domain.irepository.SavedRepository

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindSavedRepository(impl: LiveSavedRepository): SavedRepository

    @Binds
    abstract fun bindInputRepository(impl: LiveInputRepository): InputRepository

    @Binds
    abstract fun bindGoalRepository(impl: LiveGoalRepository): GoalRepository
}