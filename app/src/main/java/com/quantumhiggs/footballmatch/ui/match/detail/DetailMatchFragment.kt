package com.quantumhiggs.footballmatch.ui.match.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.quantumhiggs.footballmatch.R
import com.quantumhiggs.footballmatch.model.Event
import com.quantumhiggs.footballmatch.utils.CommonFunction.checkNullOrEmpty
import kotlinx.android.synthetic.main.fragment_detail_match.*

class DetailMatchFragment : Fragment() {

    private lateinit var viewModel: DetailMatchViewModel

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
        score_card_detail_match.text = checkNullOrEmpty(data.intHomeScore) + " : " + checkNullOrEmpty(data.intAwayScore)

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

    }

}
