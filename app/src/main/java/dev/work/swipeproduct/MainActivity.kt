package dev.work.swipeproduct

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.work.swipeproduct.databinding.ActivityMainBinding
import dev.work.swipeproduct.ui.AddProductFragment
import dev.work.swipeproduct.ui.ProductListFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        window.setStatusBarColor(this.getResources().getColor(R.color.material_blue))
        setContentView(binding.root)

    }

}