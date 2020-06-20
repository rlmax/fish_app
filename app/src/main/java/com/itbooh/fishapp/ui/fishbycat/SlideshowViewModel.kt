package com.itbooh.fishapp.ui.fishbycat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.itbooh.fishapp.App
import com.itbooh.fishapp.data.db.AppDatabase
import com.itbooh.fishapp.data.network.ApiService

class SlideshowViewModel(val apiService: ApiService = App.api!!,
                         val appDatabase: AppDatabase = AppDatabase.getInstance()) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is slideshow Fragment"
    }
    val text: LiveData<String> = _text
}