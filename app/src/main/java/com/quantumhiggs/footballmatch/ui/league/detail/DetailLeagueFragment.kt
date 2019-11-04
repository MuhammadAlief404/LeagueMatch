package com.quantumhiggs.footballmatch.ui.league.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.quantumhiggs.footballmatch.R
import com.quantumhiggs.footballmatch.model.League
import kotlinx.android.synthetic.main.fragment_detail_league_fragment.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class DetailLeagueFragmant : Fragment() {

    private lateinit var viewModel: DetailLeagueViewModel

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
        viewModel = ViewModelProviders.of(this).get(DetailLeagueViewModel::class.java)

        viewModel.setDetailLeague().observe(this, Observer { t ->
            t.leagues.let { showData(it) }
        })
    }

    fun showData(data: List<League>) {

        desc_detail_league.text = data.get(0).strDescriptionEN

    }
}
