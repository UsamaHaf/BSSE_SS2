package com.usama.uos.bssess2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.usama.uos.bssess2.Adapter.GmailAdapter
import com.usama.uos.bssess2.Models.GmailModel

class RecyclerViewsActivity : AppCompatActivity() {

   lateinit var rvGmail:RecyclerView
   lateinit var gmailArrayList: ArrayList<GmailModel>

   var useDPArray = arrayOf( R.drawable.user ,R.drawable.more , R.drawable.background)
   var userNameArray = arrayOf("Abid Rafique" , "Usman" , "BSSE 4th SS2")

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_recycler_views)

      rvGmail = findViewById(R.id.rvGmail)
      gmailArrayList = ArrayList()

      //for (i , i<userNameArray(3) , i++)

      for (i in userNameArray.indices){
         val model = GmailModel(useDPArray[i] , userNameArray[i])
         gmailArrayList.add(model)
      }

      if(gmailArrayList != null){

         val gmailAdapter = GmailAdapter(gmailArrayList , this@RecyclerViewsActivity)
         rvGmail.adapter = gmailAdapter

      }
      


   }
}