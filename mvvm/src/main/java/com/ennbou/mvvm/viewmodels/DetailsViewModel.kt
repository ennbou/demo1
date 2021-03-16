package com.ennbou.mvvm.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ennbou.mvvm.database.UserDB
import com.ennbou.mvvm.entities.User
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {

    private val _user = MutableLiveData<User>()
    var user : LiveData<User> = _user

    fun fetchUser(context: Context, id: Long) {
        if (user.value == null) {
            viewModelScope.launch {
                val db = UserDB.getDatabase(context)
                val userDao = db.userDao()
                val userDb = userDao.get(id)
                _user.value = userDb
            }
        }
    }

}