package com.quantumhiggs.footballmatch.ui.match.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.quantumhiggs.footballmatch.model.Sports
import com.quantumhiggs.footballmatch.model.Teams
import com.quantumhiggs.footballmatch.network.NetworkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchMatchViewModel(var matchName: String = "") : ViewModel() {

    private var listMatch = MutableLiveData<Sports>()
    private var teamList = MutableLiveData<Teams>()

    init {
        getListMatch(matchName)
    }

    fun getListMatch(matchName: String) {
        NetworkConfig
            .api()
            .getSearchMatch(matchName)
            .enqueue(object : Callback<Sports> {
                override fun onFailure(call: Call<Sports>, t: Throwable) {
                    listMatch.value = null
                }

                override fun onResponse(call: Call<Sports>, response: Response<Sports>) {
                    if (response.isSuccessful) {
                        listMatch.value = response.body()
                    } else {
                        listMatch.value = null
                    }
                }
            })

    }

    fun getTeamList(teamName: String) {
        NetworkConfig
            .api()
            .getTeamByName(teamName)
            .enqueue(object : Callback<Teams> {
                override fun onFailure(call: Call<Teams>, t: Throwable) {
                    teamList.value = null
                }

                override fun onResponse(call: Call<Teams>, response: Response<Teams>) {
                    if (response.isSuccessful) {
                        teamList.value = response.body()
                    } else {
                        teamList.value = null
                    }
                }
            })

    }

    fun setTeamList(): MutableLiveData<Teams> {
        getTeamList(matchName)
        return teamList
    }

    fun setListMatch(): MutableLiveData<Sports> {
        getListMatch(matchName)
        return listMatch
    }

}