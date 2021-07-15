package com.roquebuarque.smartstocks.stocks

import com.nhaarman.mockito_kotlin.given

/**
 * Stubs a mocked object's returning method with a value
 */
infix fun <T> T?.willReturn(value: T) {
    // `T?` instead of simply `T` allows to use `willReturn` with Java functions
    // which are marked with @NonNull annotations. If we use `T`, Kotlin will
    // refuse to even step inside this function, claiming that function receiver has to be non-null.
    given(this)
        .willReturn(value)
}