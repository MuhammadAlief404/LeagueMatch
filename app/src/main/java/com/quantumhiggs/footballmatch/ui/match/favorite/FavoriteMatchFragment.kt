package com.quantumhiggs.footballmatch.ui.match.favorite


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.quantumhiggs.footballmatch.R
import com.quantumhiggs.footballmatch.db.database
import com.quantumhiggs.footballmatch.model.Favorites
import kotlinx.android.synthetic.main.fragment_favorite_match.*
import org.jetbrains.anko.db.SelectQueryBuilder
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavoriteMatchFragment : Fragment() {

    private lateinit var result: SelectQueryBuilder
    private lateinit var favorite: List<Favorites>


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

        context?.database?.use {
            result = select(Favorites.TABLE_FAVORITE)
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

    private fun showData(data: List<Favorites>) {
        rv_list_favorite.adapter = FavoriteMatchAdapter(data)
    }
}
