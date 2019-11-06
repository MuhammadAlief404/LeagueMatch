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


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class DetailLeagueFragmant : Fragment() {

    private lateinit var viewModel: DetailLeagueViewModel

//    val args: ConfirmationFragmentArgs by navArgs()

    companion object {
        fun newInstance() = DetailLeagueFragmant()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_league_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val args by navArgs<DetailLeagueFragmantArgs>()
        DetailLeagueViewModel.leaugeId = args.leagueId
        viewModel = ViewModelProviders.of(this).get(DetailLeagueViewModel::class.java)
        viewModel.setDetailLeague().observe(this, Observer { t ->
            t.leagues.let { showData(it) }
        })

        fab_detail_league.setOnClickListener {
            val direction = DetailLeagueFragmantDirections.actionLeagueMatch(DetailLeagueViewModel.leaugeId)
            it.findNavController().navigate(direction)
        }
    }

    fun showData(datas: List<League>) {

        val data = datas.get(0)

        desc_detail_league.text = data.strDescriptionEN
        Glide.with(this)
            .load(data.strTrophy)
            .placeholder(R.drawable.ic_trophy)
            .into(img_detail_league)

    }
}
