package com.app.booking.core

import android.app.Application
import com.app.booking.data.source.IDataRepository

class AppController : Application() {

    val dataRepository: IDataRepository
        get() = ServiceLocator.provideDataRepository(this)
}