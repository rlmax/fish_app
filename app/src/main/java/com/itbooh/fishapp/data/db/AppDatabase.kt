package com.itbooh.fishapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.itbooh.fishapp.App
import com.itbooh.fishapp.data.model.Category
import com.itbooh.fishapp.data.model.Favourite
import com.itbooh.fishapp.data.model.Fish

@Database(entities = [Fish::class,Category::class,Favourite::class], version = AppDatabase.VERSION)

abstract class AppDatabase : RoomDatabase() {

    abstract fun fishDao(): FishDao
    companion object {
        const val DB_NAME = "fish.db"
        const val VERSION = 3
        private val instance: AppDatabase  by lazy { create(App.instance) }

        @Synchronized
        internal fun getInstance(): AppDatabase {
            return instance
        }

        private fun create(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }

    }
}