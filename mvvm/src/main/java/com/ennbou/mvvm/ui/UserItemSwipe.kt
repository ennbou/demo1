package com.ennbou.mvvm.ui

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ennbou.mvvm.viewmodels.UserViewModel

class UserItemSwipe(
    private val adapter: UserAdapter,
    private val recyclerView: RecyclerView,
    private val viewModel: UserViewModel
) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: ViewHolder,
        target: ViewHolder
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
        adapter.deleteItem(recyclerView, viewHolder.adapterPosition, viewModel)
    }

}