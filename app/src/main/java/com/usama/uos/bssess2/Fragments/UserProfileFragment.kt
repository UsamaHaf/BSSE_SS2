package com.usama.uos.bssess2.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.gson.Gson
import com.usama.uos.bssess2.Adapter.UserAdapter
import com.usama.uos.bssess2.Interfaces.UserItemsInterface
import com.usama.uos.bssess2.Models.UserModel
import com.usama.uos.bssess2.R


class UserProfileFragment : Fragment(), UserItemsInterface {

   lateinit var rvUsers: RecyclerView
   lateinit var pbUsers: ProgressBar
   lateinit var txtUserData: TextView

   lateinit var userDataArrayList: ArrayList<UserModel>
   lateinit var userAdapter: UserAdapter
   lateinit var firebaseDatabase: FirebaseDatabase
   lateinit var firebaseReference: DatabaseReference

   override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
      val view = inflater.inflate(R.layout.fragment_user_profile, container, false)

      rvUsers = view.findViewById(R.id.rvUsers)
      pbUsers = view.findViewById(R.id.pbUsers)
      txtUserData = view.findViewById(R.id.txtUserData)

      getUsers()

      return view
   }

   private fun getUsers() {
      pbUsers.visibility = ProgressBar.VISIBLE

      firebaseDatabase = FirebaseDatabase.getInstance()
      firebaseReference = firebaseDatabase.getReference("Users")

      firebaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
         override fun onDataChange(snapshot: DataSnapshot) {
            try {
               userDataArrayList = ArrayList()
               for (eachUser in snapshot.children) {
                  val userModel: UserModel? = eachUser.getValue(UserModel::class.java)
                  userDataArrayList.add(userModel!!)

               }
               if (userDataArrayList.isEmpty() || userDataArrayList.size == 0) {
                  txtUserData.visibility = TextView.VISIBLE
                  pbUsers.visibility = ProgressBar.GONE
               } else {
                  userAdapter =
                      UserAdapter(userDataArrayList, requireActivity(), this@UserProfileFragment)
                  rvUsers.adapter = userAdapter
                  pbUsers.visibility = ProgressBar.GONE
               }
            } catch (e: Exception) {
               Log.e("Error", e.toString())
            }
         }

         override fun onCancelled(error: DatabaseError) {
            Log.e("ErrorDB", error.message)
         }

      })


   }

   override fun UserItemsClickListener(view: View?, userModel: UserModel, position: Int) {

      val bundle = Bundle()
      bundle.putString("UserDetails", Gson().toJson(userModel))
      val nextFragment = UpdateDataFragment()
      nextFragment.arguments = bundle
      setFragment(nextFragment, "")

   }

   fun setFragment(fragment: Fragment, title: String) {
      requireActivity().supportFragmentManager.beginTransaction()
         .replace(R.id.fragmentContainer, fragment).addToBackStack(null).commit()
   }


}