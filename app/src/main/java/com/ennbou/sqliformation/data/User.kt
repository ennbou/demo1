package com.ennbou.sqliformation.data
import com.ennbou.sqliformation.R

data class User(val code: Int, val name: String = "FullName $code", val age:Int = 20+code, val email: String = "email$code@ennbou.com" , val image: Int = R.drawable.user)
