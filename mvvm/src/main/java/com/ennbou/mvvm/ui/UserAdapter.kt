package com.ennbou.mvvm.ui

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.util.Log
import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ennbou.mvvm.R
import com.ennbou.mvvm.entities.UserDetails
import com.ennbou.mvvm.uicontrollers.DetailsActivity
import com.ennbou.mvvm.viewmodels.UserViewModel
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

class UserAdapter(val context: Context) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private var users: MutableList<UserDetails> = mutableListOf()
//
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = itemView.findViewById(R.id.user_name)
        val username: TextView = itemView.findViewById(R.id.user_username)
        val image: ImageView = itemView.findViewById(R.id.user_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]
        holder.name.text = user.name
        holder.username.text = "@${user.username}"

        Picasso.get()
            .load(
                "https://picsum.photos/300/300"
            )
            .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
            .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
            .placeholder(R.drawable.user)
            .into(holder.image)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailsActivity::class.java).apply {
                putExtra("userid", user.id)
            }

            val activityOption: ActivityOptions =
                ActivityOptions.makeSceneTransitionAnimation(
                    context as Activity,
                    Pair.create(holder.image, "img"),
                    Pair.create(holder.name, "name"),
                    Pair.create(holder.username, "username")
                )
            context.startActivity(intent, activityOption.toBundle())
        }

    }

    fun setAndRefresh(users: List<UserDetails>) {
        this.users = users.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemCount() = users.size

    fun deleteItem(layout: View, position: Int, viewModel: UserViewModel) {
        val user: UserDetails = users.removeAt(position)
        val snackbar = Snackbar.make(layout, "Removed " + user.name, Snackbar.LENGTH_LONG)
        snackbar.addCallback(object : BaseTransientBottomBar.BaseCallback<Snackbar>() {
            override fun onShown(transientBottomBar: Snackbar?) {
                Log.d("item", "deleting")
                notifyItemRemoved(position)
            }

            override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                if (event == Snackbar.Callback.DISMISS_EVENT_TIMEOUT) {
                    Log.d("item", "deleted")
                    viewModel.deleteUser(context, user.id)
                }
            }
        })
        snackbar.setAction("cancel") {
            Log.d("item", "remove canceled")
            users.add(position, user)
            notifyItemInserted(position)
        }
        snackbar.show()
    }


}