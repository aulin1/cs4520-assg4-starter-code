package com.cs4520.assignment4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.cs4520.assignment4.R
import com.cs4520.assignment4.databinding.LoginFragmentBinding

class LoginFragment : Fragment(){

    private var _binding: LoginFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View {
        _binding = LoginFragmentBinding.inflate(inflater, container, false)
        //bindings
        binding.loginButton.setOnClickListener {
            val username = binding.usernameField.text.toString()
            val password = binding.passwordField.text.toString()
            if(username == "admin" && password == "admin"){
                binding.usernameField.setText("")
                binding.passwordField.setText("")
                findNavController().navigate(R.id.action_loginFragment_to_productListFragment)
            } else {
                val text = "Username or password is incorrect."
                val duration = Toast.LENGTH_SHORT

                val toast = Toast.makeText(this.activity, text, duration)
                toast.show()
            }
        }

        return binding.root
    }
}