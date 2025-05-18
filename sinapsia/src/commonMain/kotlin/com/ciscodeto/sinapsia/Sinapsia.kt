package com.ciscodeto.sinapsia

import com.ciscodeto.sinapsia.di.sinapsiaModule
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

object Sinapsia {
    private lateinit var koinApp: KoinApplication

    fun start() {
        koinApp = startKoin {
            modules(sinapsiaModule)
        }
    }

    private inline fun <reified T> get(): T = koinApp.koin.get()
}
