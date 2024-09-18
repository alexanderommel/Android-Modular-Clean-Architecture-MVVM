package com.alexandersw.dandelion

import com.alexandersw.api.checkout.CheckoutApiInteractor
import com.alexandersw.api.checkout.CheckoutLocalRepository
import com.alexandersw.api.stores.StoresApiInteractor
import com.alexandersw.api.user.UserApiInteractor
import com.alexandersw.checkout.api.CheckoutApiInteractorImpl
import com.alexandersw.persistence.checkout.CheckoutLocalRepositoryImpl
import com.alexandersw.stores.api.StoresApiInteractorImpl
import com.alexandersw.testing.StoreRemoteRepositoryFaker
import com.alexandersw.testing.UserApiInteractorFaker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun providesUserApi(): UserApiInteractor{
        return UserApiInteractorFaker()
    }

    @Provides
    fun providesStoresApi(userApiInteractor: UserApiInteractor): StoresApiInteractor{
        return StoresApiInteractorImpl(StoreRemoteRepositoryFaker(),userApiInteractor)
    }

    @Provides
    fun providesCheckoutApi(storesApiInteractor: StoresApiInteractor, userApiInteractor: UserApiInteractor, checkoutLocalRepository: CheckoutLocalRepository): CheckoutApiInteractor{
        return CheckoutApiInteractorImpl(storesApiInteractor, userApiInteractor, checkoutLocalRepository)
    }

}