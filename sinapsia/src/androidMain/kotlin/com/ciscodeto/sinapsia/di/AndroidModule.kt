package com.ciscodeto.sinapsia.di

import androidx.room.Room
import androidx.room.RoomDatabase
import com.ciscodeto.sinapsia.infrastructure.SinapsiaDatabase
import com.ciscodeto.sinapsia.infrastructure.getAndroidDatabaseBuilder
import com.ciscodeto.sinapsia.infrastructure.getDatabaseBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual fun getDatabaseBuilder(): RoomDatabase.Builder<SinapsiaDatabase> {
    return getAndroidDatabaseBuilder()
}