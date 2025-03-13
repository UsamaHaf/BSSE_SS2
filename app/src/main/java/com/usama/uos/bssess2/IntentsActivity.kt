package com.usama.uos.bssess2

import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class IntentsActivity : AppCompatActivity() {

   lateinit var edtPhoneNo: EditText
   lateinit var edtSMSPhoneNo: EditText
   lateinit var edtSMSMessage: EditText
   lateinit var btnPhoneCall: Button
   lateinit var btnSendMsg: Button

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_intents)

      edtPhoneNo = findViewById(R.id.edtPhoneNumber)
      edtSMSMessage = findViewById(R.id.edtSMSMessage)
      edtSMSPhoneNo = findViewById(R.id.edtSMSPhoneNo)
      btnPhoneCall = findViewById(R.id.btnPhone)
      btnSendMsg = findViewById(R.id.btnSendMsg)

      btnSendMsg.setOnClickListener {
         val strPhone = edtSMSPhoneNo.text.toString()
         val strMessage = edtSMSMessage.text.toString()

         if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.SEND_SMS), 100)
         } else {

            val sendPI : PendingIntent = PendingIntent.getBroadcast(this , 0 , Intent("SMS_SENT") , PendingIntent.FLAG_IMMUTABLE)
            SmsManager.getDefault().sendTextMessage(strPhone, null , strMessage , sendPI , null)

            Toast.makeText(this@IntentsActivity , "SMS Send" , Toast.LENGTH_SHORT).show()

            edtSMSPhoneNo.setText("")
            edtSMSMessage.setText("")

         }


      }


      btnPhoneCall.setOnClickListener {
         val strPhone = edtPhoneNo.text.toString()

         if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CALL_PHONE), 100)
         } else {

            val intent = Intent(Intent.ACTION_CALL , Uri.parse("tel: $strPhone"))
            startActivity(intent)

         }

      }


   }
}