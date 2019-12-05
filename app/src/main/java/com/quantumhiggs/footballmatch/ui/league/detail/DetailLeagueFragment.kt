package com.quantumhiggs.footballmatch.ui.league.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.quantumhiggs.footballmatch.R
import com.quantumhiggs.footballmatch.model.League
import kotlinx.android.synthetic.main.fragment_detail_league_fragment.*


class DetailLeagueFragment : Fragment() {

    private lateinit var viewModel: DetailLeagueViewModel
    private lateinit var fanArt: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_league_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val args by navArgs<DetailLeagueFragmentArgs>()
        DetailLeagueViewModel.leagueId = args.leagueId
        viewModel = ViewModelProviders.of(this).get(DetailLeagueViewModel::class.java)
        viewModel.setDetailLeague().observe(this, Observer { t ->
            if (t != null) {
                showData(t.leagues)
            }

        })

        fab_detail_league.setOnClickListener {
            val direction = DetailLeagueFragmentDirections.actionLeagueMatch(
                DetailLeagueViewModel.leagueId,
                fanArt
            )
            it.findNavController().navigate(direction)
        }
    }

    private fun showData(datas: List<League>) {

        val data = datas[0]

        fanArt = data.strFanart1

        desc_detail_league.text = data.strDescriptionEN
        Glide.with(this)
            .load(data.strTrophy)
            .placeholder(R.drawable.ic_trophy)
            .into(img_detail_league)

    }
}
