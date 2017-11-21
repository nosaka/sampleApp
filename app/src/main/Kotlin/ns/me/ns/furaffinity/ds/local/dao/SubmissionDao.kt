package ns.me.ns.furaffinity.ds.local.dao

import android.arch.persistence.room.*
import io.reactivex.Maybe
import io.reactivex.Single
import ns.me.ns.furaffinity.ds.local.model.Submission

/**
 *
 */
@Dao
interface SubmissionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(value: Submission)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg value: Submission)

    @Update
    fun update(value: Submission)

    @Delete
    fun delete(value: Submission)

    @Query("DELETE FROM Submission WHERE VIEW_ID = :viewId")
    fun delete(viewId: Int)

    @Query("DELETE FROM Submission")
    fun deleteAll()

    @Query("SELECT * FROM Submission WHERE VIEW_ID = :viewId")
    fun find(viewId: Int): Maybe<Submission>

    @Query("SELECT * FROM Submission ORDER BY VIEW_ID DESC")
    fun all(): Single<List<Submission>>

    @Query("SELECT * FROM Submission WHERE VIEW_ID < :viewId ORDER BY VIEW_ID DESC")
    fun allThanViewId(viewId: Int): Single<List<Submission>>


}