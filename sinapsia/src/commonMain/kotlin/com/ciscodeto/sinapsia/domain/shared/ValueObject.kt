package com.ciscodeto.sinapsia.domain.shared

import com.ciscodeto.sinapsia.domain.shared.Notification

abstract class ValueObject {
    protected abstract fun validate() : Notification
}