package com.quantumhiggs.footballmatch.ui.league.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.quantumhiggs.footballmatch.model.Leagues
import com.quantumhiggs.footballmatch.repository.FootballRepository
import com.quantumhiggs.footballmatch.repository.FootballRepositoryCallback


class ListLeagueViewModel(private var footballRepository: FootballRepository = FootballRepository()) :
    ViewModel() {

    private var listLeague = MutableLiveData<Leagues>()
//    private var footballRepository = FootballRepository()

    init {
        getListLeague()
    }

    fun getListLeague() {
//        NetworkConfig()
//            .api()
//            .getAllLeagues()
//            .enqueue(object : Callback<Leagues> {
//                override fun onFailure(call: Call<Leagues>, t: Throwable) {
//                    listLeague.value = null
//                }
//
//                override fun onResponse(call: Call<Leagues>, response: Response<Leagues>) {
//                    if (response.isSuccessful) {
//                        listLeague.value = response.body()
//                    } else {
//                        listLeague.value = null
//                    }
//                }
//            })
        footballRepository.getListLeague(object : FootballRepositoryCallback<Leagues?> {
            override fun onDataLoaded(data: Leagues?) {
                listLeague.value = data
            }

            override fun onDataError() {
                listLeague.value = null
            }

        })
    }

    fun setListLeague(): MutableLiveData<Leagues> {
        return listLeague
    }

}