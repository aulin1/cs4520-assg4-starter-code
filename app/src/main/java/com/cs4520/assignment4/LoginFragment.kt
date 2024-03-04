package com.cs4520.assignment4

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class LoginFragment : Fragment(R.layout.login_fragment){

    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var loginButton: Button
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        username = view.findViewById(R.id.username_field)
        password = view.findViewById(R.id.password_field)
        loginButton = view.findViewById(R.id.login_button)
        loginButton.setOnClickListener {
            if(username.text.toString().equals("admin") && password.text.toString().equals("admin")){
                username.setText("")
                password.setText("")
                findNavController().navigate(R.id.action_loginFragment_to_productListFragment)
            } else {
                username.setText("")
                password.setText("")

                val text = "Username or password is incorrect."
                val duration = Toast.LENGTH_SHORT

                val toast = Toast.makeText(this.activity, text, duration)
                toast.show()
            }
        }
    }
}