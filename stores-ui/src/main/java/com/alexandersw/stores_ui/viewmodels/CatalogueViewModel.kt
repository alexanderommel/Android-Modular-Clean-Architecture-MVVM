package com.alexandersw.stores_ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexandersw.api.stores.Catalogue
import com.alexandersw.api.stores.Store
import com.alexandersw.api.stores.StoresApiInteractor
import com.alexandersw.common.UseCaseResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CatalogueViewModel @Inject constructor(
    private val api: StoresApiInteractor
) : ViewModel() {

    // Equivalent to @Published in Swift
    private val _catalogueState = MutableStateFlow<Catalogue?>(null)
    val catalogueState: StateFlow<Catalogue?> = _catalogueState

    private val _errorLocalized = MutableStateFlow<String?>(null)
    val errorLocalized: StateFlow<String?> = _errorLocalized

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    // Function to fetch catalogue data from the store
    fun fetchCatalogueData(store: Store) {
        _isLoading.value = true
        _errorLocalized.value = null

        viewModelScope.launch {
            try {
                delay(1000)
                val response = api.getCatalogue(store)
                when (response) {
                    is UseCaseResponse.Success -> {
                        println("CatalogueViewModel: Fetch catalogue data Success")
                        _catalogueState.value = response.data
                        _isLoading.value = false
                    }
                    is UseCaseResponse.Error -> {
                        println("CatalogueViewModel: Fetch catalogue data Error")
                        _errorLocalized.value = response.failure.handleBusinessRuleFailure()
                        _isLoading.value = false
                    }
                }
            } catch (e: Exception) {
                _errorLocalized.value = e.localizedMessage
                _isLoading.value = false
            }
        }
    }
}
