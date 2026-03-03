package io.github.canimation.core

import kotlin.js.Date

internal actual fun currentTimeMillis(): Long = Date.now().toLong()
