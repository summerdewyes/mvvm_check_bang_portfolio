package com.summerdewyes.mvvm_check_bang.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.summerdewyes.mvvm_check_bang.databinding.ItemBookPreviewBinding
import com.summerdewyes.mvvm_check_bang.models.Item

class BookAdapter : ListAdapter<Item, BookAdapter.BookViewHolder>(differCallback) {

    private var onItemClickListener: ((Item) -> Unit)? = null

    inner class BookViewHolder(private val binding: ItemBookPreviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            Glide.with(binding.root).load(item.image).into(binding.ivBookImage)
            binding.tvTitle.text = item.title
            binding.tvAuthor.text = item.author
            binding.tvPublishedAt.text = item.publisher
            binding.tvPubdate.text = item.pubdate
            binding.rbBook.rating = item.rating

            itemView.setOnClickListener {
                onItemClickListener?.let { it(item) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BookViewHolder(
        ItemBookPreviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )


    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun setOnItemClickListener(listener: (Item) -> Unit) {
        onItemClickListener = listener
    }

    companion object {
        val differCallback = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem
            }
        }
    }


}