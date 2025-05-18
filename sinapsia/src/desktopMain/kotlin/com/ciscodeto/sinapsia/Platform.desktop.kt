package com.ciscodeto.sinapsia

actual fun platform(): String = "Java ${System.getProperty("java.version")}"