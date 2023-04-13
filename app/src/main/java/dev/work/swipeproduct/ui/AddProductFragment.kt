package dev.work.swipeproduct.ui

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.work.swipeproduct.R
import dev.work.swipeproduct.databinding.FragmentAddProductBinding
import dev.work.swipeproduct.networking.Repository
import dev.work.swipeproduct.utils.Snacker
import dev.work.swipeproduct.viewmodel.AddProductViewModel
import dev.work.swipeproduct.viewmodel.AddProductViewModelFactory
import dev.work.swipeproduct.viewmodel.ProductListViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import org.w3c.dom.Text
import java.io.File
import java.io.FileOutputStream
import java.lang.reflect.Type




@AndroidEntryPoint
class AddProductFragment : Fragment() {

    private var _binding: FragmentAddProductBinding? = null
    private val binding
        get() = _binding!!
    var imageUri: Uri? = null


    private val contract = registerForActivityResult(ActivityResultContracts.GetContent()) {
        imageUri = it!!
        binding.ivProduct.setImageURI(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddProductBinding.inflate(inflater, container, false)


        binding.fabCheck.setOnClickListener {
            if (binding.etProductName.text!!.isEmpty() || binding.etProductPrice.text!!.isEmpty() || binding.etProductType.text!!.isEmpty() || binding.etProductTax.text!!.isEmpty()) {
                Snacker(it, "Please enter all fields").error()
            } else {
                AddProduct()
            }

        }

        binding.cvProduct.setOnClickListener {
            contract.launch("image/*")
        }


        return binding.root
    }

    private fun AddProduct() {
        val addProductViewModel : AddProductViewModel by viewModels()

        viewLifecycleOwner.lifecycleScope.launch {
            val productPrice: Double = binding.etProductPrice.text.toString().toDouble()
            val productName: String = binding.etProductName.text.toString()
            val name: RequestBody = productName.toRequestBody("text/plain".toMediaTypeOrNull())
            val productType: String = binding.etProductType.text.toString()
            val type: RequestBody = productType.toRequestBody("text/plain".toMediaTypeOrNull())
            val productTax: Double = binding.etProductTax.text.toString().toDouble()
            val image: MultipartBody.Part?

            val fileDir = requireActivity().filesDir
            val file = File(fileDir, "image.png")

            if (imageUri == null) {
                addProductViewModel.postWithoutImage(productPrice, name, type, productTax)
            } else {

                val inputStream = requireActivity().contentResolver.openInputStream(imageUri!!)
                val outputStream = FileOutputStream(file)
                inputStream!!.copyTo(outputStream)

                val requestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
                image = MultipartBody.Part.createFormData("files[]", file.name, requestBody)
                addProductViewModel.pushPost2(productPrice, name, type, productTax, image)
            }

            addProductViewModel.myResponse.observe(viewLifecycleOwner, Observer { response ->
                if (response.isSuccessful) {
                    Log.d("Main", response.body().toString())
                    Log.d("Main", response.code().toString())
                    Log.d("Main", response.message())
                    findNavController().navigate(R.id.action_addProductFragment_to_productListFragment)
                    Toast.makeText(context, "Product Added", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
                }
            })
        }

    }


}