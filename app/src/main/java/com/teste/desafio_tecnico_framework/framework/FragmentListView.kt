package com.teste.desafio_tecnico_framework.framework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.teste.desafio_tecnico_framework.R
import kotlinx.android.synthetic.main.item_view.view.*

abstract class FragmentListView<T: Any>: Fragment() {

    lateinit var listView: CustomRecyclerView<T>
    lateinit var swipeRefresh: SwipeRefreshLayout
    abstract suspend fun loadList(): List<T>
    abstract fun showItem(view: TextView, item: T)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list_view, container, false)

        this.listView = view.findViewById(R.id.listView)
        this.swipeRefresh = view.findViewById(R.id.swipeRefresh)

        // refresh com swipe...
        swipeRefresh.setOnRefreshListener { refresh() }

        onLoad()

        return view
    }

    open fun onLoad() {
        refresh()
    }

    private fun refresh() {

        runScope {
            val list = await { loadList() }

            showList(list)

            swipeRefresh.isRefreshing = false
        }
    }

    private fun showList(list: List<T>) {

        listView.setAdapter(list, R.layout.item_view) { holder, item ->
            val view = holder.itemView.card.view
            showItem(view, item)
        }
    }

    var TextView.textAsHtml: String
        get() { return this.text.toString() }
        set(value) { this.text = HtmlCompat.fromHtml(value, HtmlCompat.FROM_HTML_MODE_COMPACT) }
}