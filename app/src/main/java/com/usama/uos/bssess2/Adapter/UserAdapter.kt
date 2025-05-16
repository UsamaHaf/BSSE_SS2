package com.usama.uos.bssess2.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.usama.uos.bssess2.Interfaces.UserItemsInterface
import com.usama.uos.bssess2.Models.UserModel
import com.usama.uos.bssess2.R

class UserAdapter(var userArrayList: ArrayList<UserModel>, context: Context,
                  var userItemsInterface: UserItemsInterface) :
   RecyclerView.Adapter<UserAdapter.MyUserViewHolder>() {

   val inflator: LayoutInflater = LayoutInflater.from(context)

   inner class MyUserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
      val txtUserName: TextView = itemView.findViewById(R.id.txtUserName)
      val txtUserEmail: TextView = itemView.findViewById(R.id.txtUserEmail)
   }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyUserViewHolder {
      val view = inflator.inflate(R.layout.list_items_users, null)
      return MyUserViewHolder(view)
   }

   override fun getItemCount(): Int {
      return userArrayList.size
   }

   override fun onBindViewHolder(holder: MyUserViewHolder, position: Int) {
      val model = userArrayList[position]

      holder.txtUserName.text = model.userFirstName
      holder.txtUserEmail.text = model.userEmailAddress

      holder.txtUserName.setOnClickListener { view ->
         userItemsInterface.UserItemsClickListener(view, model, position)


      }

   }

}