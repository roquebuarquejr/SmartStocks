package com.roquebuarque.smartstocks.stocks

import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import org.mockito.Mockito

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

/**
 * Verifies that the given `verification` was called once.
 */
infix fun <S : Any> S.verifyOnce(verification: S.() -> Unit) {
    verify(this).verification()
}

/**
 * Verifies that the given `verification` was called `count` times.
 */
fun <S : Any> S.verify(count: Int, verification: S.() -> Unit) {
    verify(this, Mockito.times(count)).verification()
}
