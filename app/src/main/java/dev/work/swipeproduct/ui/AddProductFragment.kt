package dev.work.swipeproduct.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope

import androidx.navigation.fragment.findNavController
import dev.work.swipeproduct.R
import dev.work.swipeproduct.databinding.FragmentAddProductBinding
import dev.work.swipeproduct.networking.Repository
import dev.work.swipeproduct.viewmodel.AddProductViewModel
import dev.work.swipeproduct.viewmodel.AddProductViewModelFactory
import kotlinx.coroutines.launch

private lateinit var addProductViewModel: AddProductViewModel
class AddProductFragment : Fragment() {

    private var _binding: FragmentAddProductBinding? = null
    private val binding
        get() = _binding!!

    private val repository: Repository by lazy {
        Repository()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddProductBinding.inflate(inflater, container, false)

        addProductViewModel = ViewModelProvider(this, AddProductViewModelFactory(repository))[AddProductViewModel::class.java]


        binding.fabCheck.setOnClickListener {

           AddProduct()

        }


        return binding.root
    }

private fun AddProduct(){
    viewLifecycleOwner.lifecycleScope.launch {
        val productPrice:Double = binding.etProductPrice.text.toString().toDouble()
        val productName:String = binding.etProductName.text.toString()
        val productType:String = binding.etProductType.text.toString()
        val productTax:Double = binding.etProductTax.text.toString().toDouble()
        addProductViewModel.pushPost2(productPrice,productName,productType,productTax)

        addProductViewModel.myResponse.observe(viewLifecycleOwner, Observer { response ->
            if(response.isSuccessful){
                Log.d("Main",response.body().toString())
                Log.d("Main",response.code().toString())
                Log.d("Main",response.message())
                findNavController().navigate(R.id.action_addProductFragment_to_productListFragment)
            }else{
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
            }
        })
    }

}

}