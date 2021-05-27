package com.example.searchable.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.searchable.data.User
import com.example.searchable.databinding.UserItemBinding
import javax.inject.Inject

class UserAdapter @Inject constructor() :  RecyclerView.Adapter<UserAdapter.UserViewHolder>() {


    var userList: ArrayList<User> = ArrayList()

    fun updateList(userList: ArrayList<User>) {

        this.userList = userList
    }

    class UserViewHolder(private val itemBinding: UserItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun binds(user: User) {
            itemBinding.txtUserFname.text = user.name
            itemBinding.txtUserDesc.text = user.desc;
            itemBinding.imgUserAvatar.scaleType= ImageView.ScaleType.FIT_XY
            Glide.with(itemBinding.root.context).load(user.image).into(itemBinding.imgUserAvatar)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding: UserItemBinding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        holder.binds(userList?.get(position)!!)
    }

    override fun getItemCount(): Int = if (userList.size > 0) userList.size else 0
}
