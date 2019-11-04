package com.quantumhiggs.footballmatch.ui.league.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.quantumhiggs.footballmatch.R
import com.quantumhiggs.footballmatch.model.League
import kotlinx.android.synthetic.main.item_list_league.view.*

class ListLeagueAdapter(val league: List<League>) :
    RecyclerView.Adapter<ListLeagueAdapter.ViewHoler>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHoler {
        return ViewHoler(LayoutInflater.from(parent.context).inflate(R.layout.item_list_league, parent, false))
    }

    override fun getItemCount(): Int = league.size

    override fun onBindViewHolder(holder: ViewHoler, position: Int) = holder.bindItems(league[position])

    class ViewHoler(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(items: League) {
            itemView.item_list_league_name.text = items.strLeague

            Glide.with(itemView.context)
                .load(items.strTrophy)
                .placeholder(R.drawable.ic_trophy)
                .into(itemView.item_list_league_image)

            itemView.setOnClickListener {
                it.findNavController().navigate(R.id.action_detail_league)
            }
        }
    }
}