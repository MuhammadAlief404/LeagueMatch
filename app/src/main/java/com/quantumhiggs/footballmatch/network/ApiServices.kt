package com.quantumhiggs.footballmatch.network

import com.quantumhiggs.footballmatch.model.Leagues
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("all_leagues.php")
    fun getAllLeagues(): Call<Leagues>

    @GET("lookupleague.php?")
    fun getDetailLeague(@Query("id") id: String): Call<Leagues>

}