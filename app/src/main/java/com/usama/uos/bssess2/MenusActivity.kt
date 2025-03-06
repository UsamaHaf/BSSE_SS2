package com.usama.uos.bssess2

import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MenusActivity : AppCompatActivity() {

   lateinit var btnContextMenu: TextView
   lateinit var btnPopUpMenu: TextView

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_menus)

      btnContextMenu = findViewById(R.id.btnContextMenu)
      btnPopUpMenu = findViewById(R.id.btnPopUpMenu)
      registerForContextMenu(btnContextMenu)

      btnPopUpMenu.setOnClickListener {
         popUpMenu(btnPopUpMenu)
      }

   }

   private fun popUpMenu(view: View){
      val popMenu = PopupMenu(this@MenusActivity , view )
      val inflater : MenuInflater = menuInflater
      inflater.inflate(R.menu.pop_up_menu , popMenu.menu)

      popMenu.setOnMenuItemClickListener { menuitems ->
         if (menuitems.itemId == R.id.newGrpPopupMenu){
            Toast.makeText(this@MenusActivity , "NewGroup Popup Clicked" , Toast.LENGTH_SHORT).show()

         }else if (menuitems.itemId == R.id.broadcastPopupMenu){
            Toast.makeText(this@MenusActivity , "Broadcast Popup Clicked" , Toast.LENGTH_SHORT).show()
         }
         else if (menuitems.itemId == R.id.devicesPopupMenu){
            Toast.makeText(this@MenusActivity , "Linked Devices Popup Clicked" , Toast.LENGTH_SHORT).show()
         }

         true

      }

      popMenu.show()

   }

   override fun onCreateOptionsMenu(menu: Menu?): Boolean {
      menuInflater.inflate(R.menu.option_menu , menu)
      return true
   }

   override fun onOptionsItemSelected(item: MenuItem): Boolean {

      if(item.itemId == R.id.profileMenu){
         Toast.makeText(this@MenusActivity , "Profile Clicked" , Toast.LENGTH_SHORT).show()

      }else if (item.itemId == R.id.aboutMenu){

         Toast.makeText(this@MenusActivity , "About Clicked" , Toast.LENGTH_SHORT).show()

      }
      else if (item.itemId == R.id.devicesLnkMenu){
         Toast.makeText(this@MenusActivity , "Linked Devices Clicked" , Toast.LENGTH_SHORT).show()
      }

      return true
   }

   override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
      super.onCreateContextMenu(menu, v, menuInfo)
      menuInflater.inflate(R.menu.context_menu , menu)
   }

   override fun onContextItemSelected(item: MenuItem): Boolean {
      return when(item.itemId){
         R.id.broadcastMenu ->{
            Toast.makeText(this@MenusActivity , "New Broadcast clicked" , Toast.LENGTH_SHORT).show()
            true
         }

         R.id.newGrpMenu ->{
            Toast.makeText(this@MenusActivity , "New Group clicked" , Toast.LENGTH_SHORT).show()
            true
         }



         else -> super.onContextItemSelected(item)
      }
   }

}