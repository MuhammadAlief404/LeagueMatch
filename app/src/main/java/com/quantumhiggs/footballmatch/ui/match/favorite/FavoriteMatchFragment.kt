package com.quantumhiggs.footballmatch.ui.match.favorite


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.quantumhiggs.footballmatch.R
import com.quantumhiggs.footballmatch.db.database
import com.quantumhiggs.footballmatch.model.favorite.MatchFavorite
import com.quantumhiggs.footballmatch.model.favorite.TeamFavorite
import com.quantumhiggs.footballmatch.ui.match.favorite.adapter.FavoriteMatchAdapter
import com.quantumhiggs.footballmatch.ui.match.favorite.adapter.FavoriteTeamAdapter
import kotlinx.android.synthetic.main.fragment_favorite_match.*
import org.jetbrains.anko.db.SelectQueryBuilder
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavoriteMatchFragment : Fragment() {

    private lateinit var result: SelectQueryBuilder
    private lateinit var favorite: List<MatchFavorite>
    private lateinit var team: List<TeamFavorite>



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_match, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rv_list_favorite.layoutManager = LinearLayoutManager(context)

        showMatch()

        favorite_tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                observeViewModel(p0?.position)
            }

            override fun onTabReselected(p0: TabLayout.Tab?) {

            }
        })
    }

    private fun observeViewModel(position: Int?) {
        if (position == 0) {
            showMatch()
        } else {
            showTeam()
        }
    }

    private fun showTeam() {
        context?.database?.use {
            result = select(TeamFavorite.TABLE_TEAM)
            team = result.parseList(classParser())
            if (team.isNotEmpty()) {
                img_404_favorite.visibility = View.GONE
                rv_list_favorite.visibility = View.VISIBLE
                showDataTeam(team)
            } else {
                img_404_favorite.visibility = View.VISIBLE
                rv_list_favorite.visibility = View.GONE
            }
        }
    }

    private fun showMatch() {
        context?.database?.use {
            result = select(MatchFavorite.TABLE_FAVORITE)
            favorite = result.parseList(classParser())
            if (favorite.isNotEmpty()) {
                img_404_favorite.visibility = View.GONE
                rv_list_favorite.visibility = View.VISIBLE
                showData(favorite)
            } else {
                img_404_favorite.visibility = View.VISIBLE
                rv_list_favorite.visibility = View.GONE
            }
        }
    }

    private fun showData(data: List<MatchFavorite>) {
        rv_list_favorite.adapter =
            FavoriteMatchAdapter(data)
    }

    private fun showDataTeam(data: List<TeamFavorite>) {
        rv_list_favorite.adapter = FavoriteTeamAdapter(data)
    }
}
