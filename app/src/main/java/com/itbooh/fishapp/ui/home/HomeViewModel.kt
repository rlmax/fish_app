package com.itbooh.fishapp.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.itbooh.fishapp.App
import com.itbooh.fishapp.data.db.AppDatabase
import com.itbooh.fishapp.data.db.FishDao
import com.itbooh.fishapp.data.model.Category
import com.itbooh.fishapp.data.model.Fish
import com.itbooh.fishapp.data.model.FishDto
import com.itbooh.fishapp.data.network.ApiService

import com.itbooh.fishapp.ui.base.BaseViewModel
import com.itbooh.fishapp.utils.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(  val apiService: ApiService = App.api!!,
                      val appDatabase: AppDatabase = AppDatabase.getInstance()) : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
    private val TAG: String = HomeViewModel::class.java.simpleName
    val homeData: MutableLiveData<FishDto> by lazy { MutableLiveData<FishDto>() }
    val error : MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val showToast : SingleLiveEvent<String> by lazy { SingleLiveEvent<String>() }
    init {
        getFfish()
    }

    fun getFfish() {
        apiService.getHome().enqueue(object : Callback<FishDto?> {
            override fun onFailure(call: Call<FishDto?>?, t: Throwable?) {
                error.postValue("error happened")
            }

            override fun onResponse(call: Call<FishDto?>?, response: Response<FishDto?>?) {
                Log.d(TAG, "onResponse() called with: call = [$call], response = [$response]")
                Log.d(TAG,
                    "isSuccessful : " + response?.isSuccessful
                            + " message : " + response?.message()
                            + " code : " + response?.code()
                            + " raw : " + response?.raw()
                )

                if (response != null) {
                    if (response.isSuccessful) {
                        homeData.postValue(response.body())
                        showToast.postValue("data received (Toast shows one times)")
                        if(response.body()!!.resultCategory.size >0){
                            saveInCategoryDB(response.body()!!.resultCategory)
                        }
                        if (response.body()!!.results.size > 0) {
                            saveInDB(response.body()!!.results)
                        }

                    } else {
                        error.postValue(response.errorBody()!!.string())
                    }
                }
            }

        })

    }
    private fun saveInDB(results: MutableList<Fish>) {
        for (i in results) {
            Log.d(TAG, "${i.story_title} inserted to databade")
            appDatabase.fishDao().insertFish(i)

        }

    }
    private fun saveInCategoryDB(results: MutableList<Category>) {
        for (i in results) {
            Log.d(TAG, "${i.category_name} inserted to databade")
            appDatabase.fishDao().insertCategory(i)

        }

    }
}