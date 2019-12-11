package com.quantumhiggs.footballmatch.ui.league.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.quantumhiggs.footballmatch.getOrAwaitValue
import com.quantumhiggs.footballmatch.model.League
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations

class DetailLeagueViewModelTest {

    private lateinit var viewModel: DetailLeagueViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    var id = "4346"


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = DetailLeagueViewModel()

    }

    @Test
    fun get_detail_league() {
        val expectedLeague = League(
            dateFirstEvent = "2013-03-02",
            idCup = "0",
            idLeague = "4346",
            idSoccerXML = "20",
            intFormedYear = "1993",
            strBadge = "https://www.thesportsdb.com/images/media/league/badge/dqo6r91549878326.png",
            strBanner = "https://www.thesportsdb.com/images/media/league/banner/ycqd1a1557524672.jpg",
            strComplete = "yes",
            strCountry = "USA",
            strDescriptionCN = "null",
            strDescriptionDE = "null",
            strDescriptionEN = "Major League Soccer(MLS) is a\n" +
                    "            professional soccer league representing the sport 's highest level in both the United States and Canada. MLS constitutes one of the major professional sports leagues of the United States and Canada. The league is composed of 20 teams—17 in the U.S. and 3 in Canada. The MLS regular season runs from March to October, with each team playing 34 games; the team with the best record is awarded the Supporters' Shield . The post season includes twelve teams competing in the MLS Cup Playoffs through November and December,\n" +
                    "            culminating in the championship game,\n" +
                    "            the MLS Cup.MLS teams also play in other competitions against teams from other divisions and countries,\n" +
                    "            such as the U . S . Open Cup,\n" +
                    "            the Canadian Championship,\n" +
                    "            and the CONCACAF Champions League.MLS is sanctioned\n" +
                    "            by the United States Soccer Federation (U.S.Soccer).Major\n" +
                    "                    League Soccer was founded in\n" +
                    "                    1993 as part of the United States\n" +
                    "            ' successful bid to host the 1994 FIFA World Cup. The first season took place in 1996 with ten teams. MLS experienced financial and operational struggles in its first few years: The league lost millions of dollars, teams played in mostly empty American football stadiums, and two teams folded in 2002. Since then, MLS has expanded to 20 teams, owners built soccer-specific stadiums, average attendance at MLS matches exceeds that of the National Basketball Association (NBA) and the National Hockey League (NHL), MLS secured national TV contracts, and the league is now profitable.\n" +
                    "\n" +
                    "                    Instead of operating as an association of independently owned teams,\n" +
                    "            MLS is a\n" +
                    "            single entity in which each team is owned and controlled by the league\n" +
                    "            's investors. The investor-operators control their teams as owners control teams in other leagues, and are commonly (but inaccurately) referred to as the team' s owners.The league 's closed membership makes it one of the world' s few soccer leagues that does not use promotion and relegation,\n" +
                    "            which is uncommon in North America . MLS headquarters are in New York City .",
            strDescriptionES = "null",
            strDescriptionFR = "La Major League Soccer (MLS),\n" +
                    "            littéralement la Ligue majeure de soccer,\n" +
                    "            est la principale ligue professionnelle de soccer(football) en Amérique du Nord.Elle a été créée en\n" +
                    "            1993.La première saison de cette ligue a eu lieu en 1996 avec 10 franchises . Elle comprend\n" +
                    "            20 franchises réparties aux États - Unis et au Canada (depuis\n" +
                    "            2007\n" +
                    "        ).La MLS est le plus haut niveau des compétitions de soccer de ces deux pays.Comme pour la plupart des ligues sportives professionnelles nord - américaines, la ligue professionnelle est fermée. Elle ne se joue que par des franchises qui payent pour entrer dans la ligue. Il n'y a pas de système de relégation-promotion et les vingt franchises nord-américaines (dix-sept américaines, trois canadiennes) sont réparties en associations (Est et Ouest). La saison se termine par la finale MLS, disputée sur un match, qui conclut les séries éliminatoires.\n" +
                    "\n" +
                    "        La grande majorité des joueurs viennent des États -Unis et du Mexique, mais aussi de Jamaïque, du Canada et de Trinité-et-Tobago.",
            strDescriptionHU = "null",
            strDescriptionIL = "null",
            strDescriptionIT = "null",
            strDescriptionJP = "null",
            strDescriptionNL = "null",
            strDescriptionNO = "null",
            strDescriptionPL = "null",
            strDescriptionPT = "null",
            strDescriptionRU = "null",
            strDescriptionSE = "null",
            strDivision = "0",
            strFacebook = "www.facebook.com / MLS",
            strFanart1 = "https://www.thesportsdb.com/images/media/league/fanart/rsypuy1422060998.jpg",
            strFanart2 = "https://www.thesportsdb.com/images/media/league/fanart/yrpswv1422061048.jpg",
            strFanart3 = "https://www.thesportsdb.com/images/media/league/fanart/yuwxqr1422265356.jpg",
            strFanart4 = "https://www.thesportsdb.com/images/media/league/fanart/wxsqxt1422265392.jpg",
            strGender = "Male",
            strLeague = "American Major League Soccer",
            strLeagueAlternate = "MLS,\n" +
                    "            Major League Soccer",
            strLocked = "unlocked",
            strLogo = "https://www.thesportsdb.com/images/media/league/logo/yrxxpx1421700436.png",
            strNaming = "{strHomeTeam} vs {strAwayTeam}",
            strPoster = "https://www.thesportsdb.com/images/media/league/poster/upsqts1454015303.jpg",
            strRSS = "www.mlssoccer.com/news/feed",
            strSport = "Soccer",
            strTrophy = "https://www.thesportsdb.com/images/media/league/trophy/rsqvts1422247468.png",
            strTwitter = "twitter.com/mls",
            strWebsite = "www.mlssoccer.com",
            strYoutube = "www.youtube.com / user / mls"
        )

        viewModel.getDetailLeague(id)

        Assert.assertNotNull(viewModel.listLeague.getOrAwaitValue())
        Assert.assertEquals(
            viewModel.listLeague.value!!.leagues[0].idLeague,
            expectedLeague.idLeague
        )
        Assert.assertEquals(
            viewModel.listLeague.value!!.leagues[0].strBadge,
            expectedLeague.strBadge
        )
        Assert.assertEquals(
            viewModel.listLeague.value!!.leagues[0].strLeague,
            expectedLeague.strLeague
        )

    }
}