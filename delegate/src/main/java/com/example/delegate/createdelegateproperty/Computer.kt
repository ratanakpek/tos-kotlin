package com.example.delegate.createdelegateproperty


class Computer {
    //Duplication code, so we can use delegation property to replace this
    var id: String? = null
        set(value) {
            if (value != null && value.length >= 3) {
                field = value.trim().toUpperCase()
            }
        }

    //Duplication
    var name: String? = null
        set(value) {
            if (value != null && value.length >= 3) {
                field = value.trim().toUpperCase()
            }
        }


    var type: String? by NameDelegateValidation()
}

