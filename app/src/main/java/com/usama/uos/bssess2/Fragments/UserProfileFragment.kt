package com.usama.uos.bssess2.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.usama.uos.bssess2.R


class UserProfileFragment : Fragment() {


   override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
      // Inflate the layout for this fragment
      return inflater.inflate(R.layout.fragment_user_profile, container, false)
   }

}