package com.example.assignment_11

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
    private lateinit var timerText: TextView
    private val timeList = mutableListOf<String>()
    private var job: Job? = null
    private var isRunning = false
    private var time = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val startBtn = findViewById<Button>(R.id.startBtn)
        val stopBtn = findViewById<Button>(R.id.stopBtn)
        val lapBtn = findViewById<Button>(R.id.labBtn)
        timerText = findViewById(R.id.timer_text)
        val recordRecyclerView: RecyclerView = findViewById(R.id.timer_recycler_view)
        val timerAdapter = TimerAdapter()

        val dividerItemDecoration = DividerItemDecoration(
            recordRecyclerView.context, LinearLayoutManager(this).orientation
        )

        timerAdapter.setTimeList(timeList)

        recordRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = timerAdapter
            addItemDecoration(dividerItemDecoration)
        }

        startBtn.setOnClickListener {
            when(isRunning){
                false ->{
                    startBtn.text = "pause"
                    startTimer()
                }
                else ->{
                    startBtn.text = "start"
                    job?.cancel()
                    isRunning = false
                }
            }
        }

        stopBtn.setOnClickListener {
            time = 10L
            timerText.text ="00:00:00"
            job?.cancel()
            isRunning = false
            startBtn.text = "start"
            timerAdapter.notifyItemRangeRemoved(0, timeList.size)
            timeList.clear()
        }

        lapBtn.setOnClickListener{
            timeList.add(timerText.text.toString())
            timerAdapter.notifyItemInserted(timeList.size)
        }


    }

    private fun startTimer() {
        isRunning = true

        job = CoroutineScope(Dispatchers.Main).launch {
            while (isRunning) {
                delay(10L)
                timerText.text = convertTime(time)
                time += 10
            }
        }
    }

    private fun convertTime(time: Long): String {
        val minute = time / 60000
        val second = time / 1000 % 60
        val milliSecond = time % 1000

        return String.format("%02d:%02d:%02d", minute, second, milliSecond / 10)
    }
}