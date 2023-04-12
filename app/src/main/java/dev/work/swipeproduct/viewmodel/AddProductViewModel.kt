package dev.work.swipeproduct.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dev.work.swipeproduct.models.PostResponse
import dev.work.swipeproduct.networking.Repository
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response

class AddProductViewModel(private val repository: Repository):ViewModel() {

    var myResponse:MutableLiveData<Response<PostResponse>> = MutableLiveData()

    fun pushPost2(price:Double,product_name:RequestBody,product_type:RequestBody,tax:Double,image:MultipartBody.Part){
        viewModelScope.launch {
            val response = repository.pushPost2(price,product_name,product_type,tax,image)
            myResponse.value = response
        }
    }
}

class AddProductViewModelFactory(val repository: Repository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddProductViewModel(repository) as T
    }
}