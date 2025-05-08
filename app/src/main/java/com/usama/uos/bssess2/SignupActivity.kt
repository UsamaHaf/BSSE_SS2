package com.usama.uos.bssess2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {

   private lateinit var emailAddress: EditText
   private lateinit var phoneNumber: EditText
   private lateinit var lastName: EditText
   private lateinit var firstName: EditText
   private lateinit var password: EditText
   private lateinit var signup: Button
   private lateinit var btnAlreadyAccount: Button
   lateinit var firebaseAuth: FirebaseAuth
   lateinit var pbSignUp: ProgressBar

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_signup)

      firebaseAuth = FirebaseAuth.getInstance()

      pbSignUp = findViewById(R.id.pbSignUp)
      emailAddress = findViewById(R.id.edtEmailAddress)
      phoneNumber = findViewById(R.id.edtPhoneNo)
      lastName = findViewById(R.id.edtLastName)
      firstName = findViewById(R.id.edtFirstName)
      password = findViewById(R.id.edtPassword)
      signup = findViewById(R.id.btnSignUp)
      btnAlreadyAccount = findViewById(R.id.btnAlreadyAccount)

      btnAlreadyAccount.setOnClickListener {
         startActivity(Intent(this@SignupActivity, LoginActivity::class.java))
      }

      signup.setOnClickListener {

         val strEmail = emailAddress.text.toString()
         val strPhoneNumber = phoneNumber.text.toString()
         val strFName = firstName.text.toString()
         val strLastName = lastName.text.toString()
         val strPassword = password.text.toString()

         signUpUser(strEmail, strPhoneNumber, strFName, strLastName, strPassword)

      }


   }

   private fun signUpUser(strEmail: String, strPhoneNumber: String, strFName: String, strLastName: String, strPassword: String) {
      if (strEmail.isEmpty()) {
         Toast.makeText(this@SignupActivity, "Enter Email Address", Toast.LENGTH_SHORT).show()

      } else if (strPhoneNumber.isEmpty()) {
         Toast.makeText(this@SignupActivity, "Enter Phone Number", Toast.LENGTH_SHORT).show()

      } else if (strFName.isEmpty()) {
         Toast.makeText(this@SignupActivity, "Enter First Name", Toast.LENGTH_SHORT).show()

      } else if (strLastName.isEmpty()) {
         Toast.makeText(this@SignupActivity, "Enter Last Name", Toast.LENGTH_SHORT).show()

      } else if (strPassword.isEmpty() && strPassword.equals("1232e")) {
         Toast.makeText(this@SignupActivity, "Enter Password", Toast.LENGTH_SHORT).show()

      } else {

         pbSignUp.visibility = ProgressBar.VISIBLE

         userSignUpFirebase(strEmail, strPassword)

      }
   }

   private fun userSignUpFirebase(strEmail: String, strPassword: String) {

      try {

         firebaseAuth.createUserWithEmailAndPassword(strEmail, strPassword)
            .addOnCompleteListener { task ->

               if (task.isSuccessful) {
                  Toast.makeText(this@SignupActivity, "SignUp Successful BSSE SS2", Toast.LENGTH_LONG)
                     .show()
                  startActivity(Intent(this@SignupActivity, LoginActivity::class.java))
                  pbSignUp.visibility = ProgressBar.GONE


               } else {
                  pbSignUp.visibility = ProgressBar.GONE

                  Toast.makeText(this@SignupActivity, "SignUp Failed: ${task.exception}", Toast.LENGTH_LONG)
                     .show()
                  Log.e("SignUpError", "userSignUpFirebase: ${task.exception}")
               }


            }
      } catch (e: Exception) {
         pbSignUp.visibility = ProgressBar.GONE

         Log.e("Error", "userSignUpFirebase: ${e.message}")
      }


   }

}

