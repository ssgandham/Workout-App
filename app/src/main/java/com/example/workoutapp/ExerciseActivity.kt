package com.example.workoutapp
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.example.workoutapp.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {
    private var binding:ActivityExerciseBinding? = null
    private var timer: CountDownTimer? = null
    private var progressSoFar = 0

    private fun setProgressBar(){
        binding?.progressBar?.progress = progressSoFar

        timer = object: CountDownTimer(5000, 1000){
            override fun onTick(p0: Long) {
                progressSoFar++
                binding?.progressBar?.progress = 5 - progressSoFar
                binding?.timer?.text = (5 - progressSoFar).toString()
            }

            override fun onFinish() {
                Toast.makeText(
                    this@ExerciseActivity,
                    "Here now we will start the exercise.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }.start()
    }
    private fun setupRestView(){
        if(timer!=null){
            timer!!.cancel()
            progressSoFar = 0
        }

        setProgressBar()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolbarExercise)

        if (supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolbarExercise?.setNavigationOnClickListener {
            onBackPressed()
        }

        setupRestView()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (timer != null) {
            timer?.cancel()
            progressSoFar = 0
        }
        binding = null
    }
}