package com.example.quake

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SplashScreen : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val animationView: View = findViewById(R.id.animationView)
        val textView: TextView = findViewById(R.id.SplashScreenText)
        textView.visibility = View.GONE

        Handler(Looper.getMainLooper()).postDelayed(
            {
                //Text fade in animation
                textView.visibility = View.VISIBLE
                val fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in_text_animation)
                textView.setAnimation(fadeInAnimation)
            },
            2000)

        Handler(Looper.getMainLooper()).postDelayed({
            //Text fade out animation
            textView.visibility = View.GONE
            val fadeOutAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_out_animation)
            textView.setAnimation(fadeOutAnimation)
            animationView.setAnimation(fadeOutAnimation)
        }, 5000)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 6500)
    }
}
