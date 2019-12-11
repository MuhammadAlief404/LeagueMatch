package com.quantumhiggs.footballmatch.ui.league.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.quantumhiggs.footballmatch.model.Leagues
import com.quantumhiggs.footballmatch.network.NetworkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailLeagueViewModel : ViewModel() {

    var listLeague = MutableLiveData<Leagues>()

    companion object {
        var leagueId = "4346"
    }

    init {
        getDetailLeague(leagueId)
    }

    fun getDetailLeague(leagueId: String) {
        NetworkConfig
            .api()
            .getDetailLeague(leagueId)
            .enqueue(object : Callback<Leagues> {
                override fun onFailure(call: Call<Leagues>, t: Throwable) {
                    listLeague.value = null
                }

                override fun onResponse(call: Call<Leagues>, response: Response<Leagues>) {
                    if (response.isSuccessful) {
                        listLeague.value = response.body()
                    } else {
                        listLeague.value = null
                    }
                }
            })
    }

    fun setDetailLeague(): MutableLiveData<Leagues> {
        return listLeague
    }
}