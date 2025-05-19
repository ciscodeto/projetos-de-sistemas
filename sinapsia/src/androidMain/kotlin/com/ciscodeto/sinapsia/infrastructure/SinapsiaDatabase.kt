package com.ciscodeto.sinapsia.infrastructure

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

fun getAndroidDatabaseBuilder(ctx: Context): RoomDatabase.Builder<SinapsiaDatabase> {
    val appContext = ctx.applicationContext
    val dbFile = appContext.getDatabasePath("sinapsia.db")
    return Room.databaseBuilder<SinapsiaDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}