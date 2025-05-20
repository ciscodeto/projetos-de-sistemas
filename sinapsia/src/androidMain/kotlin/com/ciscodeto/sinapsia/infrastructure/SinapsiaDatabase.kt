package com.ciscodeto.sinapsia.infrastructure

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

fun getAndroidDatabaseBuilder(ctx: Context): RoomDatabase.Builder<SinapsiaDatabase> {
    val dbFile = ctx.applicationContext.getDatabasePath("sinapsia.db")
    return Room.databaseBuilder<SinapsiaDatabase>(
        context = ctx,
        name = dbFile.absolutePath
    )
}