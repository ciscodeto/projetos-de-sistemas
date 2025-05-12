package com.ciscodeto.domain.shared

interface Identifier <T>{
    fun validate() : Notification
    fun value() : T
}