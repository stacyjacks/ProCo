package kurmakaeva.anastasia.data.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kurmakaeva.anastasia.domain.SavedRepository

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindSavedRepository(impl: LiveSavedRepository): SavedRepository
}