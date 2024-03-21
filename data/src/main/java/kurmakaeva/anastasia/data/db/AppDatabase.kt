package kurmakaeva.anastasia.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import kurmakaeva.anastasia.data.db.dao.GoalDao
import kurmakaeva.anastasia.data.db.dao.InputDao
import kurmakaeva.anastasia.data.db.dao.SavedDao
import kurmakaeva.anastasia.data.db.model.GoalData
import kurmakaeva.anastasia.data.db.model.Input
import kurmakaeva.anastasia.data.db.model.Saved

@Database(entities = [Saved::class, Input::class, GoalData::class], version = 6, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun savedDao(): SavedDao
    abstract fun inputDao(): InputDao
    abstract fun goalDao(): GoalDao

    companion object {
        const val APP_DATABASE = "proco_database"
    }
}