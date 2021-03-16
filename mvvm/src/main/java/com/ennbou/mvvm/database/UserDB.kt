package com.ennbou.mvvm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ennbou.mvvm.dao.AddressDao
import com.ennbou.mvvm.dao.CompanyDao
import com.ennbou.mvvm.dao.GeoDao
import com.ennbou.mvvm.dao.UserDao
import com.ennbou.mvvm.entities.Address
import com.ennbou.mvvm.entities.Company
import com.ennbou.mvvm.entities.Geo
import com.ennbou.mvvm.entities.User

@Database(entities = [User::class, Address::class, Company::class, Geo::class], version = 1)
abstract class UserDB : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun addressDao(): AddressDao
    abstract fun companyDao(): CompanyDao
    abstract fun geoDao(): GeoDao

    companion object {
        @Volatile
        private var INSTANCE: UserDB? = null

        fun getDatabase(context: Context): UserDB {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDB::class.java,
                    "sqli_app_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}