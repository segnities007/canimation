@file:OptIn(kotlin.js.ExperimentalWasmJsInterop::class)

package io.github.canimation.core

private fun jsDateNow(): JsNumber = js("Date.now()")

internal actual fun currentTimeMillis(): Long = jsDateNow().toDouble().toLong()
