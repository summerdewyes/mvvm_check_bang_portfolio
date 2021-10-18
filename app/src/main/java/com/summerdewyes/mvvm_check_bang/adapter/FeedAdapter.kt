package com.summerdewyes.mvvm_check_bang.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.summerdewyes.mvvm_check_bang.databinding.MainFeedPreviewBinding
import com.summerdewyes.mvvm_check_bang.models.Feed
import java.text.SimpleDateFormat
import java.util.*


class FeedAdapter : ListAdapter<Feed, FeedAdapter.FeedViewHolder>(differCallback) {

    inner class FeedViewHolder(private val binding: MainFeedPreviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(feed: Feed) {
            Glide.with(binding.root).load(feed.photo).into(binding.ivPhotoImage)
            binding.tvName.text = feed.name
            binding.tvPage.text = "[" + feed.page + " 장]"
            binding.tvContent.text = feed.content
            Glide.with(binding.root).load(feed.bookImage).into(binding.ivBookImage)

            val calendar = Calendar.getInstance().apply {
                timeInMillis = feed.timestamp
            }
            val dateFormat = SimpleDateFormat("yyyy/MM/yy", Locale.getDefault())
            binding.tvDate.text = dateFormat.format(calendar.time)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FeedViewHolder(
        MainFeedPreviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: FeedAdapter.FeedViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val differCallback = object : DiffUtil.ItemCallback<Feed>() {
            override fun areItemsTheSame(oldItem: Feed, newItem: Feed): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Feed, newItem: Feed): Boolean {
                return oldItem == newItem
            }
        }
    }

}

