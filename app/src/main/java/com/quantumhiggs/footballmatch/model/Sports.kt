package com.quantumhiggs.footballmatch.model

data class Sports(
    var events: List<Event>,
    var event: List<Event>,
    var results: List<Event>
)

data class Event(
    val dateEvent: String,
    val dateEventLocal: Any,
    val idAwayTeam: String,
    val idEvent: String,
    val idHomeTeam: String,
    val idLeague: String,
    val idSoccerXML: String,
    val intAwayScore: String?,
    val intAwayShots: String,
    val intHomeScore: String?,
    val intHomeShots: String,
    val intRound: String,
    val intSpectators: String,
    val strAwayFormation: String,
    val strAwayGoalDetails: String,
    val strAwayLineupDefense: String,
    val strAwayLineupForward: String,
    val strAwayLineupGoalkeeper: String,
    val strAwayLineupMidfield: String,
    val strAwayLineupSubstitutes: String,
    val strAwayRedCards: String,
    val strAwayTeam: String,
    val strAwayYellowCards: String,
    val strBanner: Any,
    val strCircuit: Any,
    val strCity: Any,
    val strCountry: Any,
    val strDate: String,
    val strDescriptionEN: Any,
    val strEvent: String,
    val strEventAlternate: String,
    val strFanart: Any,
    val strFilename: String,
    val strHomeFormation: String,
    val strHomeGoalDetails: String,
    val strHomeLineupDefense: String,
    val strHomeLineupForward: String,
    val strHomeLineupGoalkeeper: String,
    val strHomeLineupMidfield: String,
    val strHomeLineupSubstitutes: String,
    val strHomeRedCards: String,
    val strHomeTeam: String,
    val strHomeYellowCards: String,
    val strLeague: String,
    val strLocked: String,
    val strMap: Any,
    val strPoster: Any,
    val strResult: Any,
    val strSeason: String,
    val strSport: String,
    val strTVStation: Any,
    val strThumb: Any,
    val strTime: String,
    val strTimeLocal: Any,
    val strTweet1: Any,
    val strTweet2: Any,
    val strTweet3: Any,
    val strVideo: Any
)