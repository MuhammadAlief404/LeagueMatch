package com.quantumhiggs.footballmatch.ui.match.search


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.quantumhiggs.footballmatch.R
import com.quantumhiggs.footballmatch.model.Event
import kotlinx.android.synthetic.main.fragment_search_match.*


class SearchMatchFragment : Fragment() {

    private lateinit var viewModel: SearchMatchViewModel

    companion object {
        fun newInstance() = SearchMatchFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_match, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(SearchMatchViewModel::class.java)

        list_search_match.layoutManager = LinearLayoutManager(context)

        viewModel.setListMatch().observe(this, Observer { t ->
            showData(t.event)
        })

        edt_search_match.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                viewModel.matchName = p0.toString()
                viewModel.setListMatch().observe(this@SearchMatchFragment, Observer { t ->
                    showData(t.event)
                })
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        })
    }

    private fun showData(datas: List<Event>) {
        list_search_match.adapter = SearchMatchAdapter(datas)
    }
}
