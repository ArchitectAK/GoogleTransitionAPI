package com.cogitator.googletransitionapi.model.data.room

import android.arch.persistence.room.*
import com.cogitator.googletransitionapi.model.models.TransitionEvent
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 27/06/2018 (MM/DD/YYYY)
 */
@Dao
interface TransitionDao {

    /**
     * Get all transition data from the table.
     *
     * @return          the list of transitions retrieved from the table
     */
    @Query("SELECT * FROM transitions")
    fun getAllEvents(): Flowable<List<TransitionEvent>>

    /**
     * Get all transition data from the table in reversed chronological order.
     *
     * @return          the list of transitions retrieved from the table
     */
    @Query("SELECT * FROM transitions ORDER BY eventTimeMillis DESC")
    fun getAllEventsReversed(): Flowable<List<TransitionEvent>>

    /**
     * Get data of a specific transition from the table.
     *
     * @param id        id of the transition to be deleted
     * @return          the transition data from the table
     */
    @Query("SELECT * FROM transitions WHERE id=:id LIMIT 1")
    fun getEventById(id: Long): Single<TransitionEvent>

    /**
     * Get data of a last transition from the table
     *
     * @return          the transition data from the table
     */
    @Query("SELECT * FROM transitions ORDER BY eventTimeMillis DESC LIMIT 1")
    fun getLastEvent(): Flowable<List<TransitionEvent>>

    /**
     * Insert list of transitions to the database.
     * If the transition data already exists, ignore transaction.
     *
     * @param events     the lis of transitions to be inserted.
     * @return          list of id's of inserted drivers
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertEvents(events: List<TransitionEvent>): LongArray

    /**
     * Insert a transition to the database.
     * If the transition data already exists, ignore transaction.
     *
     * @param event      the transition to be inserted.
     * @return          list of id's of inserted drivers
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertEvent(vararg event: TransitionEvent): LongArray

    /**
     * Insert list of transitions to the database.
     * If the transition data already exists, replace it.
     *
     * @param events     the lis of transitions to be inserted.
     * @return          list of id's of upserted drivers
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertEvents(events: List<TransitionEvent>): LongArray

    /**
     * Insert a transition to the database.
     * If the transition data already exists, replace it.
     *
     * @param event      the transition to be inserted.
     * @return          list of id's of upserted drivers
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertEvent(vararg event: TransitionEvent): LongArray

    /**
     * Delete all transition data.
     *
     * @return          the number of rows removed from the DB
     */
    @Query("DELETE FROM transitions")
    fun deleteAllEvents(): Int

    /**
     * Delete specific transition data.
     *
     * @param event      the transition to be deleted
     * @return          the number of rows removed from the DB
     */
    @Delete
    fun deleteEvent(vararg event: TransitionEvent): Int
}