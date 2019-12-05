package com.quantumhiggs.footballmatch.repository

import com.quantumhiggs.footballmatch.model.Leagues
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
}