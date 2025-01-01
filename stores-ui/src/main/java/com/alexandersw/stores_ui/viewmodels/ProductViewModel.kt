package com.alexandersw.stores_ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexandersw.api.checkout.CheckoutApiInteractor
import com.alexandersw.api.dto.LineItemDto
import com.alexandersw.api.stores.Product
import com.alexandersw.api.stores.StoresApiInteractor
import com.alexandersw.common.UseCaseResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val api: CheckoutApiInteractor,
    private val storesApi: StoresApiInteractor
) : ViewModel() {

    // StateFlow to manage the error message
    private val _errorLocalized = MutableStateFlow<String?>(null)
    val errorLocalized: StateFlow<String?> get() = _errorLocalized

    // StateFlow to manage the loading state
    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    // StateFlow to manage whether the item has been added
    private val _itemAdded = MutableStateFlow(false)
    val itemAdded: StateFlow<Boolean> get() = _itemAdded

    private val _productState = MutableStateFlow<Product?>(null)
    val productState: StateFlow<Product?> = _productState

    fun loadProduct(productId: Int){
        viewModelScope.launch(Dispatchers.IO) {

            _isLoading.value = true

            val response = storesApi.getProductById(productId)
            when (response) {
                is UseCaseResponse.Error -> {
                    _errorLocalized.value = response.failure.handleBusinessRuleFailure()
                    _isLoading.value = false
                }
                is UseCaseResponse.Success -> {
                    _isLoading.value = false
                    _productState.value = response.data
                }
            }
        }
    }

    // Function to add a line item to the shopping cart
    fun addLineItemToShoppingCart(storeId: Int, lineItem: LineItemDto) {
        _isLoading.value = true
        _errorLocalized.value = null

        viewModelScope.launch {
            val response = api.addLineItem(lineItem, storeId)
            when (response) {
                is UseCaseResponse.Error -> {
                    _errorLocalized.value = response.failure.handleBusinessRuleFailure()
                    _isLoading.value = false
                }
                is UseCaseResponse.Success -> {
                    _isLoading.value = false
                    _itemAdded.value = true
                }
            }
        }
    }
}
