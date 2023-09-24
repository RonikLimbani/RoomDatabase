package com.ronik.roomdatabasewithmvvm.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ronik.roomdatabasewithmvvm.databinding.ItemUserBinding
import com.ronik.roomdatabasewithmvvm.db.User
import com.ronik.roomdatabasewithmvvm.utils.ClickStatus

class UserAdapter(context: Context) :
    ListAdapter<User, UserAdapter.UserViewHolder>(UserDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }

    inner class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.userName.text = user.name
            binding.userEmail.text = user.email
            binding.userPhone.text = user.phoneNumber
            // Add any other binding logic here

            binding.editButton.setOnClickListener {
                onItemClickListener?.let { it(user, ClickStatus.EDIT) }
            }
            binding.deleteButton.setOnClickListener {
                onItemClickListener?.let { it(user, ClickStatus.DELETE) }
            }
        }
    }

    private var onItemClickListener: ((User, ClickStatus) -> Unit)? = null
    fun setOnItemClickListener(listener: (User, ClickStatus) -> Unit) {
        onItemClickListener = listener
    }
}
class UserDiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}
