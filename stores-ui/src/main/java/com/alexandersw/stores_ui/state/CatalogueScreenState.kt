package com.alexandersw.stores_ui.state

import com.alexandersw.api.stores.Catalogue
import com.alexandersw.api.stores.Store

sealed class CatalogueScreenState {
    data class StoreLoaded(val store: Store) : CatalogueScreenState()
    data class CatalogueLoaded(val catalogue: Catalogue) : CatalogueScreenState()
}