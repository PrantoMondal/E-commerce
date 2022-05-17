package com.example.ecomadmin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ecomadmin.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    private lateinit var binding:FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater,container,false)

        binding.loginBtn.setOnClickListener {
            val email = binding.emailET.text.toString()
            val pass = binding.passwordET.text.toString()
            // TODO: Validate fields

        }
        return binding.root
    }

}