package com.quantumhiggs.footballmatch.ui.team


import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.quantumhiggs.footballmatch.R
import com.quantumhiggs.footballmatch.db.database
import com.quantumhiggs.footballmatch.model.Team
import com.quantumhiggs.footballmatch.model.favorite.TeamFavorite
import com.quantumhiggs.footballmatch.ui.match.adapter.LeagueMatchAdapter
import com.quantumhiggs.footballmatch.utils.CommonFunction.checkNullOrEmpty
import kotlinx.android.synthetic.main.fragment_team_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.toast

class TeamDetailFragment : Fragment() {

    private lateinit var viewModel: TeamDetailViewModel
    private var isFavorite = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val args by navArgs<TeamDetailFragmentArgs>()
        TeamDetailViewModel.teamId = args.teamId

        viewModel = ViewModelProviders.of(this).get(TeamDetailViewModel::class.java)

        rv_list_team.layoutManager = LinearLayoutManager(context)

        observeViewModel(0)

        detail_team_tab_menu.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                toggleTab(p0?.position)
            }
        })
    }

    private fun toggleTab(pos: Int?) {
        if (pos == 0) {
            rv_list_team.visibility = View.GONE
            detail_team_card.visibility = View.VISIBLE
        } else {
            rv_list_team.visibility = View.VISIBLE
            detail_team_card.visibility = View.GONE
        }
        observeViewModel(pos!!)
    }

    private fun observeViewModel(pos: Int) {
        when (pos) {
            0 -> viewModel.detailTeam.observe(this, Observer { t ->
                detail_team_name.text = checkNullOrEmpty(t.teams[0].strTeam)
                detail_team_description.text = checkNullOrEmpty(t.teams[0].strDescriptionEN)
                detail_team_year.text = checkNullOrEmpty(t.teams[0].intFormedYear)
                detail_team_league.text = checkNullOrEmpty(t.teams[0].strLeague)

                Glide.with(this)
                    .load(t.teams[0].strTeamLogo)
                    .placeholder(R.drawable.ic_trophy)
                    .into(detail_team_logo)
                detail_team_btn_add.setOnClickListener {
                    favoriteControl(t.teams[0])
                }
                favoriteCheck(t.teams[0])
            })
            1 -> viewModel.prevMatch.observe(this, Observer { t ->
                if (!t.results.isNullOrEmpty()) {
                    rv_list_team.adapter = LeagueMatchAdapter(t.results)
                }
            })
            2 -> viewModel.nextMatch.observe(this, Observer { t ->
                if (!t.events.isNullOrEmpty()) {
                    rv_list_team.adapter = LeagueMatchAdapter(t.events)
                }
            })
        }
    }

    private fun favoriteControl(data: Team) {
        favoriteCheck(data)
        if (isFavorited(data)) {
            removeFromFavorite(data)
        } else {
            addToFavorite(data)
            detail_team_btn_add.text = getString(R.string.remove_from_favorite)
        }
    }

    private fun addToFavorite(data: Team) {
        try {
            context?.database?.use {
                insert(
                    TeamFavorite.TABLE_TEAM,
                    TeamFavorite.TEAM_ID to data.idTeam,
                    TeamFavorite.TEAM_NAME to data.strTeam,
                    TeamFavorite.TEAM_DESC to data.strDescriptionEN,
                    TeamFavorite.TEAM_IMG to data.strTeamLogo,
                    TeamFavorite.TEAM_LEAGUE to data.strLeague,
                    TeamFavorite.TEAM_YEAR to data.intFormedYear
                )
                toast(getString(R.string.match_added))
                favoriteCheck(data)
            }
        } catch (e: SQLiteConstraintException) {
            toast(e.localizedMessage)
        }
    }

    private fun removeFromFavorite(data: Team) {
        try {
            context?.database?.use {
                delete(
                    TeamFavorite.TABLE_TEAM, "(TEAM_ID = {id})",
                    "id" to data.idTeam
                )
            }
            toast(getString(R.string.match_removed))
            favoriteCheck(data)
        } catch (e: SQLiteConstraintException) {
            toast(e.localizedMessage)
        }
    }

    private fun favoriteCheck(data: Team) {
        if (isFavorited(data)) {
            detail_team_btn_add.text = getString(R.string.remove_from_favorite)
            isFavorite = true
        } else {
            detail_team_btn_add.text = getString(R.string.add_to_favorite)
            isFavorite = false
        }
    }

    private fun isFavorited(data: Team): Boolean {
        var temp = false
        context?.database?.use {
            val result = select(TeamFavorite.TABLE_TEAM)
                .whereArgs(
                    "(TEAM_ID = {id})",
                    "id" to data.idTeam
                )
            val favorite = result.parseList(classParser<TeamFavorite>())
            if (favorite.isNotEmpty()) {
                temp = true
            }
        }
        return temp
    }
}
