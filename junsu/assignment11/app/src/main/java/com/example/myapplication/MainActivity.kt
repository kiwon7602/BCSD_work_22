package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private val lapTimeList = mutableListOf<TimeData>()
    private var job: Job? = null
    private var isRunningTimer = false
    private var time = 0L
    private var recordNum = 0

    private lateinit var timerTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timerTextView = findViewById(R.id.time_text_view)
        val recordRecyclerView: RecyclerView = findViewById(R.id.record_recycler_view)

        val startPauseButton: Button = findViewById(R.id.start_pause_button)
        val stopButton: Button = findViewById(R.id.stop_button)
        val lapButton: Button = findViewById(R.id.lap_button)

        val timeAdapter = TimeAdapter()

        startPauseButton.setOnClickListener {
            when(isRunningTimer) {
                true -> {
                    job?.cancel()
                    startPauseButton.text = getString(R.string.start)
                    isRunningTimer = false
                }
                else -> {
                    startTimer()
                    startPauseButton.text = getString(R.string.pause)
                    isRunningTimer = true
                }
            }
        }

        stopButton.setOnClickListener {
            job?.cancel()
            time = 0L
            recordNum = 0
            timerTextView.text = getString(R.string.initial_time)
            lapTimeList.clear()
            timeAdapter.notifyDataSetChanged()
            startPauseButton.text = getString(R.string.start)
            isRunningTimer = false
        }

        val dividerItemDecoration = DividerItemDecoration(
            recordRecyclerView.context, LinearLayoutManager(this).orientation
        )

        timeAdapter.setTimeList(lapTimeList)

        recordRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = timeAdapter
            addItemDecoration(dividerItemDecoration)
        }

        lapButton.setOnClickListener {
            lapTimeList.add(TimeData(++recordNum, timerTextView.text.toString()))
            timeAdapter.notifyItemInserted(lapTimeList.size)
        }
    }

    private fun startTimer() {
        isRunningTimer = true

        var delayTime = 10L
        var prevSystemTime: Long
        var currSystemTime: Long
        var timeInterval: Long

        job = CoroutineScope(Dispatchers.Main).launch {
            prevSystemTime = System.currentTimeMillis()
            while (true) {
                delay(delayTime)

                currSystemTime = System.currentTimeMillis()
                timeInterval = currSystemTime - prevSystemTime - 10L
                delayTime -= timeInterval
                prevSystemTime = currSystemTime

                timerTextView.text = convertTime(time)
                time += 10
            }
        }
    }

    private fun convertTime(time: Long): String {
        val minutes= TimeUnit.MILLISECONDS.toMinutes(time)
        val seconds = TimeUnit.MILLISECONDS.toSeconds(time) - TimeUnit.MINUTES.toSeconds(minutes)
        val milliSeconds = time - TimeUnit.SECONDS.toMillis(seconds) - TimeUnit.MINUTES.toMillis(minutes)

        return String.format("%02d:%02d:%02d", minutes, seconds, milliSeconds / 10)
    }
}