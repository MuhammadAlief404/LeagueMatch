package com.quantumhiggs.footballmatch.ui.match


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.quantumhiggs.footballmatch.R
import com.quantumhiggs.footballmatch.model.Event
import com.quantumhiggs.footballmatch.model.Table
import com.quantumhiggs.footballmatch.model.Team
import com.quantumhiggs.footballmatch.ui.match.adapter.LeagueMatchAdapter
import com.quantumhiggs.footballmatch.ui.match.adapter.LeagueMatchTeamAdapter
import com.quantumhiggs.footballmatch.ui.match.adapter.StandingsAdapter
import kotlinx.android.synthetic.main.fragment_league_match.*


class LeagueMatchFragment : Fragment() {

    private lateinit var viewModel: LeagueMatchViewModel
    private lateinit var fanArt: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_league_match, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val args by navArgs<LeagueMatchFragmentArgs>()
        LeagueMatchViewModel.leaugeId = args.leagueId
        fanArt = args.fanArt
        viewModel = ViewModelProviders.of(this).get(LeagueMatchViewModel::class.java)

        rv_list_match.layoutManager = LinearLayoutManager(context)

        observeViewModel(0)

        tab_menu_league_match.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                observeViewModel(p0?.position)
            }

        })

        fab_search_league_match.setOnClickListener {
            val direction = LeagueMatchFragmentDirections.actionSearchMatch()
            it.findNavController().navigate(direction)
        }

    }

    private fun showDataEvent(datas: List<Event>) {
        rv_list_match.adapter =
            LeagueMatchAdapter(datas)

        Glide.with(this)
            .load(fanArt)
            .placeholder(R.drawable.ic_trophy)
            .into(img_league_match)
    }

    private fun showDataTeams(datas: List<Team>){
        rv_list_match.adapter =
            LeagueMatchTeamAdapter(datas)
    }

    private fun showDataStandings(datas: List<Table>) {
        rv_list_match.adapter = StandingsAdapter(datas)
    }

    private fun observeViewModel(position: Int?) {
        if (position == 0) {
            viewModel.setListPrevMatch().observe(this, Observer { t ->
                showDataEvent(t.events)
            })
        } else if (position == 1) {
            viewModel.setListNextMatch().observe(this, Observer { t ->
                showDataEvent(t.events)
            })
        } else if (position == 2) {
            viewModel.setListTeams().observe(this, Observer { t ->
                showDataTeams(t.teams)
            })
        } else if (position == 3) {
            viewModel.listStandings.observe(this, Observer { t ->
                showDataStandings(t.table)
            })
        }
    }
}

