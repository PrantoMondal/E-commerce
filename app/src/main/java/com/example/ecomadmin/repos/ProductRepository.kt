package com.example.ecomadmin.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ecomadmin.utils.collectionCategory
import com.google.firebase.firestore.FirebaseFirestore

class ProductRepository {
    val db = FirebaseFirestore.getInstance()


    fun getAllCategories():LiveData<List<String>>{
        val catLD = MutableLiveData<List<String>>()
        db.collection(collectionCategory)
            .addSnapshotListener { value, error ->
            if (error!=null){
                return@addSnapshotListener
            }
            val tempList = mutableListOf<String>()
            for (doc in value!!.documents){
                tempList.add(doc.get("name").toString())

            }
            catLD.value = tempList
        }
        return catLD
    }

}