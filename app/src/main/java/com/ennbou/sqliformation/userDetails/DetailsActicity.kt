package com.ennbou.sqliformation.userDetails

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ennbou.sqliformation.R
import com.ennbou.sqliformation.data.users

class DetailsActicity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_acticity)

        val code:Int = intent.getIntExtra("code",1)
        val user = users.first{it.code == code}

        val userName: TextView = findViewById(R.id.user_name)
        val userAge:TextView = findViewById(R.id.user_age)
        val userEmail:TextView = findViewById(R.id.user_email)

        userName.text = user.name
        userAge.text = user.age.toString()
        userEmail.text = user.email


    }
}