package com.cs4520.assignment4

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cs4520.assignment4.databinding.ProductListFragmentBinding

class ProductListFragment : Fragment(){

    private var _binding: ProductListFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ProductViewModel
    private lateinit var viewModelFactory: ProductViewModelFactory

    private lateinit var dataAdapter: DataAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View {
        _binding = ProductListFragmentBinding.inflate(inflater, container, false)

        viewModelFactory = ProductViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory)[ProductViewModel::class.java]

        dataAdapter = DataAdapter()

        binding.recyclerview.adapter = dataAdapter

        initObserver()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setAdapterData(data : ArrayList<ProductData>){
        dataAdapter.setData(data)
        dataAdapter.notifyDataSetChanged()
    }

    private fun initObserver() {

        viewModel.ResponseData.observe(viewLifecycleOwner, Observer {
            if (viewModel.ResponseData.value != null) {
                binding.progressbar.visibility = View.GONE
                binding.textView.visibility = View.GONE
                setAdapterData(viewModel.ResponseData.value!!) //professor told me in office hours to move adapter here
                if (viewModel.ResponseData.value!!.size == 0) {
                    binding.textView.visibility = View.VISIBLE
                }
            } else {
                binding.progressbar.visibility = View.GONE
                binding.textView.visibility = View.VISIBLE
                val errormessage = "Error Fetching Data"
                binding.textView.text = errormessage
            }
        })
    }
}