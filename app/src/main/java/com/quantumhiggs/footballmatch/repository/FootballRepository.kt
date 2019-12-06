package com.quantumhiggs.footballmatch.repository

import com.quantumhiggs.footballmatch.model.Leagues
import com.quantumhiggs.footballmatch.model.Sports
import com.quantumhiggs.footballmatch.network.NetworkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FootballRepository {

    fun getListLeague(callback: FootballRepositoryCallback<Leagues?>) {
        NetworkConfig
            .api()
            .getAllLeagues()
            .enqueue(object : Callback<Leagues> {
                override fun onFailure(call: Call<Leagues>, t: Throwable) {
                    callback.onDataError()
                }

                override fun onResponse(call: Call<Leagues>, response: Response<Leagues>) {
                    response.let {
                        if (it.isSuccessful) {
                            callback.onDataLoaded(response.body())
                        } else {
                            callback.onDataError()
                        }
                    }
                }
            })
    }

    fun getDetailLeague(leagueId: String, callback: FootballRepositoryCallback<Leagues?>) {
        NetworkConfig
            .api()
            .getDetailLeague(leagueId)
            .enqueue(object : Callback<Leagues> {
                override fun onFailure(call: Call<Leagues>, t: Throwable) {
                    callback.onDataError()
                }

                override fun onResponse(call: Call<Leagues>, response: Response<Leagues>) {
                    if (response.isSuccessful) {
                        callback.onDataLoaded(response.body())
                    } else {
                        callback.onDataError()
                    }
                }
            })
    }

    fun getListMatch(matchName: String, callback: FootballRepositoryCallback<Sports?>) {
        NetworkConfig
            .api()
            .getSearchMatch(matchName)
            .enqueue(object : Callback<Sports> {
                override fun onFailure(call: Call<Sports>, t: Throwable) {
                    callback.onDataError()
                }

                override fun onResponse(call: Call<Sports>, response: Response<Sports>) {
                    if (response.isSuccessful) {
                        callback.onDataLoaded(response.body())
                    } else {
                        callback.onDataError()
                    }
                }
            })
    }

    fun getListPrevMatch(leaugeId: String, callback: FootballRepositoryCallback<Sports?>) {
        NetworkConfig
            .api()
            .getPrevLeague(leaugeId)
            .enqueue(object : Callback<Sports> {
                override fun onFailure(call: Call<Sports>, t: Throwable) {
                    callback.onDataError()
                }

                override fun onResponse(call: Call<Sports>, response: Response<Sports>) {
                    if (response.isSuccessful) {
                        callback.onDataLoaded(response.body())
                    } else {
                        callback.onDataError()
                    }
                }
            })
    }

    fun getListNextMatch(leaugeId: String, callback: FootballRepositoryCallback<Sports?>) {
        NetworkConfig
            .api()
            .getNextLeague(leaugeId)
            .enqueue(object : Callback<Sports> {
                override fun onFailure(call: Call<Sports>, t: Throwable) {
                    callback.onDataError()
                }

                override fun onResponse(call: Call<Sports>, response: Response<Sports>) {
                    if (response.isSuccessful) {
                        callback.onDataLoaded(response.body())
                    } else {
                        callback.onDataError()
                    }
                }
            })
    }
}