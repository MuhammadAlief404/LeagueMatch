package com.quantumhiggs.footballmatch.ui.league.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.quantumhiggs.footballmatch.model.Leagues
import com.quantumhiggs.footballmatch.repository.FootballRepository
import com.quantumhiggs.footballmatch.repository.FootballRepositoryCallback

class DetailLeagueViewModel(private var footballRepository: FootballRepository = FootballRepository()) :
    ViewModel() {

    private var listLeague = MutableLiveData<Leagues>()

    companion object {
        var leagueId: String = ""
    }

    init {
        getDetailLeague(leagueId)
    }

    fun getDetailLeague(leaugeId: String) {
//        NetworkConfig
//            .api()
//            .getDetailLeague(leaugeId)
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
        footballRepository.getDetailLeague(leaugeId, object : FootballRepositoryCallback<Leagues?> {
            override fun onDataLoaded(data: Leagues?) {
                listLeague.value = data
            }

            override fun onDataError() {
                listLeague.value = null
            }

        })

    }

    fun setLeagueID(id: String) {
        leagueId = id
    }

    fun setDetailLeague(): MutableLiveData<Leagues> {
        return listLeague
    }
}