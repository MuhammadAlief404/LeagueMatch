package com.quantumhiggs.footballmatch.ui.match

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.quantumhiggs.footballmatch.model.Sports
import com.quantumhiggs.footballmatch.network.NetworkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LeagueMatchViewModel : ViewModel() {

    private var listPrevMatch = MutableLiveData<Sports>()
    private var listNextMatch = MutableLiveData<Sports>()

    companion object {
        var leaugeId: String = "1"
    }

    init {
        getListPrevMatch(leaugeId)
        getListNextMatch(leaugeId)
    }

    private fun getListPrevMatch(leaugeId: String) {
        NetworkConfig()
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

    private fun getListNextMatch(leaugeId: String) {
        NetworkConfig()
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

    fun setListNextMatch(): MutableLiveData<Sports> {
        return listNextMatch
    }

    fun setListPrevMatch(): MutableLiveData<Sports> {
        return listPrevMatch
    }
}