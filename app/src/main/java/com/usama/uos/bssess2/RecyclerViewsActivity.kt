package com.usama.uos.bssess2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.usama.uos.bssess2.Adapter.GmailAdapter
import com.usama.uos.bssess2.Interfaces.GmailInterface
import com.usama.uos.bssess2.Models.GmailModel

class RecyclerViewsActivity : AppCompatActivity() , GmailInterface {

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

         val gmailAdapter = GmailAdapter(gmailArrayList , this@RecyclerViewsActivity , this)
         rvGmail.adapter = gmailAdapter

      }
      


   }

   override fun gmailItemClickListener(view: View, gmailModel: GmailModel, position: Int) {

      //Toast.makeText(this@RecyclerViewsActivity , gmailModel.txtUserName , Toast.LENGTH_LONG).show()

      val intent = Intent(this@RecyclerViewsActivity , HomePageActivity::class.java)
      intent.putExtra("TestData" , gmailModel.txtUserName)
      startActivity(intent)


   }
}