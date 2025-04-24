package com.usama.uos.bssess2

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.usama.uos.bssess2.Fragments.AboutUsFragment
import com.usama.uos.bssess2.Fragments.UserProfileFragment

class HomePageActivity : AppCompatActivity() {

   lateinit var btnOpenSideMenu: ImageView
   lateinit var myNavigationView: NavigationView
   lateinit var mainDrawerLayout: DrawerLayout

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_home_page)

      mainDrawerLayout = findViewById(R.id.mainDrawerLayout)
      myNavigationView = findViewById(R.id.myNavigationView)
      btnOpenSideMenu = findViewById(R.id.btnOpenSideMenu)

      mainDrawerLayout.closeDrawer(GravityCompat.START)
      btnOpenSideMenu.setOnClickListener {
         mainDrawerLayout.openDrawer(GravityCompat.START)
      }

      val toggle = ActionBarDrawerToggle(this@HomePageActivity , mainDrawerLayout , null , R.string.open_navigation_drawer , R.string.close_navigation_drawer)
      mainDrawerLayout.addDrawerListener(toggle)
      toggle.syncState()

      myNavigationView.setNavigationItemSelectedListener { menuItems ->
         when (menuItems.itemId) {
            R.id.userProfile -> {
               setFragment(UserProfileFragment() , "")
            }

            R.id.aboutUs -> {
               setFragment(AboutUsFragment() , "")
            }

            R.id.logoutUser -> {
            }
         }
         true

      }








   }

   fun setFragment(fragment: Fragment , title:String){
      this@HomePageActivity.supportFragmentManager.beginTransaction()
         .replace(R.id.fragmentContainer , fragment)
         .addToBackStack(null)
         .commit()
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