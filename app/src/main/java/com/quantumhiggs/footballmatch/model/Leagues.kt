package com.quantumhiggs.footballmatch.model

data class Leagues(
    val leagues: List<League>
)

data class League(
    val idLeague: String,
    val strLeague: String,
    val strLeagueAlternate: String,
    val strSport: String
)