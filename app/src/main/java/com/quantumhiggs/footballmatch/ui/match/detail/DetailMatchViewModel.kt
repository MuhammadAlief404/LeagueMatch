package com.quantumhiggs.footballmatch.ui.match.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.quantumhiggs.footballmatch.model.Sports
import com.quantumhiggs.footballmatch.model.Teams
import com.quantumhiggs.footballmatch.network.NetworkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailMatchViewModel : ViewModel() {

    var detailMatch = MutableLiveData<Sports>()
    var homeDetail = MutableLiveData<Teams>()
    var awayDetail = MutableLiveData<Teams>()

    companion object {
        var matchId: String = "441613"
        var homeId: String = "133605"
        var awayId: String = "2112"
    }

    init {
        getDetailMatch(matchId)
    }

    fun getDetailMatch(matchId: String) {
        NetworkConfig
            .api()
            .getDetailMatch(matchId)
            .enqueue(object : Callback<Sports> {
                override fun onFailure(call: Call<Sports>, t: Throwable) {
                    detailMatch.value = null
                }

                override fun onResponse(call: Call<Sports>, response: Response<Sports>) {
                    if (response.isSuccessful) {
                        detailMatch.value = response.body()
                    } else {
                        detailMatch.value = null
                    }
                }
            })

    }

    fun getDetailHome(teamId: String) {
        NetworkConfig
            .api()
            .getTeamDetail(teamId)
            .enqueue(object : Callback<Teams> {
                override fun onFailure(call: Call<Teams>, t: Throwable) {
                    homeDetail.value = null
                }

                override fun onResponse(call: Call<Teams>, response: Response<Teams>) {
                    if (response.isSuccessful) {
                        homeDetail.value = response.body()
                    } else {
                        homeDetail.value = null
                    }
                }
            })
    }

    fun getAwayDetail(teamId: String) {
        NetworkConfig
            .api()
            .getTeamDetail(teamId)
            .enqueue(object : Callback<Teams> {
                override fun onFailure(call: Call<Teams>, t: Throwable) {
                    awayDetail.value = null
                }

                override fun onResponse(call: Call<Teams>, response: Response<Teams>) {
                    if (response.isSuccessful) {
                        awayDetail.value = response.body()
                    } else {
                        awayDetail.value = null
                    }
                }
            })
    }

    fun setHomeDetail(): MutableLiveData<Teams> {
        homeId = detailMatch.value?.events?.get(0)?.idHomeTeam.toString()
        getDetailHome(homeId)
        return homeDetail
    }

    fun setAwayDetail(): MutableLiveData<Teams> {
        awayId = detailMatch.value?.events?.get(0)?.idAwayTeam.toString()
        getAwayDetail(awayId)
        return awayDetail
    }
}