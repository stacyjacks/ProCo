package kurmakaeva.anastasia.data.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kurmakaeva.anastasia.data.db.model.GoalData
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Volatile
    private var INSTANCE: AppDatabase? = null

    @Provides
    @Singleton
    fun provideSavedDao(appDb: AppDatabase) = appDb.savedDao()

    @Provides
    @Singleton
    fun provideInputDao(appDb: AppDatabase) = appDb.inputDao()

    @Provides
    @Singleton
    fun provideGoalDao(appDb: AppDatabase) = appDb.goalDao()

    @Provides
    @Singleton
    fun provideAppDb(@ApplicationContext context: Context): AppDatabase {
        return INSTANCE ?: synchronized(this) {
            val scope = CoroutineScope(Dispatchers.IO)
            val instance = Room
                .databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    name = AppDatabase.APP_DATABASE
                )
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        scope.launch {
                            INSTANCE?.goalDao()?.addGoalData(
                                GoalData(current = 0.0f, goal = 0.0f)
                            )
                        }
                    }
                })
                .fallbackToDestructiveMigration()
                .build()
                .also { INSTANCE = it }
            instance
        }
    }
}