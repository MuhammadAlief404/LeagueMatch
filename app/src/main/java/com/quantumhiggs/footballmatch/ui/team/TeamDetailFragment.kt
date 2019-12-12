package com.quantumhiggs.footballmatch.ui.team


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
import com.quantumhiggs.footballmatch.ui.match.adapter.LeagueMatchAdapter
import com.quantumhiggs.footballmatch.utils.CommonFunction.checkNullOrEmpty
import kotlinx.android.synthetic.main.fragment_team_detail.*

class TeamDetailFragment : Fragment() {

    private lateinit var viewModel: TeamDetailViewModel

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
        if (pos == 0) {
            viewModel.detailTeam.observe(this, Observer { t ->
                detail_team_name.text = checkNullOrEmpty(t.teams[0].strTeam)
                detail_team_description.text = checkNullOrEmpty(t.teams[0].strDescriptionEN)
                detail_team_year.text = checkNullOrEmpty(t.teams[0].intFormedYear)
                detail_team_league.text = checkNullOrEmpty(t.teams[0].strLeague)

                Glide.with(this)
                    .load(t.teams[0].strTeamLogo)
                    .placeholder(R.drawable.ic_trophy)
                    .into(detail_team_logo)
            })
        } else {
            viewModel.matchTeam.observe(this, Observer { t ->
                rv_list_team.adapter = LeagueMatchAdapter(t.events)
            })
        }
    }
}
