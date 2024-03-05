package com.cs4520.assignment4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.cs4520.assignment4.databinding.LoginFragmentBinding
import com.cs4520.assignment4.databinding.ProductListFragmentBinding

class ProductListFragment : Fragment(){

    private var _binding: ProductListFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ProductViewModel
    private lateinit var viewModelFactory: ProductViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View {
        _binding = ProductListFragmentBinding.inflate(inflater, container, false)

        viewModelFactory = ProductViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory)[ProductViewModel::class.java]

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

/*"Use Recyclerview, MVVM, livedata & constraint layouts wherever applicable.
Data should be loaded on when the user navigates to the Product list fragment. Load data using retrofit and coroutines.
For Loading data :
While the data is loading, the user should see an infinite progress bar.
On success, present it to the UI and save it to the database.
If api returns 0-zero results, display a message on screen “No products available”.
Json can have repeated products.This should not be shown on UI.
Products in json can have empty or missing data. This should not be shown on UI.
When offline, data should be fetched from the database if available and displayed on UI"

 "Dates for Foods missing. Should've made the visibility as View.VISIBLE for the TextView" */