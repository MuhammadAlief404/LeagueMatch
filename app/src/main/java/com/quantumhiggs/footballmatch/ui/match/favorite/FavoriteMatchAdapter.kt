package com.quantumhiggs.footballmatch.ui.match.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.quantumhiggs.footballmatch.R
import com.quantumhiggs.footballmatch.model.Event

class FavoriteMatchAdapter(private val sports: List<Event>) :
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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bindItems(sports[0])


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(match: Event) {

        }
    }
}