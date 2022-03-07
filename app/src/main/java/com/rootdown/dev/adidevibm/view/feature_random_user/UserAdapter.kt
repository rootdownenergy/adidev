package com.rootdown.dev.adidevibm.view.feature_random_user

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.rootdown.dev.adidevibm.R
import com.rootdown.dev.adidevibm.databinding.GridViewItemBinding
import com.rootdown.dev.adidevibm.model.feature_random_user.db.Users


class UserAdapter: RecyclerView.Adapter<UserViewHolder>() {
    var users: List<Users> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.grid_view_item, parent, false)
        return UserViewHolder(view)
    }
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position])
    }
    override fun getItemCount() = users.size
}
class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private var user: Users? = null
    private val name: TextView = view.findViewById(R.id.txt_user_name)
    private val img: ImageView = view.findViewById(R.id.img_user)
    fun bind(repo: Users?){
        if (repo != null) {
            showRepo(repo)
        }
    }
    private fun showRepo(repo: Users){
        this.user = repo
        name.text = "XXXXXXXXX"
    }
}

