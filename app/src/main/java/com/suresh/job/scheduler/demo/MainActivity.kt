package com.suresh.job.scheduler.demo

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.suresh.job.scheduler.demo.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var jobScheduler: JobScheduler
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        jobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler

        binding.StartJob.setOnClickListener {
            initializeJobService()
        }

        binding.stopJob.setOnClickListener {
            stopJobService()
        }
    }

    private fun stopJobService() {
        jobScheduler.cancel(101)
        Log.d(TAG, "initializeJobService: stopJobService")

    }

    private fun initializeJobService() {
        Log.d(TAG, "initializeJobService: clicked")

        val componentName = ComponentName(this,MyJobService::class.java)
        val jobInfo = JobInfo.Builder(101,componentName)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
            .setRequiresCharging(true)
            .setPersisted(true)
            .build()


        jobScheduler.schedule(jobInfo)
        Log.d(TAG, "initializeJobService: startJobService")


    }

    companion object {
        val TAG = "JOB_SCHEDULER_LOG"
    }
}