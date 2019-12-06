package com.quantumhiggs.footballmatch.ui.match.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.quantumhiggs.footballmatch.model.Sports
import com.quantumhiggs.footballmatch.repository.FootballRepository
import com.quantumhiggs.footballmatch.repository.FootballRepositoryCallback

class SearchMatchViewModel(
    var matchName: String = "",
    private var footballRepository: FootballRepository = FootballRepository()
) : ViewModel() {

    private var listMatch = MutableLiveData<Sports>()

    init {
        getListMatch(matchName)
    }

    fun getListMatch(matchName: String) {
//        NetworkConfig
//            .api()
//            .getSearchMatch(matchName)
//            .enqueue(object : Callback<Sports> {
//                override fun onFailure(call: Call<Sports>, t: Throwable) {
//                    listMatch.value = null
//                }
//
//                override fun onResponse(call: Call<Sports>, response: Response<Sports>) {
//                    if (response.isSuccessful) {
//                        listMatch.value = response.body()
//                    } else {
//                        listMatch.value = null
//                    }
//                }
//            })

        footballRepository.getListMatch(matchName, object : FootballRepositoryCallback<Sports?> {
            override fun onDataLoaded(data: Sports?) {
                listMatch.value = data
            }

            override fun onDataError() {
                listMatch.value = null
            }

        })
    }

    fun setListMatch(): MutableLiveData<Sports> {
        getListMatch(matchName)
        return listMatch
    }

}