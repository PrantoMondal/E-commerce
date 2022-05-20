package com.example.ecomadmin.viewmodels

import androidx.lifecycle.ViewModel
import com.example.ecomadmin.repos.ProductRepository

class ProductViewModel:ViewModel() {
    val repository = ProductRepository()


    fun getCategories() = repository.getAllCategories()

}