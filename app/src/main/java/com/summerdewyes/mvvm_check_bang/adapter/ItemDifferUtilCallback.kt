package com.summerdewyes.mvvm_check_bang.adapter

import androidx.recyclerview.widget.DiffUtil
import com.summerdewyes.mvvm_check_bang.models.Item


class ItemDifferUtilCallback: DiffUtil.ItemCallback<Item>() {

    /**
     * 두 아이템이 동일한 아이템인지 체크한다.
     */
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.id == newItem.id
    }

    /**
     * 두 아이템이 동일한 내용물을 가지고 있는지 체크한다, areItemsTheSame()가 true일 때만 호출.
     */
    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }
}