package dev.work.swipeproduct.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
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
import java.util.*



class ProductListFragment : Fragment() {
    private var _binding: FragmentProductListBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var productListViewModel: ProductListViewModel
    private lateinit var productListAdapter: ProductListAdapter
    var pData = ArrayList<ProductDataItem>()

    private val repository: Repository by lazy {
        Repository()
    }

    @SuppressLint("NotifyDataSetChanged", "SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductListBinding.inflate(inflater, container, false)

        productListViewModel = ViewModelProvider(this, ProductViewModelFactory(repository))[ProductListViewModel::class.java]

        productListViewModel.getProducts()

        val loadProgress = binding.shimmerEffect

            productListViewModel.productResponse.observe(viewLifecycleOwner, Observer {
                pData  =  it.body() as ArrayList<ProductDataItem>

                loadProgress.visibility = View.GONE

                productListAdapter = ProductListAdapter(requireContext(),pData)

                val adapter = productListAdapter

                adapter.notifyDataSetChanged()
                binding.rvProducts.setHasFixedSize(true)
                binding.rvProducts.adapter = adapter
                binding.rvProducts.layoutManager = GridLayoutManager(context,2)
                adapter.notifyDataSetChanged()


            })


        binding.fabAddProduct.setOnClickListener {
            findNavController().navigate(R.id.action_productListFragment_to_addProductFragment)

        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }

        })

        return binding.root
    }

    private fun filterList(query: String?) {
        if (query != null) {
            val filteredList = ArrayList<ProductDataItem>()
            for (i in pData) {
                if (i.product_name.lowercase(Locale.ROOT).contains(query.lowercase(Locale.ROOT))) {
                    filteredList.add(i)
                }
            }

            if (filteredList.isEmpty()) {
                binding.rvProducts.visibility = View.INVISIBLE
                binding.lottie.visibility = View.VISIBLE
                binding.tvProductNotFound.visibility = View.VISIBLE
            } else {
                binding.lottie.visibility = View.INVISIBLE
                binding.tvProductNotFound.visibility = View.INVISIBLE
                binding.rvProducts.visibility = View.VISIBLE
                productListAdapter.setFilteredList(filteredList)

            }
        }
    }
}