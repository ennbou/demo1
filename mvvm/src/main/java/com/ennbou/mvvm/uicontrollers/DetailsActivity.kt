package com.ennbou.mvvm.uicontrollers

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ennbou.mvvm.R
import com.ennbou.mvvm.database.UserDB
import com.ennbou.mvvm.entities.User
import com.ennbou.mvvm.viewmodels.DetailsViewModel
import com.squareup.picasso.Picasso

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val userId: Long = intent.getLongExtra("userid", 1)

        UserDB.getDatabase(this)

        val detailsViewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
        detailsViewModel.fetchUser(this, userId)


        val userName: TextView = findViewById(R.id.user_name)
        val userUsername: TextView = findViewById(R.id.user_username)
        val userEmail: TextView = findViewById(R.id.user_email)
        val userImage: ImageView = findViewById(R.id.user_image)

        detailsViewModel.user.observe(this, Observer<User> {
            userName.text = it.name
            userUsername.text = it.username
            userEmail.text = it.email
            Picasso.get().load("https://picsum.photos/id/${it.id * 10}/200")
                .placeholder(R.drawable.user).into(userImage)
        })


    }
}