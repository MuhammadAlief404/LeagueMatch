package com.quantumhiggs.footballmatch.ui.league.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.quantumhiggs.footballmatch.model.Leagues
import com.quantumhiggs.footballmatch.network.NetworkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ListLeagueViewModel : ViewModel() {

    private var listLeague = MutableLiveData<Leagues>()

    init {
        getListLeague()
    }

    private fun getListLeague() {
        NetworkConfig()
            .api()
            .getAllLeagues()
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

    fun setListLeague(): MutableLiveData<Leagues> {
        return listLeague
    }

}