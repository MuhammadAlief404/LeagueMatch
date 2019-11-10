package com.quantumhiggs.footballmatch.ui.league.list


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.quantumhiggs.footballmatch.R
import com.quantumhiggs.footballmatch.model.League
import kotlinx.android.synthetic.main.fragment_list_league.*

class ListLeagueFragment : Fragment() {

    private lateinit var viewModel: ListLeagueViewModel

    companion object {
        fun newInstance() = ListLeagueFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_league, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ListLeagueViewModel::class.java)

        rv_list_league.layoutManager = GridLayoutManager(context, 2)

        viewModel.setListLeague().observe(this, Observer { t ->
            showData(t.leagues)
        })
    }

    private fun showData(data: List<League>) {
        rv_list_league.adapter = ListLeagueAdapter(data)
    }
}
