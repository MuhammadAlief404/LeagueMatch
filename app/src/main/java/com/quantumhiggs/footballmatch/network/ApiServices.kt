package com.quantumhiggs.footballmatch.network

import com.quantumhiggs.footballmatch.model.*
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

    @GET("lookup_all_teams.php?")
    fun getListTeam(@Query("id") id: String): Call<Teams>

    @GET("lookuptable.php?")
    fun getClassement(@Query("l") id: String): Call<Standings>

    @GET("lookupplayer.php?")
    fun getPlayers(@Query("id") id: String): Call<Players>

    @GET("eventsnext.php?")
    fun getEventNextByTeam(@Query("id") id: String): Call<Sports>

    @GET("eventslast.php?")
    fun getEventPastByTeam(@Query("id") id: String): Call<Sports>

    @GET("searchteams.php?")
    fun getTeamByName(@Query("t") name: String): Call<Teams>

}