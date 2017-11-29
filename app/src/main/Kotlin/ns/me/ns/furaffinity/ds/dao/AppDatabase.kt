package ns.me.ns.furaffinity.ds.dao

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import ns.me.ns.furaffinity.repository.model.local.Favorite
import ns.me.ns.furaffinity.repository.model.local.Gallery
import ns.me.ns.furaffinity.repository.model.local.Submission

/**
 *
 */
@Database(entities = arrayOf(Submission::class, Favorite::class, Gallery::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {

        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "app.db")
                        .allowMainThreadQueries()
                        .build()
            }
            return instance!!
        }

        fun destroyInstance() {
            instance = null
        }

    }

    abstract fun favoriteDao(): FavoriteDao

    abstract fun submissionDao(): SubmissionDao

    abstract fun galleryDao(): GalleryDao
}