package com.quantumhiggs.footballmatch.ui.match.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.quantumhiggs.footballmatch.R
import com.quantumhiggs.footballmatch.model.Event
import com.quantumhiggs.footballmatch.utils.CommonFunction
import kotlinx.android.synthetic.main.item_list_match.view.*

class SearchMatchAdapter(private val sports: List<Event>?) :
    RecyclerView.Adapter<SearchMatchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_list_match,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = sports?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bindItems(sports?.get(position))

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(sport: Event?) {
            itemView.item_list_match_title.text = CommonFunction.checkNullOrEmpty(sport?.strEvent)
            itemView.item_list_match_subtitle.text =
                CommonFunction.checkNullOrEmpty(sport?.dateEvent)
            itemView.item_list_match_name_left.text =
                CommonFunction.checkNullOrEmpty(sport?.strHomeTeam)
            itemView.item_list_match_name_right.text =
                CommonFunction.checkNullOrEmpty(sport?.strAwayTeam)
            itemView.item_list_match_score.text =
                CommonFunction.checkNullOrEmpty(sport?.intHomeScore) + " : " + CommonFunction.checkNullOrEmpty(
                    sport?.intAwayScore
                )

            itemView.setOnClickListener {
                val direction =
                    SearchMatchFragmentDirections.actionSearchMatchFragmentToDetailMatchFragment(
                        sport?.idEvent ?: ""
                    )
                it.findNavController().navigate(direction)
            }
        }
    }

}