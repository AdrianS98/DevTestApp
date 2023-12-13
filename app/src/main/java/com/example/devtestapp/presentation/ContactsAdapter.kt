package com.example.devtestapp.presentation

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.devtestapp.databinding.ItemContactLayoutBinding

class ContactsAdapter: RecyclerView.Adapter<ContactsAdapter.ContactViewHolder>() {


    inner class ContactViewHolder(binding: ItemContactLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val name = binding.tvName
        val email = binding.tvEmail
        val image = binding.ivUser
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}