package dev.work.swipeproduct.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.navigation.fragment.findNavController
import dev.work.swipeproduct.R
import dev.work.swipeproduct.databinding.FragmentAddProductBinding


class AddProductFragment : Fragment() {

    private var _binding: FragmentAddProductBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddProductBinding.inflate(inflater, container, false)

//        binding.btnList.setOnClickListener {
//
//       findNavController().navigate(R.id.action_addProductFragment_to_productListFragment)
//
//        }


        return binding.root
    }



}