package com.app.booking.ui.bookings

import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.booking.R
import com.app.booking.pojo.BookingData
import com.app.booking.utilities.BookingDataDiffCallback
import kotlinx.android.synthetic.main.booking_list_item_layout.view.*
import java.text.SimpleDateFormat
import java.util.*

class BookingAdapter (
    val itemList: MutableList<BookingData> = mutableListOf()
) : RecyclerView.Adapter<BookingAdapter.ViewHolder>() {

    inner class ViewHolder(containerView: View) : RecyclerView.ViewHolder(containerView) {

        fun bind(bookingData: BookingData) {

            itemView.textViewSource.text = "${bookingData.source}\nTime: ${SimpleDateFormat("HH:mm", Locale.ENGLISH).format(bookingData.tripStartTime)}"
            itemView.textViewDestination.text = bookingData.destination
            itemView.textViewDepartureTime.text = DateUtils.getRelativeTimeSpanString(bookingData.tripStartTime)
            itemView.textViewTravelTime.text = "Travel time: ${bookingData.tripDuration}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.booking_list_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun updateList(dataList: List<BookingData>) {
        val diffCallback = BookingDataDiffCallback(itemList, dataList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        itemList.clear()
        itemList.addAll(dataList)
        diffResult.dispatchUpdatesTo(this)
    }
}