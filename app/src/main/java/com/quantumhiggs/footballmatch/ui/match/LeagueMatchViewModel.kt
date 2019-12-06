package com.quantumhiggs.footballmatch.ui.match

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.quantumhiggs.footballmatch.model.Sports
import com.quantumhiggs.footballmatch.repository.FootballRepository
import com.quantumhiggs.footballmatch.repository.FootballRepositoryCallback

class LeagueMatchViewModel(private var footballRepository: FootballRepository = FootballRepository()) :
    ViewModel() {

    private var listPrevMatch = MutableLiveData<Sports>()
    private var listNextMatch = MutableLiveData<Sports>()

    companion object {
        var leaugeId: String = ""
    }

    init {
        getListPrevMatch(leaugeId)
        getListNextMatch(leaugeId)
    }

    fun getListPrevMatch(leaugeId: String) {
//        NetworkConfig
//            .api()
//            .getPrevLeague(leaugeId)
//            .enqueue(object : Callback<Sports> {
//                override fun onFailure(call: Call<Sports>, t: Throwable) {
//                    listPrevMatch.value = null
//                }
//
//                override fun onResponse(call: Call<Sports>, response: Response<Sports>) {
//                    if (response.isSuccessful) {
//                        listPrevMatch.value = response.body()
//                    } else {
//                        listPrevMatch.value = null
//                    }
//                }
//            })
        footballRepository.getListPrevMatch(leaugeId, object : FootballRepositoryCallback<Sports?> {
            override fun onDataLoaded(data: Sports?) {
                listPrevMatch.value = data
            }

            override fun onDataError() {
                listPrevMatch.value = null
            }

        })
    }

    fun getListNextMatch(leaugeId: String) {
//        NetworkConfig
//            .api()
//            .getNextLeague(leaugeId)
//            .enqueue(object : Callback<Sports> {
//                override fun onFailure(call: Call<Sports>, t: Throwable) {
//                    listNextMatch.value = null
//                }
//
//                override fun onResponse(call: Call<Sports>, response: Response<Sports>) {
//                    if (response.isSuccessful) {
//                        listNextMatch.value = response.body()
//                    } else {
//                        listNextMatch.value = null
//                    }
//                }
//            })
        footballRepository.getListNextMatch(leaugeId, object : FootballRepositoryCallback<Sports?> {
            override fun onDataLoaded(data: Sports?) {
                listNextMatch.value = data
            }

            override fun onDataError() {
                listNextMatch.value = null
            }

        })
    }


    fun setLeagueID(id: String) {
        leaugeId = id
    }
    fun setListNextMatch(): MutableLiveData<Sports> {
        return listNextMatch
    }

    fun setListPrevMatch(): MutableLiveData<Sports> {
        return listPrevMatch
    }
}