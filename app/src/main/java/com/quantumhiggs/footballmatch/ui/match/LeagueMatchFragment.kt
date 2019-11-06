package com.quantumhiggs.footballmatch.ui.match


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.quantumhiggs.footballmatch.R
import com.quantumhiggs.footballmatch.model.Event
import kotlinx.android.synthetic.main.fragment_league_match.*


class LeagueMatchFragment : Fragment() {

    private lateinit var viewModel: LeagueMatchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_league_match, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val args by navArgs<LeagueMatchFragmentArgs>()
        LeagueMatchViewModel.leaugeId = args.leagueId
        viewModel = ViewModelProviders.of(this).get(LeagueMatchViewModel::class.java)

        rv_list_match.layoutManager = LinearLayoutManager(context)

        viewModel.setListPrevMatch().observe(this, Observer { t ->
            t.events.let { showData(it) }
        })

//        tab_next_league_match.setOnClickListener{
//            viewModel.setListNextMatch().observe(this, Observer { t ->
//                t.events.let { showData(it) }
//            })
//        }
//
//        tab_prev_league_match.setOnClickListener {
//            viewModel.setListPrevMatch().observe(this, Observer { t ->
//                t.events.let { showData(it) }
//            })
//        }

    }

    fun showData(datas: List<Event>) {

        rv_list_match.adapter = LeagueMatchAdapter(datas)

    }
}
