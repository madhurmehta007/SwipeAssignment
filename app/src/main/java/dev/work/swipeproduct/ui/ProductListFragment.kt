package dev.work.swipeproduct.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import dev.work.swipeproduct.R
import dev.work.swipeproduct.adapter.ProductListAdapter
import dev.work.swipeproduct.databinding.FragmentAddProductBinding
import dev.work.swipeproduct.databinding.FragmentProductListBinding
import dev.work.swipeproduct.models.ProductData
import dev.work.swipeproduct.models.ProductDataItem
import dev.work.swipeproduct.networking.Repository
import dev.work.swipeproduct.viewmodel.ProductListViewModel
import dev.work.swipeproduct.viewmodel.ProductViewModelFactory
import kotlinx.coroutines.launch

private lateinit var productListViewModel: ProductListViewModel
private lateinit var productListAdapter: ProductListAdapter

class ProductListFragment : Fragment() {
    private var _binding: FragmentProductListBinding? = null
    private val binding
        get() = _binding!!


    private val repository: Repository by lazy {
        Repository()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductListBinding.inflate(inflater, container, false)

        productListViewModel = ViewModelProvider(this, ProductViewModelFactory(repository))[ProductListViewModel::class.java]

        productListViewModel.getProducts()

        viewLifecycleOwner.lifecycleScope.launch {
            productListViewModel.productResponse.observe(viewLifecycleOwner, Observer {
                val pData : MutableList<ProductDataItem> =  it.body()?.toMutableList() as MutableList<ProductDataItem>

                productListAdapter = ProductListAdapter(requireContext(),pData)

                val adapter = productListAdapter

                adapter.notifyDataSetChanged()
                binding.rvProducts.setHasFixedSize(true)
                binding.rvProducts.adapter = adapter
                binding.rvProducts.layoutManager = GridLayoutManager(context,2)
                adapter.notifyDataSetChanged()
            })
        }

        binding.fabAddProduct.setOnClickListener {
            findNavController().navigate(R.id.action_productListFragment_to_addProductFragment)

        }

        return binding.root
    }

}