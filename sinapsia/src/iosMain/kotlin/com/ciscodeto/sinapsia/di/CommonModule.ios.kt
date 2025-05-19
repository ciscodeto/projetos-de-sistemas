package com.ciscodeto.sinapsia.di

import androidx.room.RoomDatabase
import com.ciscodeto.sinapsia.infrastructure.SinapsiaDatabase
import com.ciscodeto.sinapsia.infrastructure.getIosDatabaseBuilder

actual fun getDatabaseBuilder(): RoomDatabase.Builder<SinapsiaDatabase> {
    return getIosDatabaseBuilder()
}