package com.ennbou.sqliformation.usersList

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ennbou.sqliformation.R
import com.ennbou.sqliformation.data.User
import com.ennbou.sqliformation.userDetails.DetailsActicity

class UserAdapter(val users: List<User>, val context: Context) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = itemView.findViewById(R.id.user_name)
        val age: TextView = itemView.findViewById(R.id.user_age)
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
        holder.age.text = user.age.toString()
        holder.image.setImageResource(user.image)
        holder.itemView.setOnClickListener {
            val intent: Intent = Intent(context, DetailsActicity::class.java).apply {
                putExtra("code", user.code)
            }
            val activityOption: ActivityOptions = ActivityOptions.makeSceneTransitionAnimation(
                context as Activity,
                holder.image,
                "img"
            )
            context.startActivity(intent, activityOption.toBundle())
        }

    }

    override fun getItemCount() = users.size
}