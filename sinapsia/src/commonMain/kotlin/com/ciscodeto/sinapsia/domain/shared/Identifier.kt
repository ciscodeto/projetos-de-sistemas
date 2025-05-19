package com.ciscodeto.sinapsia.domain.shared

interface Identifier <T>{
    fun validate() : Notification
    fun value() : T
}