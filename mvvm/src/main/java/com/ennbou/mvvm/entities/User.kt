package com.ennbou.mvvm.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey var id: Long,
    var name: String,
    var username: String,
    var email: String,
    var phone: String,
    var website: String
)

