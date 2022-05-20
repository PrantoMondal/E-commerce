package com.example.ecomadmin

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import com.example.ecomadmin.customdialogs.DatePickerFragment
import com.example.ecomadmin.databinding.FragmentAddProductBinding
import com.example.ecomadmin.utils.getFormattedDate
import com.example.ecomadmin.viewmodels.ProductViewModel
import com.google.firebase.Timestamp

class AddProductFragment : Fragment() {

    private val productViewModel:ProductViewModel by activityViewModels()
    private lateinit var binding:FragmentAddProductBinding
    private var category:String? = null
    private var timeStamp: Timestamp? = null
    private var imageUrl: String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddProductBinding.inflate(inflater,container,false)

        productViewModel.getCategories().observe(viewLifecycleOwner){
            if (!it.isNullOrEmpty()){
                val adapter = ArrayAdapter<String>(requireActivity(),
                android.R.layout.simple_spinner_dropdown_item,it)
                binding.catSP.adapter = adapter
            }
        }
        binding.catSP.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                category = p0!!.getItemAtPosition(p2).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }


        }
        binding.dateBtn.setOnClickListener {
            DatePickerFragment {
                binding.dateTV.text = getFormattedDate(it.seconds,"dd/MM/yyyy")
            }.show(childFragmentManager,null)

        }
        binding.captureBtn.setOnClickListener {
            dispatchTakePictureIntent()
        }




        return binding.root
    }

    val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            val bitmap = it.data?.extras?.get("data") as Bitmap
            productViewModel.uploadImage(bitmap){downloadUrl ->

                imageUrl = downloadUrl
            }
        }
    }

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            resultLauncher.launch(takePictureIntent)
        } catch (e: ActivityNotFoundException) {
            // display error state to the user
        }
    }



}