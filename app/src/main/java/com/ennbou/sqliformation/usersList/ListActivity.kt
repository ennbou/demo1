package com.ennbou.sqliformation.usersList

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ennbou.sqliformation.R
import com.ennbou.sqliformation.data.users

class ListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userList : RecyclerView = findViewById(R.id.users_list)

        val usersData = users

        val adapter = UserAdapter(usersData, this)

        userList.adapter = adapter

        userList.layoutManager = LinearLayoutManager(this)


    }
}