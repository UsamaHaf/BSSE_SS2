package com.usama.uos.bssess2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.usama.uos.bssess2.SharedPref.MySharedPreferences

class LoginActivity : AppCompatActivity() {

   lateinit var sharedPreferences: MySharedPreferences
   lateinit var loginEmail: EditText
   lateinit var loginPassword: EditText
   lateinit var btnLoginUser: Button
   lateinit var btnCreateNewAcc: Button
   lateinit var pbLogin: ProgressBar
   lateinit var firebaseAuth: FirebaseAuth

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_login)

      firebaseAuth = FirebaseAuth.getInstance()

      sharedPreferences = MySharedPreferences(this@LoginActivity)

      loginEmail = findViewById(R.id.loginEmail)
      loginPassword = findViewById(R.id.loginPassword)
      btnLoginUser = findViewById(R.id.btnLoginUser)
      btnCreateNewAcc = findViewById(R.id.btnCreateNewAcc)
      pbLogin = findViewById(R.id.pbLogin)

      btnCreateNewAcc.setOnClickListener {
         startActivity(Intent(this@LoginActivity, SignupActivity::class.java))
      }

      btnLoginUser.setOnClickListener {
         val strEmail = loginEmail.text.toString()
         val strPassword = loginPassword.text.toString()

         if (strEmail.isEmpty()) {
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show()
         }else if (strPassword.isEmpty()) {
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show()
         } else {

            pbLogin.visibility =ProgressBar.VISIBLE
            signUser(strEmail , strPassword)




         }


      }


   }

   private fun signUser(strEmail: String, strPassword: String) {

      firebaseAuth.signInWithEmailAndPassword(strEmail , strPassword).addOnCompleteListener { task->
         if(task.isSuccessful){
            startActivity(Intent(this@LoginActivity, HomePageActivity::class.java))
            Toast.makeText(this@LoginActivity , "Login Successful", Toast.LENGTH_SHORT).show()

            pbLogin.visibility =ProgressBar.GONE

         }else{
            pbLogin.visibility =ProgressBar.GONE

            Toast.makeText(this@LoginActivity , "Login Failed ${task.exception}", Toast.LENGTH_SHORT).show()
         }
      }

   }
}

/*
sharedPreferences.saveEmail("UserEmail", strEmail)
sharedPreferences.saveIsLoggedIn("UserLoginStatus", "True")*/
