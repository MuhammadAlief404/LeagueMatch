package com.quantumhiggs.footballmatch.ui.match

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.quantumhiggs.footballmatch.model.Sports
import com.quantumhiggs.footballmatch.model.Standings
import com.quantumhiggs.footballmatch.model.Teams
import com.quantumhiggs.footballmatch.network.NetworkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LeagueMatchViewModel : ViewModel() {

    var listPrevMatch = MutableLiveData<Sports>()
    var listNextMatch = MutableLiveData<Sports>()
    var listTeams = MutableLiveData<Teams>()
    var listStandings = MutableLiveData<Standings>()

    companion object {
        var leaugeId: String = "4387"
    }

    init {
        getListPrevMatch(leaugeId)
        getListNextMatch(leaugeId)
        getListTeams(leaugeId)
        getListStandings(leaugeId)
    }

    fun getListPrevMatch(leaugeId: String) {
        NetworkConfig
            .api()
            .getPrevLeague(leaugeId)
            .enqueue(object : Callback<Sports> {
                override fun onFailure(call: Call<Sports>, t: Throwable) {
                    listPrevMatch.value = null
                }

                override fun onResponse(call: Call<Sports>, response: Response<Sports>) {
                    if (response.isSuccessful) {
                        listPrevMatch.value = response.body()
                    } else {
                        listPrevMatch.value = null
                    }
                }
            })
    }

    fun getListNextMatch(leaugeId: String) {
        NetworkConfig
            .api()
            .getNextLeague(leaugeId)
            .enqueue(object : Callback<Sports> {
                override fun onFailure(call: Call<Sports>, t: Throwable) {
                    listNextMatch.value = null
                }

                override fun onResponse(call: Call<Sports>, response: Response<Sports>) {
                    if (response.isSuccessful) {
                        listNextMatch.value = response.body()
                    } else {
                        listNextMatch.value = null
                    }
                }
            })
    }

    fun getListTeams(leaugeId: String) {
        NetworkConfig
            .api()
            .getListTeam(leaugeId)
            .enqueue(object : Callback<Teams>{
                override fun onFailure(call: Call<Teams>, t: Throwable) {
                    listTeams.value = null
                }

                override fun onResponse(call: Call<Teams>, response: Response<Teams>) {
                    if(response.isSuccessful){
                        listTeams.value = response.body()
                    }
                    else {
                        listTeams.value = null
                    }
                }

            })
    }

    fun getListStandings(leaugeId: String) {
        NetworkConfig
            .api()
            .getClassement(leaugeId)
            .enqueue(object : Callback<Standings> {
                override fun onFailure(call: Call<Standings>, t: Throwable) {
                    listStandings.value = null
                }

                override fun onResponse(call: Call<Standings>, response: Response<Standings>) {
                    if (response.isSuccessful) {
                        listStandings.value = response.body()
                    } else {
                        listStandings.value = null
                    }
                }

            })
    }

    fun setListNextMatch(): MutableLiveData<Sports> {
        return listNextMatch
    }

    fun setListPrevMatch(): MutableLiveData<Sports> {
        return listPrevMatch
    }

    fun setListTeams() : MutableLiveData<Teams> {
        return listTeams
    }
}