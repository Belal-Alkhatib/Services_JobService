package com.example.and2_nluc4_par2_jobservice

import android.app.job.JobParameters
import android.app.job.JobService
import android.os.CountDownTimer
import android.util.Log


class MyService : JobService() {
    private var jobCancelled:Boolean = false

    override fun onStartJob(params: JobParameters?): Boolean {
        val counter = object : CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                Log.e("hzm", "Count is  ${millisUntilFinished/1000}")
                if(jobCancelled)
                    return
            }

            override fun onFinish() {
                Log.e("hzm", "Job Completed")
                jobFinished(params,true)
            }

        }
            counter.start()
        return false
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        jobCancelled = true
        return true

    }


}