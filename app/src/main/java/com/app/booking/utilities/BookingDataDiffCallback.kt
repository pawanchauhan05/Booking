package com.app.booking.utilities

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import com.app.booking.pojo.BookingData

class BookingDataDiffCallback(private val oldList: List<BookingData>, private val newList: List<BookingData>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].tripStartTime === newList[newItemPosition].tripStartTime
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return oldList[oldPosition] == newList[newPosition]
    }

    @Nullable
    override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
        return super.getChangePayload(oldPosition, newPosition)
    }

}