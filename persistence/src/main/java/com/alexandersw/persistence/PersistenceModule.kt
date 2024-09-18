package com.alexandersw.persistence

import android.content.Context
import com.alexandersw.api.checkout.CheckoutLocalRepository
import com.alexandersw.persistence.checkout.CheckoutDao
import com.alexandersw.persistence.checkout.CheckoutLocalRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase{
        return AppDatabase.getDatabase(context)
    }

    @Singleton
    @Provides
    fun provideCheckoutDao(appDatabase: AppDatabase): CheckoutDao{
        return appDatabase.checkoutDao()
    }
}

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Provides
    fun provideCheckoutLocalRepository(checkoutDao: CheckoutDao): CheckoutLocalRepository{
        return CheckoutLocalRepositoryImpl(checkoutDao)
    }

}