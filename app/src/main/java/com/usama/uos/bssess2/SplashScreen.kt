package com.usama.uos.bssess2

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashScreenActivity : AppCompatActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_splash_screen)

      Handler().postDelayed(Runnable {

         startActivity(Intent(this@SplashScreenActivity , RecyclerViewsActivity::class.java))

      }, 3000)

   }
}