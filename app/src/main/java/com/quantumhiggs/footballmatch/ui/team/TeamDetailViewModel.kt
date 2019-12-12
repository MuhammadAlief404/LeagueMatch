package com.quantumhiggs.footballmatch.ui.team

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.quantumhiggs.footballmatch.model.Players
import com.quantumhiggs.footballmatch.model.Sports
import com.quantumhiggs.footballmatch.model.Team
import com.quantumhiggs.footballmatch.model.Teams
import com.quantumhiggs.footballmatch.network.NetworkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamDetailViewModel : ViewModel() {
    var detailTeam = MutableLiveData<Teams>()
    var matchTeam = MutableLiveData<Sports>()
    var listPlayer = MutableLiveData<Players>()

    companion object{
        var teamId : String = ""
    }

    init {
        getDetailTeam(teamId)
        getListMatch(teamId)
    }

    fun getDetailTeam(id : String) {
        NetworkConfig
            .api()
            .getTeamDetail(id)
            .enqueue(object : Callback<Teams> {
                override fun onFailure(call: Call<Teams>, t: Throwable) {
                    detailTeam.value = null
                }

                override fun onResponse(call: Call<Teams>, response: Response<Teams>) {
                    if (response.isSuccessful) {
                        detailTeam.value = response.body()
                    } else {
                        detailTeam.value = null
                    }
                }
            })
    }

    fun getListMatch(id : String) {
        NetworkConfig
            .api()
            .getDetailMatch(id)
            .enqueue(object : Callback<Sports> {
                override fun onFailure(call: Call<Sports>, t: Throwable) {
                    matchTeam.value = null
                }

                override fun onResponse(call: Call<Sports>, response: Response<Sports>) {
                    if (response.isSuccessful) {
                        matchTeam.value = response.body()
                    } else {
                        matchTeam.value = null
                    }
                }
            })
    }

    fun getListPlayer(id : String) {
        NetworkConfig
            .api()
            .getPlayers(id)
            .enqueue(object : Callback<Players> {
                override fun onFailure(call: Call<Players>, t: Throwable) {
                    listPlayer.value = null
                }

                override fun onResponse(call: Call<Players>, response: Response<Players>) {
                    if (response.isSuccessful) {
                        listPlayer.value = response.body()
                    } else {
                        listPlayer.value = null
                    }
                }
            })
    }
}