package com.usama.uos.bssess2.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.usama.uos.bssess2.Adapter.ProductAdapter
import com.usama.uos.bssess2.Adapter.UserAdapter
import com.usama.uos.bssess2.Interfaces.StoreInterfaces
import com.usama.uos.bssess2.Models.ProductsModel
import com.usama.uos.bssess2.R

class ShowProductsFragment : Fragment(), StoreInterfaces {
    
   lateinit var rvProducts:RecyclerView
   lateinit var productArrayList: ArrayList<ProductsModel>
   lateinit var productAdapter: ProductAdapter
   lateinit var firebaseDatabase: FirebaseDatabase
   lateinit var firebaseReference: DatabaseReference

   override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
      // Inflate the layout for this fragment
      val view = inflater.inflate(R.layout.fragment_show_products, container, false)
   
      rvProducts = view.findViewById(R.id.rvProducts)
      getProducts()
      
      
      return view
   
   }

   private fun getProducts() {

      firebaseDatabase = FirebaseDatabase.getInstance()
      firebaseReference = firebaseDatabase.getReference("Products")

      firebaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
         override fun onDataChange(snapshot: DataSnapshot) {
            try {
               productArrayList = ArrayList()
               for (eachUser in snapshot.children) {
                  val productsModel: ProductsModel? = eachUser.getValue(ProductsModel::class.java)
                  productArrayList.add(productsModel!!)

               }
               if (productArrayList.isEmpty() || productArrayList.size == 0) {

                  Toast.makeText(requireActivity(), "No Data Found", Toast.LENGTH_SHORT).show()

               } else {
                  productAdapter =
                      ProductAdapter(productArrayList, requireActivity(), this@ShowProductsFragment)
                  rvProducts.adapter = productAdapter
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

   override fun addToCartClickListener(view: View?, stockModel: ProductsModel?, position: Int) {

      Toast.makeText(requireActivity(), "Item Added: ${stockModel?.productName}", Toast.LENGTH_SHORT).show()

   }

   override fun onPlusClick(view: View?, stockModel: ProductsModel?, position: Int) {
      TODO("Not yet implemented")
   }

   override fun onDeleteClick(view: View?, stockModel: ProductsModel?, position: Int) {
      TODO("Not yet implemented")
   }


}