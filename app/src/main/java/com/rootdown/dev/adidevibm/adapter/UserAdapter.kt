package com.rootdown.dev.adidevibm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rootdown.dev.adidevibm.R
import com.rootdown.dev.adidevibm.data.feature_random_user.models.UserX
import com.rootdown.dev.adidevibm.data.feature_random_user.repo.util.User
import com.rootdown.dev.adidevibm.databinding.LayoutUsersBinding

class UserAdapter(private val list : List<UserX>, private val navigationCallBack : (UserX) -> Unit ) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {


    class ViewHolder(val binding: LayoutUsersBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LayoutUsersBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(list[position]) {
                Glide.with(holder.binding.root).load(this.picture!!.medium)
                    .centerCrop()
                    .placeholder(R.drawable.ic_baseline_autorenew_24).into(binding.userImageView);
                binding.userNameTextView.text = "${this.name?.first} ${this.name?.last}"
                binding.userEmailTextView.text = this.email.toString()
                binding.mainLayout.setOnClickListener {
                    navigationCallBack.invoke(this)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}