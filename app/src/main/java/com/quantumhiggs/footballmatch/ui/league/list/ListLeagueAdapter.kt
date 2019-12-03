package com.quantumhiggs.footballmatch.ui.league.list

import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.quantumhiggs.footballmatch.R
import com.quantumhiggs.footballmatch.model.League
import com.quantumhiggs.footballmatch.ui.league.list.anko.ItemListUI
import kotlinx.android.synthetic.main.item_list_league.view.*
import org.jetbrains.anko.AnkoContext

class ListLeagueAdapter(val league: List<League>) :
    RecyclerView.Adapter<ListLeagueAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemListUI().createView(AnkoContext.Companion.create(parent.context))
    )

    override fun getItemCount(): Int = league.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bindItems(league[position])

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(items: League) {
            itemView.item_list_league_name.text = items.strLeague

            Glide.with(itemView.context)
                .load(items.strTrophy)
                .placeholder(R.drawable.ic_trophy)
                .into(itemView.item_list_league_image)

            itemView.setOnClickListener {
                val direction = ListLeagueFragmentDirections.actionDetailLeague(items.idLeague)
                it.findNavController().navigate(direction)
            }
        }
    }
}