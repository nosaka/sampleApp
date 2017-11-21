package ns.me.ns.furaffinity.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import ns.me.ns.furaffinity.ds.local.dao.AppDatabase
import ns.me.ns.furaffinity.ds.local.dao.FavoriteDao
import ns.me.ns.furaffinity.ds.local.dao.SubmissionDao
import javax.inject.Singleton

/**
 * Database Provider
 */
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase = AppDatabase.getDatabase(context)

    @Provides
    @Singleton
    fun provideFavoriteDao(database: AppDatabase): FavoriteDao = database.favoriteDao()

    @Provides
    @Singleton
    fun provideSubmissionDao(database: AppDatabase): SubmissionDao = database.submissionDao()

}