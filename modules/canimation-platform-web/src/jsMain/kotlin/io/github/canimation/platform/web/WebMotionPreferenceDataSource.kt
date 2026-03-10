package io.github.canimation.platform.web

import io.github.canimation.a11y.MotionPreferenceDataSource
import io.github.canimation.a11y.SystemMotionPreference
import kotlinx.browser.window
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import org.w3c.dom.MediaQueryList
import org.w3c.dom.events.Event

/**
 * Web (JS) implementation of [MotionPreferenceDataSource].
 *
 * Uses the `prefers-reduced-motion` CSS media query to detect the user's preference.
 */
class WebMotionPreferenceDataSource internal constructor(
    private val mediaQueryProvider: () -> MotionMediaQueryHandle?,
) : MotionPreferenceDataSource {
    constructor() : this(::defaultMotionMediaQueryHandle)

    override fun currentPreference(): SystemMotionPreference = readPreference(mediaQueryProvider())

    override fun observePreference(): Flow<SystemMotionPreference> = callbackFlow {
        val mediaQuery = mediaQueryProvider()
        if (mediaQuery == null) {
            trySend(SystemMotionPreference.ReduceMotion)
            close()
            return@callbackFlow
        }

        val listener: ChangeListener = {
            trySend(readPreference(mediaQuery))
        }

        trySend(readPreference(mediaQuery))
        mediaQuery.addChangeListener(listener)
        awaitClose {
            mediaQuery.removeChangeListener(listener)
        }
    }

    private fun readPreference(mediaQuery: MotionMediaQueryHandle?): SystemMotionPreference {
        if (mediaQuery == null) return SystemMotionPreference.ReduceMotion
        return if (mediaQuery.matches) {
            SystemMotionPreference.ReduceMotion
        } else {
            SystemMotionPreference.NoPreference
        }
    }
}

internal typealias ChangeListener = () -> Unit

internal interface MotionMediaQueryHandle {
    val matches: Boolean

    fun addChangeListener(listener: ChangeListener)

    fun removeChangeListener(listener: ChangeListener)
}

private class BrowserMotionMediaQueryHandle(
    private val mediaQueryList: MediaQueryList,
) : MotionMediaQueryHandle {
    private val listeners = mutableMapOf<ChangeListener, (Event) -> Unit>()

    override val matches: Boolean
        get() = mediaQueryList.matches

    override fun addChangeListener(listener: ChangeListener) {
        val eventListener: (Event) -> Unit = { listener() }
        listeners[listener] = eventListener
        mediaQueryList.addEventListener("change", eventListener)
    }

    override fun removeChangeListener(listener: ChangeListener) {
        val eventListener = listeners.remove(listener) ?: return
        mediaQueryList.removeEventListener("change", eventListener)
    }
}

private fun defaultMotionMediaQueryHandle(): MotionMediaQueryHandle? {
    val matchMedia = window.asDynamic().matchMedia
    return if (jsTypeOf(matchMedia) == "function") {
        BrowserMotionMediaQueryHandle(
            mediaQueryList = matchMedia("(prefers-reduced-motion: reduce)") as MediaQueryList,
        )
    } else {
        null
    }
}
