package com.quantumhiggs.footballmatch.ui.match.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.quantumhiggs.footballmatch.model.Sports
import com.quantumhiggs.footballmatch.network.NetworkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailMatchViewModel : ViewModel() {

    private var detailMatch = MutableLiveData<Sports>()

    companion object {
        var matchId: String = "1"
    }

    init {
        getDetailMatch(matchId)
    }

    private fun getDetailMatch(matchId: String) {
        NetworkConfig()
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

    fun setDetailMatch(): MutableLiveData<Sports> {
        return detailMatch
    }
}