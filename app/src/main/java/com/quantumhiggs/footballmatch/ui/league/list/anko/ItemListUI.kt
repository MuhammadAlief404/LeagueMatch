package com.quantumhiggs.footballmatch.ui.league.list.anko

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import com.quantumhiggs.footballmatch.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class ItemListUI : AnkoComponent<Context> {
    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        return linearLayout {
            orientation = LinearLayout.VERTICAL
            cardView {
                padding = dip(16)
                linearLayout {
                    orientation = LinearLayout.VERTICAL
                    imageView {
                        id = R.id.item_list_league_image
                        imageResource = R.drawable.ic_trophy
                    }.lparams(width = dip(100), height = dip(100)) {
                        gravity = Gravity.CENTER
                    }
                    textView {
                        id = R.id.item_list_league_name
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                        gravity = Gravity.CENTER
                    }.lparams(width = matchParent, height = matchParent) {
                        margin = dip(10)
                    }
                }.lparams(width = matchParent, height = matchParent)
            }
        }
    }
}