package com.example.assignment11

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {

    private lateinit var timeText : TextView
    private var timerStartState = false
    private var timerPauseState = false
    private var startTime = 0L
    private var currentTime = 0L
    private var pauseTime = 0L
    private var resumeTime = 0L
    var timerJob : Job? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val recyclerView : RecyclerView = findViewById(R.id.time_lab)
        val startAndPauseButton = findViewById<Button>(R.id.startButton)
        val stopButton = findViewById<Button>(R.id.stopButton)
        val recodeButton = findViewById<Button>(R.id.recordButton)
        val timerAdapter = TimerAdapter()
        timeText = findViewById(R.id.TimeText)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = timerAdapter



        startAndPauseButton.setOnClickListener{
            when(timerStartState){
                false -> {
                    startTime = System.currentTimeMillis()
                    timeStart(startTime)
                    startAndPauseButton.text = getString(R.string.Pause)
                    timerStartState = true
                }
                true -> {
                    when(timerPauseState){
                        false ->{
                            timePause()
                            timerPauseState = !timerPauseState
                            startAndPauseButton.text = getString(R.string.Start)
                        }
                        true -> {
                            startTime = resumeTime
                            timeStart(resumeTime)
                            timerPauseState = !timerPauseState
                            startAndPauseButton.text = getString(R.string.Pause)
                        }
                    }
                }
            }
        }
        stopButton.setOnClickListener{
            timerJob?.cancel()
            startAndPauseButton.text = getString(R.string.Start)
            timerStartState = false
            timerPauseState = false
            startTime= 0L
            timeText.text = getString(R.string.Zero)
        }
        recodeButton.setOnClickListener {
            if(timerStartState){
                timerAdapter.addTimeData(TimeData(currentTime))
            }
        }


    }
    private fun timeStart(time:Long) {
        timerJob = CoroutineScope(Dispatchers.Main).launch {
            while(!timerPauseState){
                currentTime = System.currentTimeMillis()-time
                timeText.text = currentTime.displayTime()
                delay(INTERVAL)
            }
        }
    }

    private fun timePause(){
        pauseTime = System.currentTimeMillis()-startTime
        timeText.text = pauseTime.displayTime()
        timerJob = CoroutineScope(Dispatchers.Main).launch {
            while(true){
                resumeTime = System.currentTimeMillis() - pauseTime
                delay(INTERVAL)
            }
        }
    }

    fun Long.displayTime(): String{
        if (this <= 0L){
            return getString(R.string.Zero)
        }
        val min = this / 1000 % 3600 / 60
        val sec = this/ 1000 % 60
        val msec = this % 1000 / 10

        return "${displaySlot(min)}:${displaySlot(sec)}:${displaySlot(msec)}"
    }
    private fun displaySlot(count: Long): String{
        return if(count / 10L > 0){ "$count" }
        else {"0$count"}
    }

    private companion object{
        private const val INTERVAL = 10L
    }
}