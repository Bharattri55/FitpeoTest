package com.test.fitpeo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.fitpeo.data.model.AlbumResponse
import com.test.fitpeo.databinding.ItemAlbumLayoutBinding
import java.util.ArrayList
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AlbumAdapter @Inject constructor() : RecyclerView.Adapter<AlbumAdapter.DataViewHolder>() {
    private val mUserList: ArrayList<AlbumResponse> = ArrayList()
    class DataViewHolder(val itemLayoutBinding: ItemAlbumLayoutBinding) : RecyclerView.ViewHolder(itemLayoutBinding.root) {
        fun bind(user: AlbumResponse) {
            itemLayoutBinding.textViewTitle.text=user.title
            Glide.with(itemLayoutBinding.root.context).load(user.url).into(itemLayoutBinding.imageViewUrl)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(ItemAlbumLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int = mUserList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(mUserList[position])
    }

    fun addUsers(users: List<AlbumResponse>) {
        this.mUserList.apply {
            addAll(users)
        }
    }
}