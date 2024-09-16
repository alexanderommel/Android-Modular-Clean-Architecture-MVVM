package com.alexandersw.stores_ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexandersw.api.checkout.Checkout
import com.alexandersw.api.checkout.CheckoutApiInteractor
import com.alexandersw.api.stores.Store
import com.alexandersw.api.stores.StoresApiInteractor
import com.alexandersw.common.UseCaseResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoresViewModel @Inject constructor (val api: StoresApiInteractor, val checkout_api: CheckoutApiInteractor): ViewModel() {

    companion object{
        const val TAG = "StoresViewModel"
    }

    private val _stores = MutableStateFlow<List<Store>>(emptyList())
    val stores: Flow<List<Store>> get() = _stores.asStateFlow()

    private val _checkouts = MutableStateFlow<List<Checkout>>(emptyList())
    val checkoutsFlow: Flow<List<Checkout>> = _checkouts.asStateFlow()


    init {
        fetchItems()
        viewModelScope.launch(Dispatchers.IO) {
            checkout_api.getAll().collect(){ checks ->
                _checkouts.value = checks
            }
        }

    }

    //suspend fun checkouts(): Flow<List<Checkout>> = checkout_api.getAll()

    private fun fetchItems() {
        System.out.println("fetch stores 1")
        Log.i(null,"Fetch stores 2")
        viewModelScope.launch(Dispatchers.IO) {
            val res = api.getStores()
            when(res){
                is UseCaseResponse.Success -> {
                    _stores.value = res.data
                }
                is UseCaseResponse.Error -> {
                    _stores.value = emptyList()
                }
            }
        }
    }

}