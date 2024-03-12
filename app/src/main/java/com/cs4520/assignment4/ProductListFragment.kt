package com.cs4520.assignment4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cs4520.assignment4.databinding.ProductListFragmentBinding

class ProductListFragment : Fragment(){ //TODO: remember to check xml files for hardcoded strings

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, ProductViewModelFactory())[ProductViewModel::class.java]

        //TODO: fix

        initObserver()

    }

    private fun initObserver() {

        viewModel.ResponseData.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                binding.progressbar.visibility = View.INVISIBLE
                viewModel.setAdapterData(it.items)

            } else {
                Toast.makeText(requireContext(), "Error Fetching Data", Toast.LENGTH_LONG).show()
            }
        })
    }
}