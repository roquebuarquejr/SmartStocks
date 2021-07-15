package com.roquebuarque.smartstocks.stocks.domain.models

/**
 * Wrapper remote connection error
 *
 * @param message to display to the user
 */
class ConnectionFailedException(override val message :String): Throwable(message)