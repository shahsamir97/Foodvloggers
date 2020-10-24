package com.samir.foodvloggers

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.WindowManager
import javax.xml.parsers.SAXParser


class SplashScreen : AppCompatActivity() {

    private val SPLASH_SCREEN_TIME_OUT = 3000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)




        val runnable = Runnable {
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
            Log.i("runnable223","running")
        }

      Handler().postDelayed(runnable, SPLASH_SCREEN_TIME_OUT)
    }

}


