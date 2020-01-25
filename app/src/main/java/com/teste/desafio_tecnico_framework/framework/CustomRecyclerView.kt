package com.teste.desafio_tecnico_framework.framework

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CustomRecyclerView<T: Any>: RecyclerView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    var list = mutableListOf<T>()
    var adapter: Adapter? = null

    fun setAdapter(
        items: List<T>,
        itemLayout: Int,
        onShowing: (holder: ViewHolder, item: T) -> Unit
    ) {
        this.layoutManager = LinearLayoutManager(context)
        this.setHasFixedSize(true)

        this.list = items.toMutableList()

        adapter = Adapter(itemLayout, onShowing)
        this.setAdapter(adapter)
    }


    //-------------------------------------------------------------------------- Adapter
    inner class Adapter(
        private val itemLayout: Int,
        private val onShowing: (holder: ViewHolder, item: T) -> Unit
    ) : RecyclerView.Adapter<Adapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(itemLayout, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, i: Int) {
            val item = list[i]
            onShowing.invoke(holder, item)
        }

        override fun getItemCount(): Int {
            return list.size
        }

        //-------------------------------------------------------------------------- ViewHolder
        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

            init {
                view.setOnClickListener { onClick() }
                view.setOnLongClickListener { onLongClick() }
            }

            private fun onClick() {
                val index = adapterPosition
                val item = list[index]
            }

            private fun onLongClick(): Boolean {
                val index = adapterPosition
                val item = list[index]

                return true
            }
        }
    }
}