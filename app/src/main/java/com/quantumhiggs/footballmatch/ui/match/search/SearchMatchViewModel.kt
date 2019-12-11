package com.quantumhiggs.footballmatch.ui.match.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.quantumhiggs.footballmatch.model.Sports
import com.quantumhiggs.footballmatch.network.NetworkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchMatchViewModel(var matchName: String = "") : ViewModel() {

    private var listMatch = MutableLiveData<Sports>()

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

    fun setListMatch(): MutableLiveData<Sports> {
        getListMatch(matchName)
        return listMatch
    }

}