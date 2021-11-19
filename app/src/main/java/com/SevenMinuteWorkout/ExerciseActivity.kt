package com.SevenMinuteWorkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import com.SevenMinuteWorkout.databinding.ActivityExerciseBinding
import com.SevenMinuteWorkout.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private var binding:ActivityExerciseBinding?=null
    private var countDownTimer:CountDownTimer?=null
    private var exerciseDownTimer:CountDownTimer?=null
    private var pauseOffset:Long=0
    private var totalDuration:Long=10000
    private var exerciseDuration:Long=30000
    private var exerciseProgress:Int=0
    private var restProgress:Int=0
    private var currentExercisePosition=0
    private var exerciseList:ArrayList<ExerciseClass>?=null
    private var tts:TextToSpeech?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarExercise)
        tts= TextToSpeech(this,this)
        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolbarExercise?.setNavigationOnClickListener({
            onBackPressed()
        })
        exerciseList=Constants.defaultExerciseList()
        setUpResetView()
    }

    override fun onDestroy() {
        super.onDestroy()
        if(tts !=null){
            tts?.stop()
            tts?.shutdown()
        }
        if(countDownTimer!=null){
            countDownTimer!!.cancel()
            restProgress=0
        }
        if(exerciseDownTimer!=null){
            exerciseDownTimer!!.cancel()
            exerciseProgress=0
        }
        binding = null
    }
    private fun setUpResetView() {
        binding?.flcircle?.visibility=View.VISIBLE
        binding?.flprogressExercise?.visibility=View.INVISIBLE
        binding?.tvTitle?.visibility=View.VISIBLE
        binding?.tvTitleExercise?.visibility=View.INVISIBLE
        binding?.exerciseImage?.visibility=View.INVISIBLE
        if (countDownTimer != null) {
            countDownTimer!!.cancel()
            restProgress = 0

        }
        var restText:String="Get Ready For "+exerciseList!![currentExercisePosition+1].getName()
        binding?.tvTitle?.text=restText
        speakOut(restText)
        startTimer()
    }
    private fun setUpExerciseView() {
        binding?.flcircle?.visibility=View.INVISIBLE
        binding?.tvTitle?.visibility=View.INVISIBLE
        binding?.tvTitleExercise?.visibility=View.VISIBLE
        binding?.exerciseImage?.visibility=View.VISIBLE
        binding?.flprogressExercise?.visibility=View.VISIBLE
        if (exerciseDownTimer != null) {
            exerciseDownTimer!!.cancel()
            exerciseProgress = 0

        }
        var exerciseText:String=exerciseList!![currentExercisePosition].getName()
        binding?.exerciseImage?.setImageResource(exerciseList!![currentExercisePosition].getImage())
        binding?.tvTitleExercise?.text=exerciseText
        speakOut(exerciseText)
        startExerciseTimer()
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
                currentExercisePosition++
                setUpExerciseView()
            }
        }.start()
    }

    private fun startExerciseTimer(){
        binding?.exProgressBar?.progress=exerciseProgress
        exerciseDownTimer=object : CountDownTimer(30000,1000){
            override fun onTick(millisUntilFinished: Long) {
                exerciseProgress++
                binding?.exProgressBar?.progress=30-exerciseProgress

                binding?.tvTimerForExercise?.text=(30-exerciseProgress).toString()
                speakOut((30-exerciseProgress).toString())
            }

            override fun onFinish() {
                if(currentExercisePosition<exerciseList?.size!!-1)
                {
                setUpResetView()
                }else{
                    var intent=Intent(this@ExerciseActivity,MainActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this@ExerciseActivity,"The exercise is completed",Toast.LENGTH_LONG).show()
                }
            }
        }.start()
    }

    override fun onInit(status: Int) {
        if(status==TextToSpeech.SUCCESS){
            var result=tts!!.setLanguage(Locale.US)
            if(result==TextToSpeech.LANG_MISSING_DATA||result==TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS","There is an icorrect text format or missing text")
            }
        }else{
            Log.e("TTS","Intialization Failed")
        }
    }
 private fun speakOut(text:String){
     tts?.speak(text,TextToSpeech.QUEUE_ADD,null,"")
 }
}