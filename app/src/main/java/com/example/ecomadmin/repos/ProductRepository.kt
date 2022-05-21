package com.example.ecomadmin.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ecomadmin.models.Product
import com.example.ecomadmin.models.Purchase
import com.example.ecomadmin.utils.collectionCategory
import com.example.ecomadmin.utils.collectionProduct
import com.example.ecomadmin.utils.collectionPurchase
import com.google.firebase.firestore.FirebaseFirestore

class ProductRepository {

    val db = FirebaseFirestore.getInstance()

    fun addProduct(product: Product, purchase: Purchase, callback: (String) -> Unit) {
        val wb = db.batch()
        val productDocRef = db.collection(collectionProduct).document()
        val purchaseDocRef = db.collection(collectionPurchase).document()
        product.id = productDocRef.id
        purchase.purchaseId = purchaseDocRef.id
        purchase.productId = product.id

        wb.set(productDocRef, product)
        wb.set(purchaseDocRef, purchase)
        wb.commit().addOnSuccessListener {
            callback("Success")
        }.addOnFailureListener {
            callback("Failure")
        }
    }

    fun getAllCategories() : LiveData<List<String>> {
        val catLD = MutableLiveData<List<String>>()
        db.collection(collectionCategory)
            .addSnapshotListener { value, error ->
                if (error != null) {
                    return@addSnapshotListener
                }
                val tempList = mutableListOf<String>()
                for (doc in value!!.documents) {
                    tempList.add(doc.get("name").toString())
                }
                catLD.value = tempList
            }
        return catLD
    }
}