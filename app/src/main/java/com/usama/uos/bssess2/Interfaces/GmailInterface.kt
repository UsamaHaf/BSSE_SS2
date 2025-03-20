package com.usama.uos.bssess2.Interfaces

import android.view.View
import com.usama.uos.bssess2.Models.GmailModel

interface GmailInterface {

   fun gmailItemClickListener(view: View , gmailModel: GmailModel , position:Int)

}