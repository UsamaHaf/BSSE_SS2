package com.usama.uos.bssess2.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.usama.uos.bssess2.Interfaces.GmailInterface
import com.usama.uos.bssess2.Models.GmailModel
import com.usama.uos.bssess2.R

class GmailAdapter (var arrayListGmail: ArrayList<GmailModel>, activity:Context?, var gmailInterface: GmailInterface): RecyclerView.Adapter<GmailAdapter.MyGmailViewHolder>() {

   var inflator:LayoutInflater = LayoutInflater.from(activity)

   inner class MyGmailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
      val userDP: ImageView = itemView.findViewById(R.id.userDP)
      val userName: TextView = itemView.findViewById(R.id.txtUserName)
      val mainItemLayout:RelativeLayout = itemView.findViewById(R.id.mainItemLayout)
   }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyGmailViewHolder {
      val layoutView = inflator.inflate(R.layout.list_items_gmail, null)
      return MyGmailViewHolder(layoutView)
   }

   override fun getItemCount(): Int {
      return arrayListGmail.size
   }

   override fun onBindViewHolder(holder: MyGmailViewHolder, position: Int) {
      val model = arrayListGmail[position]
      holder.userDP.setImageResource(model.userDP)
      holder.userName.text = model.txtUserName

      holder.mainItemLayout.setOnClickListener { v ->
         gmailInterface.gmailItemClickListener(v , model , position )
      }

   }

}