package com.example.assignment8

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    private val CHANNEL_ID = "testChannel01"   // Channel for notification
    private var notificationManager: NotificationManager? = null
    var num = 0

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createNotificationChannel(CHANNEL_ID, "testChannel", "this is a test Channel")

        val print_num = findViewById<TextView>(R.id.print_number)
        val plus_number = findViewById<Button>(R.id.count_btn)
        val toast_massage = findViewById<Button>(R.id.tst_btn)
        val random_btn = findViewById<Button>(R.id.random_btn)

        if(intent.hasExtra("랜덤"))
        {
            num = intent.getIntExtra("랜덤", 0)
            print_num.setText(num.toString())
        }

        toast_massage.setOnClickListener()
        {
            Toast.makeText(applicationContext,"현재 입력된 숫자는 " + num.toString() + "입니다.", Toast.LENGTH_SHORT).show();
        }

        plus_number.setOnClickListener{
            num++
            print_num.setText(num.toString())
        }

        random_btn.setOnClickListener{
            displayNotification()
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun displayNotification() {
        val notificationId = 45

        val tapResultIntent = Intent(this, SubActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("숫자", num)
        }

        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            this,
            0,
            tapResultIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notification = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.notion_image)
            .setContentTitle(getString(R.string.notification_title))
            .setContentText(getString(R.string.notification_main_text))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        notificationManager?.notify(notificationId, notification)
    }

    private fun createNotificationChannel(channelId: String, name: String, channelDescription: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT // set importance
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = channelDescription
            }
            // Register the channel with the system
            notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager?.createNotificationChannel(channel)
        }
    }
}