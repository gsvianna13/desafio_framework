package com.teste.desafio_tecnico_framework

import android.widget.TextView
import com.teste.desafio_tecnico_framework.framework.API
import com.teste.desafio_tecnico_framework.framework.FragmentListView
import com.teste.desafio_tecnico_framework.framework.WebService

class TodosFragment : FragmentListView<API.Todo>() {

    private val webService by lazy {
        WebService.create(API::class.java)
    }

    var list: List<API.Todo>? = null

    override suspend fun loadList(): List<API.Todo> {
        if (list == null) {

            list = webService.getTodos()

            return list!!
        }

        return list!!

    }

    override fun showItem(view: TextView, item: API.Todo) {
        view.textAsHtml = """
            <b>userId: </b>${item.userId}<br/>
            <b>id: </b>${item.id}<br/>
            <b>title: </b>${item.title}<br/>
            <b>completed: </b>${item.completed}<br/>
        """.trimIndent()
    }
}
