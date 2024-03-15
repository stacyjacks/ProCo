package kurmakaeva.anastasia.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Saved::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun savedDao(): SavedDao
}