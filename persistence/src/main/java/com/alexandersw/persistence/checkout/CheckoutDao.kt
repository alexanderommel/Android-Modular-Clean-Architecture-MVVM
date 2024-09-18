package com.alexandersw.persistence.checkout

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CheckoutDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCheckout(checkout: CheckoutEntity): Long

    @Update
    suspend fun updateCheckout(checkout: CheckoutEntity): Int

    @Query("SELECT * FROM checkout_table WHERE userId = :userId")
    suspend fun getAllCheckoutsByUserId(userId: String): List<CheckoutEntity>

    @Query("SELECT * FROM checkout_table WHERE userId = :userId")
    fun flowOfAll(userId: String): Flow<List<CheckoutEntity>>
}
