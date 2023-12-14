package com.example.devtestapp.presentation.contactsList

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.devtestapp.R
import com.example.devtestapp.databinding.ItemContactLayoutBinding
import com.example.devtestapp.domain.model.ContactsModel
import com.google.android.material.imageview.ShapeableImageView

class ContactsAdapter : RecyclerView.Adapter<ContactsAdapter.ContactViewHolder>() {

    private val contactsModel: ArrayList<ContactsModel> = ArrayList()
    private lateinit var mContext: Context
    var onItemClicked: ((ContactsModel) -> Unit)? = null

    inner class ContactViewHolder(binding: ItemContactLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val name = binding.tvName
        val email = binding.tvEmail
        val image = binding.ivUser
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        mContext = parent.context
        val viewBinding = ItemContactLayoutBinding.inflate(LayoutInflater.from(parent.context))

        return ContactViewHolder(viewBinding)
    }

    override fun getItemCount(): Int {
        return contactsModel.size
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contactsModel[position]

        holder.name.text = contact.name
        holder.email.text = contact.email
        getImageFromUrl(contact.pictureUrl, holder.image)

        holder.itemView.setOnClickListener { onItemClicked?.invoke(contact) }
    }

    fun refreshList(contacts: List<ContactsModel>) {
        contactsModel.run {
            clear()
            addAll(contacts)
            notifyDataSetChanged()
        }
    }

    private fun getImageFromUrl(url: String, image: ShapeableImageView) {

        val circularProgress = CircularProgressDrawable(mContext)
        circularProgress.strokeWidth = 5f
        circularProgress.centerRadius = 30f
        circularProgress.start()

        Glide.with(mContext)
            .load(url)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>,
                    isFirstResource: Boolean
                ): Boolean {
                    circularProgress.stop()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable>?,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    circularProgress.stop()
                    return false
                }

            })
            .circleCrop()
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .error(R.drawable.ic_error_load)
            .into(image)
    }
}