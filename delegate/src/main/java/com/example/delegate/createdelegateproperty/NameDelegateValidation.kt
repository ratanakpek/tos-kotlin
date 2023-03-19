package com.example.delegate.createdelegateproperty

import kotlin.reflect.KProperty

class NameDelegateValidation {
    var formatValue: String? = null
    operator fun setValue(thisRef: Any?, properties: KProperty<*>, value: String?) {
        if (value != null && value.length >= 3) {
            formatValue = value.trim().toUpperCase()
        }
    }

    operator fun getValue(thisRef: Any?, properties: KProperty<*>): String? {
        return formatValue
    }
}