package com.quantumhiggs.footballmatch.ui.match

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.quantumhiggs.footballmatch.R
import com.quantumhiggs.footballmatch.model.Event
import com.quantumhiggs.footballmatch.utils.CommonFunction.checkNullOrEmpty
import kotlinx.android.synthetic.main.item_list_match.view.*

class LeagueMatchAdapter(private val sports: List<Event>) :
    RecyclerView.Adapter<LeagueMatchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_list_match,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = sports.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bindItems(sports.get(position))

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(sport: Event) {
            itemView.item_list_match_title.text = checkNullOrEmpty(sport.strEvent)
            itemView.item_list_match_subtitle.text = checkNullOrEmpty(sport.dateEvent)
            itemView.item_list_match_name_left.text = checkNullOrEmpty(sport.strHomeTeam)
            itemView.item_list_match_name_right.text = checkNullOrEmpty(sport.strAwayTeam)
            itemView.item_list_match_score.text =
                checkNullOrEmpty(sport.intHomeScore) + " : " + checkNullOrEmpty(sport.intAwayScore)

            itemView.setOnClickListener {
                val direction = LeagueMatchFragmentDirections.actionDetailMatch(sport.idEvent)
                it.findNavController().navigate(direction)
            }

        }
    }


}