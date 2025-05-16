package com.usama.uos.bssess2.Interfaces

import android.icu.text.Transliterator.Position
import android.view.View
import com.usama.uos.bssess2.Models.UserModel

interface UserItemsInterface {

   fun UserItemsClickListener(view:View? , userModel: UserModel , position: Int)

}