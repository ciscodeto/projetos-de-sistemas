package com.ciscodeto.sinapsia.infrastructure

import androidx.room.RoomDatabase
import androidx.room.Room
import platform.Foundation.NSFileManager
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSUserDomainMask
import kotlinx.cinterop.ExperimentalForeignApi

fun getIosDatabaseBuilder(): RoomDatabase.Builder<SinapsiaDatabase> {
    val dbFilePath = documentDirectory() + "/sinapsia.db"
    return Room.databaseBuilder<SinapsiaDatabase>(
        name = dbFilePath,
    )
}

@OptIn(ExperimentalForeignApi::class)
private fun documentDirectory(): String {
    val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null,
    )
    return requireNotNull(documentDirectory?.path)
}
