package com.quantumhiggs.footballmatch.ui.match.favorite.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.quantumhiggs.footballmatch.R
import com.quantumhiggs.footballmatch.model.favorite.TeamFavorite
import com.quantumhiggs.footballmatch.ui.match.search.SearchMatchFragmentDirections
import com.quantumhiggs.footballmatch.utils.CommonFunction.checkNullOrEmpty
import kotlinx.android.synthetic.main.item_list_team.view.*

class FavoriteTeamAdapter(private val team: List<TeamFavorite>) :
    RecyclerView.Adapter<FavoriteTeamAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_list_team,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = team.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bindItems(team[position])

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(team: TeamFavorite) {
            itemView.item_list_team_name.text = checkNullOrEmpty(team.teamName)
            Glide.with(itemView.context)
                .load(team.teamImg)
                .placeholder(R.drawable.ic_trophy)
                .into(itemView.item_list_team_logo)

            itemView.setOnClickListener {
                val direction = SearchMatchFragmentDirections.actionToDetailTeam(team.teamId!!)
                it.findNavController().navigate(direction)
            }
        }
    }


}