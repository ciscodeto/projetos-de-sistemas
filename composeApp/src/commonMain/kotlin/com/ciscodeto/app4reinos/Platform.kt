package com.ciscodeto.managerapp4reinos

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform