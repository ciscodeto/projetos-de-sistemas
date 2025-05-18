package com.ciscodeto.app4reinos

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform