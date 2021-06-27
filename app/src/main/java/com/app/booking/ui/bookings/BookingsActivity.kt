package com.app.booking.ui.bookings

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.booking.R
import com.app.booking.core.AppController
import com.app.booking.pojo.Result
import com.app.booking.utilities.visibility
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_bookings.*
import java.util.*
import java.util.concurrent.TimeUnit

class BookingsActivity :  AppCompatActivity() {

    private val beg : CompositeDisposable = CompositeDisposable()

    private lateinit var bookingAdapter: BookingAdapter

    private val bookingsViewModel by viewModels<BookingsViewModel> {
        BookingsViewModelFactory((applicationContext as AppController).dataRepository)
    }

    private val linearLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(this@BookingsActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookings)

        bookingAdapter = BookingAdapter(mutableListOf())

        recyclerView.apply {
            layoutManager = linearLayoutManager
            itemAnimator = DefaultItemAnimator()
            adapter = bookingAdapter
        }

        initObserver()

        bookingsViewModel.getBookings(Calendar.getInstance().time)

        imageViewBackPress.setOnClickListener {
            finish()
        }

        initTimer()
    }

    private fun initObserver() {

        bookingsViewModel.dataLoading.observe(this, {
            progressBar.visibility(it)
        })

        bookingsViewModel.empty.observe(this, {
            when(bookingAdapter.itemList.isNotEmpty()) {
                true -> {
                    recyclerView.visibility(true)
                    textViewError.visibility(false)
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }

                false -> {
                    textViewError.apply {
                        text = it.message
                        visibility = View.VISIBLE
                    }
                    recyclerView.visibility(false)
                }
            }
        })

        bookingsViewModel.bookingList.observe(this, {
            when(it) {
                is Result.Success -> {
                    recyclerView.visibility(true)
                    textViewError.visibility(false)
                    bookingAdapter.updateList(it.dataList)
                }
                is Result.Failure -> {
                    textViewError.apply {
                        text = it.exception.message
                        visibility = View.VISIBLE
                    }
                    recyclerView.visibility(false)
                }
            }
        })
    }

    private fun initTimer() {
        Observable
            .interval(60, TimeUnit.SECONDS)
            .subscribe({
                bookingsViewModel.getBookings(Calendar.getInstance().time)
            }, {
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
            }).run {
                beg.add(this)
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        beg.clear()
    }
}