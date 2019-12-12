package com.quantumhiggs.footballmatch.ui.match.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.quantumhiggs.footballmatch.R
import com.quantumhiggs.footballmatch.model.Table
import com.quantumhiggs.footballmatch.utils.CommonFunction.checkNullOrEmpty
import kotlinx.android.synthetic.main.item_list_standings.view.*

class StandingsAdapter(private val standings: List<Table>) :
    RecyclerView.Adapter<StandingsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_list_standings,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = standings.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bindItems(standings[position])

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(standing: Table) {
            itemView.item_standings_title.text = checkNullOrEmpty(standing.name)
            itemView.item_standing_played.text = checkNullOrEmpty(standing.played.toString())
            itemView.item_standings_win.text = checkNullOrEmpty(standing.win.toString())
            itemView.item_standings_draw.text = checkNullOrEmpty(standing.draw.toString())
            itemView.item_standings_loss.text = checkNullOrEmpty(standing.loss.toString())
            itemView.item_standings_total.text = checkNullOrEmpty(standing.total.toString())
        }
    }


}