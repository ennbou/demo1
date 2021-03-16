package com.ennbou.mvvm.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.ennbou.mvvm.entities.Company

@Dao
interface CompanyDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(company: Company) : Long
}