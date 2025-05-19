package com.ciscodeto.sinapsia.di

import android.content.Context
import androidx.room.RoomDatabase
import com.ciscodeto.sinapsia.infrastructure.SinapsiaDatabase
import com.ciscodeto.sinapsia.infrastructure.getAndroidDatabaseBuilder

private lateinit var appContext: Context

fun initSinapsiaDatabase(context: Context) {
    appContext = context.applicationContext
}

actual fun getDatabaseBuilder(): RoomDatabase.Builder<SinapsiaDatabase> {
    return getAndroidDatabaseBuilder(appContext)
}