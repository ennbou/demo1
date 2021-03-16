package com.ennbou.mvvm.repositories

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ennbou.mvvm.api.UserService
import com.ennbou.mvvm.dao.AddressDao
import com.ennbou.mvvm.dao.CompanyDao
import com.ennbou.mvvm.dao.GeoDao
import com.ennbou.mvvm.dao.UserDao
import com.ennbou.mvvm.database.UserDB
import com.ennbou.mvvm.entities.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserRepo {

    val users: MutableLiveData<List<UserDetails>> = MutableLiveData()
    private lateinit var userDao: UserDao
    private lateinit var companyDao: CompanyDao
    private lateinit var addressDao: AddressDao
    private lateinit var geoDao: GeoDao

    suspend fun fetchUsers(context: Context) {

        val db = UserDB.getDatabase(context)

        userDao = db.userDao()
        companyDao = db.companyDao()
        addressDao = db.addressDao()
        geoDao = db.geoDao()
        withContext(Dispatchers.IO) {
            val usersDb = userDao.getAllDetails()
            if (usersDb.isNotEmpty()) {
                Log.d("USERS", "FROM DB")
                val newData = usersDb.map {
                    UserDetails(
                        id = it.user.id,
                        name = it.user.name,
                        username = it.user.username,
                        email = it.user.email,
                        phone = it.user.phone,
                        website = it.user.website,
                        company = null,
                        address = null
                    )
                }
                users.postValue(newData)
            } else {
                val call = UserService.fetchUsers()

                call.enqueue(object : Callback<List<UserDetails>> {
                    override fun onResponse(
                        call: Call<List<UserDetails>>,
                        response: Response<List<UserDetails>>
                    ) {
                        Log.d("USERS", "FROM API")
                        users.value = response.body()!!
                        CoroutineScope(Dispatchers.IO).launch{
                            save()
                        }
                    }

                    override fun onFailure(call: Call<List<UserDetails>>, t: Throwable) {
                        Log.d("USERS", "FAILED")
                    }

                })
            }



        }


    }

    suspend fun save() {
        withContext(Dispatchers.IO) {
            users.value?.forEach {
                val user = User(it.id, it.name, it.username, it.email, it.phone, it.website)
                val userId = userDao.insert(user)
                val add = it.address!!
                val addressId = addressDao.insert(
                    Address(
                        null,
                        add.street,
                        add.suite,
                        add.city,
                        add.zipcode,
                        userId
                    )
                )
                geoDao.insert(Geo(null, add.geo.lat, add.geo.lng, addressId))
                val comp = it.company!!
                companyDao.insert(Company(null, comp.name, comp.catchPhrase, comp.bs, userId))
            }
        }

    }

    suspend fun deleteUser(context: Context, id: Long){
        val db = UserDB.getDatabase(context)
        val userDao = db.userDao()
        userDao.delete(id)
    }
}