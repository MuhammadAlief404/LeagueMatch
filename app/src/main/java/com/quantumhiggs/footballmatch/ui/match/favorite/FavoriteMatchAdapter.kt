package com.quantumhiggs.footballmatch.ui.match.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.quantumhiggs.footballmatch.R
import com.quantumhiggs.footballmatch.model.Favorites
import kotlinx.android.synthetic.main.item_list_match.view.*

class FavoriteMatchAdapter(private val sports: List<Favorites>) :
    RecyclerView.Adapter<FavoriteMatchAdapter.ViewHolder>() {

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
        holder.bindItems(sports[position])

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(match: Favorites) {
            itemView.item_list_match_title.text = match.eventName
            itemView.item_list_match_subtitle.text = match.dateEvent
            itemView.item_list_match_name_left.text = match.homeName
            itemView.item_list_match_name_right.text = match.awayName
            itemView.item_list_match_score.text = match.homeScore + " : " + match.awayScore

            itemView.setOnClickListener {
                val direction =
                    FavoriteMatchFragmentDirections.actionFavToDetailMatch(match.eventId.toString())
                it.findNavController().navigate(direction)
            }
        }
    }
}