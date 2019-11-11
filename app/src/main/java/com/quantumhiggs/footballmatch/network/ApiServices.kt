package com.quantumhiggs.footballmatch.network

import com.quantumhiggs.footballmatch.model.Leagues
import com.quantumhiggs.footballmatch.model.Sports
import com.quantumhiggs.footballmatch.model.Teams
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("all_leagues.php")
    fun getAllLeagues(): Call<Leagues>

    @GET("lookupleague.php?")
    fun getDetailLeague(@Query("id") id: String): Call<Leagues>

    @GET("eventsnextleague.php?")
    fun getNextLeague(@Query("id") id: String): Call<Sports>

    @GET("eventspastleague.php?")
    fun getPrevLeague(@Query("id") id: String): Call<Sports>

    @GET("lookupevent.php?")
    fun getDetailMatch(@Query("id") id: String): Call<Sports>

    @GET("searchevents.php?")
    fun getSearchMatch(@Query("e") name: String): Call<Sports>

    @GET("lookupteam.php?")
    fun getTeamDetail(@Query("id") id: String): Call<Teams>

}