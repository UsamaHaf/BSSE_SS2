package com.usama.uos.bssess2.Interfaces

import android.view.View
import com.usama.uos.bssess2.Models.ProductsModel

interface StoreInterfaces {

   fun addToCartClickListener(view: View?, stockModel: ProductsModel?, position: Int)

   fun onPlusClick(view: View?, stockModel: ProductsModel?, position: Int)

   fun onDeleteClick(view: View?, stockModel: ProductsModel?, position: Int)


}