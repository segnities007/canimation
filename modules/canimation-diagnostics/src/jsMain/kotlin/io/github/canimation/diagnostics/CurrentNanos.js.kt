package io.github.canimation.diagnostics

internal actual fun currentNanos(): Long =
    (kotlin.js.Date.now() * 1_000_000).toLong()
