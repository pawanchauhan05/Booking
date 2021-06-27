package com.app.booking.core

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.Room
import com.app.booking.data.source.DataRepository
import com.app.booking.data.source.IDataRepository
import com.app.booking.data.source.local.AppDatabase
import com.app.booking.data.source.local.ILocalDataSource
import com.app.booking.data.source.local.LocalDataSource
import com.app.booking.data.source.remote.RemoteDataSource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ServiceLocator {

    private val lock = Any()
    private var database: AppDatabase? = null


    @Volatile
    var dataRepository: IDataRepository? = null
        @VisibleForTesting set

    fun provideDataRepository(context: Context): IDataRepository {
        synchronized(this) {
            return dataRepository ?: createDataRepository(context)
        }
    }

    private fun createDataRepository(context: Context): IDataRepository {
        val newRepo = DataRepository(createLocalDataSource(context), createRemoteDataSource())
        dataRepository = newRepo
        return newRepo
    }


    private fun createRemoteDataSource(): RemoteDataSource {
        val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://open-app1.herokuapp.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RemoteDataSource::class.java)
    }

    private fun createLocalDataSource(context: Context): ILocalDataSource {
        val database = database ?: createDataBase(context)
        return LocalDataSource(database.getBookingDataDao())
    }

    private fun createDataBase(context: Context): AppDatabase {
        val result = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java, "Booking.db"
        ).build()
        database = result
        return result
    }

    @VisibleForTesting
    fun resetRepository() {
        synchronized(lock) {
            // Clear all data to avoid test pollution.
            database?.apply {
                clearAllTables()
                close()
            }
            database = null
            dataRepository = null
        }
    }
}