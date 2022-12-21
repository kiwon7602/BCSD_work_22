package com.example.assignment8

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity(){
    companion object {
        private const val CHANNEL_ID = "testChannel01"
    }
    private var notificationManager: NotificationManager? = null
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private var number = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toastButton = findViewById<Button>(R.id.toastButton)
        val countButton = findViewById<Button>(R.id.countButton)
        val randomButton = findViewById<Button>(R.id.randomButton)
        val numberText = findViewById<TextView>(R.id.numberText)

        createNotificationChannel(CHANNEL_ID, "testChannel", "this is a test Channel")

        activityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK){
                val receive = it.data?.getIntExtra("number", -1) ?:""
                number = receive.toString().toInt()
                numberText.text = number.toString()
            }
        }

        toastButton.setOnClickListener{
            Toast.makeText(this, "시공의 폭풍은 정말 최고야", Toast.LENGTH_SHORT).show()
        }
        countButton.setOnClickListener{
            number += 1
            numberText.text = number.toString()
        }
        randomButton.setOnClickListener{
            displayNotification(number, CHANNEL_ID)
        }

        if(savedInstanceState == null){}
        else{
            number = savedInstanceState.getInt("number")
            numberText.text = number.toString()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("number", number)
        super.onSaveInstanceState(outState)
    }

    private fun displayNotification(count : Int, channelId: String) {
        val notificationId = 45
        val intent = Intent(this, SubActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        intent.putExtra("number", number)
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_MUTABLE)
        val builder= NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(getString(R.string.title))
            .setContentText(getString(R.string.content))
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(this)) {
            notify(notificationId, builder.build())
        }

    }

    private fun createNotificationChannel(channelId: String, name: String, channelDescription: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = channelDescription
            }
            notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager?.createNotificationChannel(channel)
        }
    }
}