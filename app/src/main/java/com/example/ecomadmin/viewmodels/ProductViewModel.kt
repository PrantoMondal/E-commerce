package com.example.ecomadmin.viewmodels

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import com.example.ecomadmin.repos.ProductRepository
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream
import javax.security.auth.callback.Callback

class ProductViewModel:ViewModel() {
    val repository = ProductRepository()


    fun getCategories() = repository.getAllCategories()
    fun uploadImage(bitmap: Bitmap, callback: (String)->Unit) {
        fun uploadImage(id: String, bitmap: Bitmap) {
            val photoRef = FirebaseStorage.getInstance().reference
                .child("images/${System.currentTimeMillis()}")
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 75, baos)
            val data: ByteArray = baos.toByteArray()
            val uploadTask = photoRef.putBytes(data)
            val urlTask = uploadTask.continueWithTask { task ->
                if (!task.isSuccessful) {
                    task.exception?.let {

                        throw it
                    }
                }
                photoRef.downloadUrl
            }.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val downloadUri = task.result.toString()
                   callback(downloadUri)
                } else {
                    // Handle failures
                    // ...
                }
            }

        }

    }

}