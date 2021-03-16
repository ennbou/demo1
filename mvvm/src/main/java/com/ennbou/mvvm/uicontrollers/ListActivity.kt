package com.ennbou.mvvm.uicontrollers

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ennbou.mvvm.R
import com.ennbou.mvvm.ui.UserAdapter
import com.ennbou.mvvm.ui.UserItemSwipe
import com.ennbou.mvvm.viewmodels.UserViewModel


class ListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val recyclerView: RecyclerView = findViewById(R.id.user_list)
        val userListRefresh: SwipeRefreshLayout = findViewById(R.id.user_list_refresh)

        val adapter = UserAdapter(this)

        userViewModel.getUsers(this)

        userViewModel.users.observe(this, Observer {
            adapter.setAndRefresh(it)
            Log.d("USERS Update", "Done")
        })

        userListRefresh.setOnRefreshListener {
            adapter.notifyDataSetChanged()
            userListRefresh.isRefreshing = false
        }

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        val itemTouchHelper = ItemTouchHelper(UserItemSwipe(adapter,recyclerView, userViewModel))
        itemTouchHelper.attachToRecyclerView(recyclerView)

    }
}