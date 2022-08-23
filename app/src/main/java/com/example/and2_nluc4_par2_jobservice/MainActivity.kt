package com.example.and2_nluc4_par2_jobservice

import android.annotation.SuppressLint
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.and2_nluc4_par2_jobservice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    @SuppressLint("ServiceCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        binding.btnStart.setOnClickListener {
            val cn = ComponentName(this,MyService::class.java)
            val jobInfo = JobInfo.Builder(123,cn)
                .setMinimumLatency(5000)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setRequiresCharging(true)
                .build()

            val schuler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
            val resultCode = schuler.schedule(jobInfo)

            if (resultCode == JobScheduler.RESULT_SUCCESS){
                Log.e("hzm", "Job Success")
            }else{
                Log.e("hzm", "Job Failed")
            }

        }

        binding.btnStop.setOnClickListener {
            val scheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
            scheduler.cancel(123)
        }

    }
}