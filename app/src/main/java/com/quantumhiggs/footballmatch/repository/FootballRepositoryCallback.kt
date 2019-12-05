package com.quantumhiggs.footballmatch.repository

interface FootballRepositoryCallback<T> {
    fun onDataLoaded(data: T?)
    fun onDataError()
}