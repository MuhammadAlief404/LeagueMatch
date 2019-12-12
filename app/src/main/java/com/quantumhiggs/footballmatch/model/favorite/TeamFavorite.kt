package com.quantumhiggs.footballmatch.model.favorite

data class TeamFavorite(
    val id: String?,
    val teamId: String?,
    val teamName: String?,
    val teamDesc: String?,
    val teamImg: String?,
    val teamYear: String?,
    val teamLeague: String?
) {
    companion object {
        const val TABLE_TEAM: String = "TABLE_TEAM"
        const val ID: String = "ID_"
        const val TEAM_ID: String = "TEAM_ID"
        const val TEAM_NAME: String = "TEAM_NAME"
        const val TEAM_DESC: String = "TEAM_DESC"
        const val TEAM_IMG: String = "TEAM_IMG"
        const val TEAM_YEAR: String = "TEAM_YEAR"
        const val TEAM_LEAGUE: String = "TEAM_LEAGUE"
    }
}