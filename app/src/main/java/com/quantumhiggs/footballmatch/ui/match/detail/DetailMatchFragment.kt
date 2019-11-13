package com.quantumhiggs.footballmatch.ui.match.detail


import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.quantumhiggs.footballmatch.R
import com.quantumhiggs.footballmatch.db.database
import com.quantumhiggs.footballmatch.model.Event
import com.quantumhiggs.footballmatch.model.Favorites
import com.quantumhiggs.footballmatch.model.Team
import com.quantumhiggs.footballmatch.utils.CommonFunction.checkNullOrEmpty
import kotlinx.android.synthetic.main.fragment_detail_match.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.toast

class DetailMatchFragment : Fragment() {

    private lateinit var viewModel: DetailMatchViewModel
    private var isFavorite = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_match, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val args by navArgs<DetailMatchFragmentArgs>()
        DetailMatchViewModel.matchId = args.matchId

        viewModel = ViewModelProviders.of(this).get(DetailMatchViewModel::class.java)

        viewModel.setDetailMatch().observe(this, Observer { t ->
            showData(t.events)
        })
    }

    private fun showData(datas: List<Event>) {
        val data = datas[0]

        event_detail_match.text = checkNullOrEmpty(data.strEvent)
        league_detail_match.text = checkNullOrEmpty(data.strLeague)

        home_card_detail_match.text = checkNullOrEmpty(data.strHomeTeam)
        away_card_detail_match.text = checkNullOrEmpty(data.strAwayTeam)
        score_card_detail_match.text =
            checkNullOrEmpty(data.intHomeScore) + " : " + checkNullOrEmpty(data.intAwayScore)

        away_detail_match.text = checkNullOrEmpty(data.strAwayTeam)
        away_score_detail_match.text = checkNullOrEmpty(data.intAwayScore)
        away_formation_detail_match.text = checkNullOrEmpty(data.strAwayFormation)
        away_shoots_detail_match.text = checkNullOrEmpty(data.intAwayShots)
        away_goald_detail_match.text = checkNullOrEmpty(data.strAwayGoalDetails)


        home_detail_match.text = checkNullOrEmpty(data.strHomeTeam)
        home_score_detail_match.text = checkNullOrEmpty(data.intHomeScore)
        home_formation_detail_match.text = checkNullOrEmpty(data.strHomeFormation)
        home_shoots_detail_match.text = checkNullOrEmpty(data.intHomeShots)
        home_goald_detail_match.text = checkNullOrEmpty(data.strHomeGoalDetails)

        if (!datas.isNullOrEmpty()) {
            viewModel.setHomeDetail().observe(this, Observer { t ->
                showHome(t.teams)
            })

            viewModel.setAwayDetail().observe(this, Observer { t ->
                showAway(t.teams)
            })
        }

        favoriteCheck(data)

        btn_favorite_detail_match.setOnClickListener {
            favoriteControl(data)
        }

    }

    private fun showHome(datas: List<Team>) {
        Glide.with(this)
            .load(datas[0].strTeamLogo)
            .placeholder(R.drawable.ic_trophy)
            .into(home_image_card_detail_match)

        Glide.with(this)
            .load(datas[0].strTeamLogo)
            .placeholder(R.drawable.ic_trophy)
            .into(home_image_detail_match)
    }

    private fun showAway(datas: List<Team>) {
        Glide.with(this)
            .load(datas[0].strTeamLogo)
            .placeholder(R.drawable.ic_trophy)
            .into(away_image_card_detail_match)

        Glide.with(this)
            .load(datas[0].strTeamLogo)
            .placeholder(R.drawable.ic_trophy)
            .into(away_image_detail_match)
    }


    private fun favoriteControl(data: Event) {
        favoriteCheck(data)
        if (isFavorited(data)) {
            try {
                context?.database?.use {
                    delete(
                        Favorites.TABLE_FAVORITE, "(EVENT_ID = {id})",
                        "id" to data.idEvent
                    )
                }
                toast("Match Removed from Favorite")

            } catch (e: SQLiteConstraintException) {
                toast(e.localizedMessage)
            }
        } else {
            try {
                context?.database?.use {
                    insert(
                        Favorites.TABLE_FAVORITE,
                        Favorites.EVENT_ID to data.idEvent,
                        Favorites.EVENT_NAME to data.strEvent,
                        Favorites.DATE_EVENT to data.dateEvent,
                        Favorites.HOME_NAME to data.strHomeTeam,
                        Favorites.AWAY_NAME to data.strAwayTeam,
                        Favorites.HOME_SCORE to data.intHomeScore,
                        Favorites.AWAY_SCORE to data.intAwayScore
                    )
                    toast("Match Added to Favorite")
                }
            } catch (e: SQLiteConstraintException) {
                toast(e.localizedMessage)
            }
            btn_favorite_detail_match.text = getString(R.string.remove_from_favorite)
        }
    }

    private fun favoriteCheck(data: Event) {
        if (isFavorited(data)) {
            btn_favorite_detail_match.text = getString(R.string.remove_from_favorite)
            isFavorite = true
        } else {
            btn_favorite_detail_match.text = getString(R.string.add_to_favorite)
            isFavorite = false
        }
    }

    private fun isFavorited(event: Event): Boolean {
        var temp = false
        context?.database?.use {
            val result = select(Favorites.TABLE_FAVORITE)
                .whereArgs(
                    "(EVENT_ID = {id})",
                    "id" to event.idEvent
                )
            val favorite = result.parseList(classParser<Favorites>())
            if (favorite.isNotEmpty()) {
                temp = true
            }
        }
        return temp
    }
}
