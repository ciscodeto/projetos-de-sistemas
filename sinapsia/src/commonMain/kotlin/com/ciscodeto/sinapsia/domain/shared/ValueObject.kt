package com.ciscodeto.domain.shared

abstract class ValueObject {
    protected abstract fun validate() : Notification
}