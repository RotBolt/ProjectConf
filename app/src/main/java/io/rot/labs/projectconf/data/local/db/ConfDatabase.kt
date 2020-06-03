package io.rot.labs.projectconf.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.rot.labs.projectconf.data.local.db.dao.EventsDao
import io.rot.labs.projectconf.data.local.db.entity.EventEntity
import io.rot.labs.projectconf.data.model.Event
import javax.inject.Singleton


@Singleton
@Database(
    entities = [EventEntity::class],
    exportSchema = false,
    version = 1
)
@TypeConverters(DateConverters::class)
abstract class ConfDatabase : RoomDatabase() {

    abstract fun eventsDao(): EventsDao

}