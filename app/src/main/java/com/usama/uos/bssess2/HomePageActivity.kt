package com.usama.uos.bssess2

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.usama.uos.bssess2.Fragments.AddProductsFragment
import com.usama.uos.bssess2.Fragments.ShowProductsFragment
import com.usama.uos.bssess2.Fragments.UserProfileFragment

class HomePageActivity : AppCompatActivity() {

   lateinit var btnOpenSideMenu: ImageView
   lateinit var txtAppBarTitle: TextView
   lateinit var myNavigationView: NavigationView
   lateinit var mainDrawerLayout: DrawerLayout
   lateinit var firebaseAuth: FirebaseAuth

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_home_page)

      firebaseAuth = FirebaseAuth.getInstance()

      mainDrawerLayout = findViewById(R.id.mainDrawerLayout)
      myNavigationView = findViewById(R.id.myNavigationView)
      btnOpenSideMenu = findViewById(R.id.btnOpenSideMenu)
      txtAppBarTitle = findViewById(R.id.txtAppBarTitle)

      mainDrawerLayout.closeDrawer(GravityCompat.START)
      btnOpenSideMenu.setOnClickListener {
         mainDrawerLayout.openDrawer(GravityCompat.START)
      }
      val toggle =
          ActionBarDrawerToggle(this@HomePageActivity, mainDrawerLayout,
             null, R.string.open_navigation_drawer, R.string.close_navigation_drawer)
      mainDrawerLayout.addDrawerListener(toggle)
      toggle.syncState()

      //setting dynamic data on navigation header
      val headview = myNavigationView.getHeaderView(0)
      val headerEmailAddress = headview.findViewById<TextView>(R.id.headerEmailAddress)
      headerEmailAddress.text = firebaseAuth.currentUser?.email

      setFragment(ShowProductsFragment(), "Products")


      myNavigationView.setNavigationItemSelectedListener { menuItems ->
         when (menuItems.itemId) {
            R.id.userProfile -> {
               setFragment(UserProfileFragment(), "User Profile Fragment")
            }
            R.id.aboutUs -> {
               setFragment(AddProductsFragment(), "Add Products")
            }
            R.id.logoutUser -> {
               firebaseAuth.signOut()
               Toast.makeText(this@HomePageActivity, "Logout Successful", Toast.LENGTH_SHORT).show()
               startActivity(Intent(this@HomePageActivity, LoginActivity::class.java))
            }}
         true
      }


   }

   private fun setFragment(fragment: Fragment, title: String) {
      this@HomePageActivity.supportFragmentManager
         .beginTransaction()
         .replace(R.id.fragmentContainer, fragment)
         .addToBackStack(null).commit()

      txtAppBarTitle.text = title
      mainDrawerLayout.closeDrawer(GravityCompat.START)
   }

}

/*lateinit var txtWelcomeText:TextView
      txtWelcomeText = findViewById(R.id.txtWelcomeText)
      val recievedData = intent.getStringExtra("TestData")
      if(recievedData != null){
         txtWelcomeText.text = "Welcome Mr. $recievedData"
      }else{
         txtWelcomeText.text = "No Data Yet"
      }*/