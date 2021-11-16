package com.SevenMinuteWorkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import com.SevenMinuteWorkout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding:ActivityMainBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        //val flLayoutview:FrameLayout=findViewById(R.id.fl_view)
        binding?.flStart?.setOnClickListener({
            Toast.makeText(this@MainActivity,"Start is Clicked",Toast.LENGTH_LONG).show()
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }
}