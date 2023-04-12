package dev.work.swipeproduct.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dev.work.swipeproduct.models.PostResponse
import dev.work.swipeproduct.networking.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class AddProductViewModel(private val repository: Repository):ViewModel() {

    var myResponse:MutableLiveData<Response<PostResponse>> = MutableLiveData()

    fun pushPost2(price:Double,product_name:String,product_type:String,tax:Double){
        viewModelScope.launch {
            val response = repository.pushPost2(price,product_name,product_type,tax)
            myResponse.value = response
        }
    }
}

class AddProductViewModelFactory(val repository: Repository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddProductViewModel(repository) as T
    }
}