package com.ennbou.mvvm.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.ennbou.mvvm.entities.Address

@Dao
interface AddressDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(address: Address) : Long
}