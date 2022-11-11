package com.bmk.baseproject.fragment

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.bmk.baseproject.databinding.ItemUserBinding
import com.bmk.domain.UserDetails


class UserAdapter(private val userDetails: List<UserDetails>,private val onClickListener: (UserDetails) -> Unit) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding, onClickListener)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(userDetails[position])
    }

    override fun getItemCount() = userDetails.size
    class UserViewHolder(
        private val binding: ItemUserBinding,
        val onClickListener: (UserDetails) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(userDetails: UserDetails) {
            binding.apply {
                root.setOnClickListener { onClickListener(userDetails) }
                tvId.text = userDetails.id.toString()
                tvEmail.text = userDetails.email
                tvName.text = userDetails.getFullName()
            }
        }
    }
}