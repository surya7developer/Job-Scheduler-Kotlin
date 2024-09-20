package com.suresh.job.scheduler.demo

import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log
import com.suresh.job.scheduler.demo.MainActivity.Companion.TAG
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.concurrent.thread
import kotlin.random.Random

class MyJobService : JobService() {

    companion object {
        var randomNumber: Int = 0
    }
    private var isServiceRunning = false

    override fun onStartJob(params: JobParameters?): Boolean {
        isServiceRunning = true
        Log.d(TAG, "onStartJob Called")
        GlobalScope.launch {
            while (isServiceRunning) {
                delay(1000)
                randomNumber = Random.nextInt(20)
                Log.d(TAG, "onStartJob: $randomNumber")
            }
        }

        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        Log.d(TAG, "onStopJob Called")
        isServiceRunning = false
        return false
    }
}