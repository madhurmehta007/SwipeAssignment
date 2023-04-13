package dev.work.swipeproduct.viewmodel

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.work.swipeproduct.models.ProductData
import dev.work.swipeproduct.models.ProductDataItem
import dev.work.swipeproduct.networking.Repository
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(private val repository:Repository):ViewModel() {

    private val _productResponse = MutableLiveData<Response<List<ProductDataItem>>>()
    val productResponse: MutableLiveData<Response<List<ProductDataItem>>> get() = _productResponse

    fun getProducts(){
        viewModelScope.launch {
            val pResponse = repository.getLatestProducts()
            _productResponse.value = pResponse
        }
    }
}

class ProductViewModelFactory(val repository: Repository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductListViewModel(repository) as T
    }
}