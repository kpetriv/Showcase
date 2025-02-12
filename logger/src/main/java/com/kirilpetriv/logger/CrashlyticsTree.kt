package com.kirilpetriv.logger

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.crashlytics.crashlytics
import timber.log.Timber

class CrashlyticsTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        when (priority) {
            Log.DEBUG -> Unit
            Log.INFO,
            Log.WARN -> Firebase.crashlytics.log(message)

            Log.ERROR -> t?.also {
                Firebase.crashlytics.log(message)
                Firebase.crashlytics.recordException(it)
            }
            // Not used
            Log.ASSERT,
            Log.VERBOSE -> Unit
        }
    }
}