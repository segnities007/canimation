package io.github.canimation.diagnostics

private fun jsDateNow(): JsNumber = js("Date.now()")

internal actual fun currentNanos(): Long = (jsDateNow().toDouble() * 1_000_000).toLong()
