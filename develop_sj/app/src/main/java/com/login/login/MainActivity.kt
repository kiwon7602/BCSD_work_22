package com.login.login

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import java.util.Locale

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageNaverLogo = findViewById<ImageView>(R.id.image_naver_logo)
        val textLoginGuide = findViewById<TextView>(R.id.text_login_guide)
        val textResult = findViewById<TextView>(R.id.text_result)
        val editId = findViewById<EditText>(R.id.edit_id)
        val editPassword = findViewById<EditText>(R.id.edit_password)
        val btnLogin = findViewById<Button>(R.id.btn_login)
        val themeSwitch = findViewById<Switch>(R.id.theme_switch)
        val themeState = findViewById<TextView>(R.id.theme_state)
        btnLogin.setOnClickListener(View.OnClickListener() {
            var result = editId.text.toString() + resources.getString(R.string.welcome_msg)
            var password = editPassword.text.toString()
            if(password=="bcsd")textResult.text = result
            else textResult.text = resources.getString(R.string.wrong_pw_msg)
        })
        themeSwitch.setOnCheckedChangeListener { p0, isChecked ->
            if (isChecked) {
                themeState.text = "DARK"
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                themeState.text = "LIGHT"
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE){

        }
    }
}