package com.example.quake

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

@Suppress("DEPRECATION")
class SplashScreen : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val textView: TextView = findViewById(R.id.SplashScreenText)
        textView.visibility = View.GONE
        //val view: View = findViewById(R.id.SplashScreenView)
        //view.visibility = View.GONE

        //Animation call
        //val animation = AnimationUtils.loadAnimation(this, R.anim.quake_animation)

        //Setting the animation
        //view.setAnimation(animation)

        // Hide status bar and make splash screen as a full screen activity
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        /*Handler(Looper.getMainLooper()).postDelayed(
            {
            textView.visibility = View.VISIBLE
            },
            3000 // delayed time in milliseconds
        )*/

        /*Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            //finish()
        }, 5500) // delayed time in milliseconds*/
    }
}
