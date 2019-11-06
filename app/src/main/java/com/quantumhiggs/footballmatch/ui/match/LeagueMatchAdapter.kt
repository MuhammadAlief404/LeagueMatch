package com.quantumhiggs.footballmatch.ui.match

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.quantumhiggs.footballmatch.R
import com.quantumhiggs.footballmatch.model.Event
import kotlinx.android.synthetic.main.item_list_match.view.*

class LeagueMatchAdapter(val sports: List<Event>) : RecyclerView.Adapter<LeagueMatchAdapter.ViewHoler>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHoler =
        ViewHoler(LayoutInflater.from(parent.context).inflate(R.layout.item_list_match, parent, false))

    override fun getItemCount(): Int = sports.size

    override fun onBindViewHolder(holder: ViewHoler, position: Int) = holder.bindItems(sports.get(position))

    class ViewHoler(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(sport: Event) {
            itemView.item_list_match_title.text = sport.strEvent
            itemView.item_list_match_subtitle.text = sport.dateEvent
            itemView.item_list_match_name_left.text = sport.strHomeTeam
            itemView.item_list_match_name_right.text = sport.strAwayTeam
            itemView.item_list_match_score.text = sport.intHomeScore + " : " + sport.intAwayScore

        }

    }


}