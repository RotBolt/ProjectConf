/*
 *  Created by Rohan Maity on 11/1/21 3:19 PM
 *  Copyright (c) 2021 . All rights reserved.
 *  Last modified 11/1/21 3:12 PM
 */

package io.rot.labs.projectconf.data.local.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import io.rot.labs.projectconf.data.local.db.entity.BookmarkedEvent
import java.util.Date

@Dao
interface BookmarksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(bookmarkedEvent: BookmarkedEvent): Completable

    @Update
    fun update(bookmarkedEvent: BookmarkedEvent): Completable

    @Delete
    fun remove(bookmarkedEvent: BookmarkedEvent): Completable

    @Query("SELECT * FROM bookmarked_events ORDER BY startDate")
    fun getAllBookmarkedEvents(): Single<List<BookmarkedEvent>>

    @Query("SELECT * FROM bookmarked_events WHERE cfp_reminder_enabled = 1 ORDER BY startDate")
    fun getCFPReminderEvents(): Single<List<BookmarkedEvent>>

    @Query("SELECT * FROM bookmarked_events WHERE name = :name AND startDate = :startDate AND topic = :topic LIMIT 1")
    fun getBookmarkedEvent(name: String, startDate: Date, topic: String): Maybe<BookmarkedEvent>
}
