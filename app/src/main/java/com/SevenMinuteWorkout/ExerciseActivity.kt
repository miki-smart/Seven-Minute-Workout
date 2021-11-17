package com.SevenMinuteWorkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.SevenMinuteWorkout.databinding.ActivityExerciseBinding
import com.SevenMinuteWorkout.databinding.ActivityMainBinding

class ExerciseActivity : AppCompatActivity() {
    private var binding:ActivityExerciseBinding?=null
    private var countDownTimer:CountDownTimer?=null
    private var pauseOffset:Long=0
    private var totalDuration:Long=10000
    private var restProgress:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarExercise)
        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolbarExercise?.setNavigationOnClickListener({
            onBackPressed()
        })
        setUpResetView()
    }

    override fun onDestroy() {
        super.onDestroy()
        if(countDownTimer!=null){
            countDownTimer!!.cancel()
            restProgress=0

        }
        binding = null
    }
    private fun setUpResetView() {
        if (countDownTimer != null) {
            countDownTimer!!.cancel()
            restProgress = 0

        }
        startTimer()
    }
    private fun startTimer(){
        binding?.progressBar?.progress=restProgress
        countDownTimer=object : CountDownTimer(10000,1000){
            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                binding?.progressBar?.progress=10-restProgress

                binding?.tvTimer?.text=(10-restProgress).toString()
            }

            override fun onFinish() {
                Toast.makeText(this@ExerciseActivity,"the rest is finished",Toast.LENGTH_LONG).show()
            }
        }.start()
    }
}