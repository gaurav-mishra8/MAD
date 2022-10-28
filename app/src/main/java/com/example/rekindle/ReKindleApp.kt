package com.example.rekindle

import android.app.Activity
import android.app.ActivityManager
import android.app.ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
import android.app.Application
import android.os.Bundle
import android.os.Handler
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ReKindleApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (isForegroundProcess()) {
            var firstPostEnqueued = true
            Handler().post {
                firstPostEnqueued = false
            }
            registerActivityLifecycleCallbacks(object :
                ActivityLifecycleCallbacks {

                override fun onActivityStarted(activity: Activity) {

                }

                override fun onActivityResumed(activity: Activity) {
                }

                override fun onActivityPaused(activity: Activity) {
                }

                override fun onActivityStopped(activity: Activity) {
                }

                override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
                }

                override fun onActivityDestroyed(activity: Activity) {
                }

                override fun onActivityCreated(
                    activity: Activity,
                    savedInstanceState: Bundle?
                ) {
                    unregisterActivityLifecycleCallbacks(this)
                    if (firstPostEnqueued && savedInstanceState == null) {
                        Log.d("Cold Start", " first activity created")
                    }
                }
            })
        }
    }

    private fun isForegroundProcess(): Boolean {
        val processInfo = ActivityManager.RunningAppProcessInfo()
        ActivityManager.getMyMemoryState(processInfo)
        return processInfo.importance == IMPORTANCE_FOREGROUND
    }
}