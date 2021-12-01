package utils

import kotlin.properties.Delegates

class Timer {

    private var begin by Delegates.notNull<Long>()

    private fun now() = System.currentTimeMillis()

    fun start() {
        begin = now()
    }

    fun elapsed() = now() - begin
}