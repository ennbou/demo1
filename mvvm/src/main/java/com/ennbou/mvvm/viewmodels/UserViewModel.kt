package com.ennbou.mvvm.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ennbou.mvvm.entities.UserDetails
import com.ennbou.mvvm.repositories.UserRepo
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private val repo = UserRepo()
    private val _users = repo.users
    var users: LiveData<List<UserDetails>> = _users

    fun getUsers(context: Context) {
        if (users.value == null) {
            viewModelScope.launch {
                repo.fetchUsers(context)
            }
        }
    }

    fun deleteUser(context: Context, id: Long) {
        viewModelScope.launch {
            repo.deleteUser(context, id)
            getUsers(context)
        }
    }

}
