package com.itbooh.fishapp.data.network

import com.itbooh.fishapp.data.model.FishDto
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("api.php?get_home")
    fun getHome(
    ): Call<FishDto>


}