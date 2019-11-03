package com.quantumhiggs.footballmatch.network

import com.quantumhiggs.footballmatch.model.Leagues
import retrofit2.Call
import retrofit2.http.GET

interface ApiServices {

    @GET("all_leagues.php")
    fun getAllLeagues(): Call<Leagues>

}