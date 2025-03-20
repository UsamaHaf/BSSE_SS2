package com.usama.uos.bssess2

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomePageActivity : AppCompatActivity() {

   lateinit var txtWelcomeText:TextView

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_home_page)

      txtWelcomeText = findViewById(R.id.txtWelcomeText)

      val recievedData = intent.getStringExtra("TestData")

      if(recievedData != null){
         txtWelcomeText.text = "Welcome Mr. $recievedData"

      }else{
         txtWelcomeText.text = "No Data Yet"

      }


   }
}