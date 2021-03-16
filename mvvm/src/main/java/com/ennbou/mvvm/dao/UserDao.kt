package com.ennbou.mvvm.dao

import androidx.room.*
import com.ennbou.mvvm.entities.User
import com.ennbou.mvvm.entities.relations.UserAddressGeoCompany

@Dao
interface UserDao {
    @Query("SELECT * FROM USER")
    suspend fun getAll(): List<User>

    @Transaction
    @Query("SELECT * FROM User")
    suspend fun getAllDetails(): List<UserAddressGeoCompany>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User) : Long

    @Query("SELECT * FROM user WHERE id = :id")
    suspend fun get(id: Long): User

    @Query("DELETE FROM user WHERE id = :id ")
    suspend fun delete(id: Long): Int
}