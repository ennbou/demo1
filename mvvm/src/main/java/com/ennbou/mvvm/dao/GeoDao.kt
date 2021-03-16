package com.ennbou.mvvm.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.ennbou.mvvm.entities.Geo

@Dao
interface GeoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(geo: Geo) : Long
}