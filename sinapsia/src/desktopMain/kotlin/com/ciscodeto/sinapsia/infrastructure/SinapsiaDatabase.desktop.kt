package com.ciscodeto.sinapsia.infrastructure

import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File

fun getDesktopDatabaseBuilder(): RoomDatabase.Builder<SinapsiaDatabase> {
    val dbFile = File(System.getProperty("java.io.tmpdir"), "sinapsia.db")
    return Room.databaseBuilder<SinapsiaDatabase>(
        name = dbFile.absolutePath,
    )
}