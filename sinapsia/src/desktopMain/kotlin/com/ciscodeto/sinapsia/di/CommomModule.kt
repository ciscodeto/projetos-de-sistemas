package com.ciscodeto.sinapsia.di

import androidx.room.RoomDatabase
import com.ciscodeto.sinapsia.infrastructure.SinapsiaDatabase
import com.ciscodeto.sinapsia.infrastructure.getDesktopDatabaseBuilder
import org.koin.dsl.module

actual fun getDatabaseBuilder(): RoomDatabase.Builder<SinapsiaDatabase> {
    return getDesktopDatabaseBuilder()
}