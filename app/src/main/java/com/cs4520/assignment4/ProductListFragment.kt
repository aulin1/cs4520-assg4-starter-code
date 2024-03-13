package com.cs4520.assignment4

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cs4520.assignment4.databinding.ProductListFragmentBinding

class ProductListFragment : Fragment(){
    //TODO: deal with if API returns no products - error screen
    //TODO: get 30 items
    //TODO: match_parent width
    //TODO: coroutines

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

        binding.recyclerview.adapter = viewModel.getAdapter()

        initObserver()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initObserver() {

        viewModel.ResponseData.observe(viewLifecycleOwner, Observer {
            if (viewModel.ResponseData.value != null) {
                binding.progressbar.visibility = View.INVISIBLE
                viewModel.setAdapterData(viewModel.ResponseData.value!!)

            } else {
                Toast.makeText(requireContext(), "Error Fetching Data", Toast.LENGTH_LONG).show()
            }
        })
    }
}