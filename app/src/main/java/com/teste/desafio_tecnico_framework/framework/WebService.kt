package com.teste.desafio_tecnico_framework.framework

import android.widget.TextView
import androidx.core.text.HtmlCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

object WebService {

    //Assim que setar a url, cria a instância do retrofit
    var baseURL: String = ""
        set(value) {
            if (value != field) {
                retrofit = createRetrofit(value)
                field = value
            }
        }

    var retrofit: Retrofit? = null

    fun <T> create(service: Class<T>): T {
        return retrofit!!.create(service)
    }

    private fun createRetrofit(url: String): Retrofit {

        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}

fun <T> runScope(context: CoroutineContext = EmptyCoroutineContext,
                 block: suspend CoroutineScope.() -> T) {
    runBlocking(context) {
        block()
    }
}

suspend fun <T> await(block: suspend CoroutineScope.() -> T): T = withContext(Dispatchers.Default) {
    block()
}

interface API {

    class Post(val userId: String, val id: String, val title: String, val body: String)

    class Album(val userId: String, val id: String, val title: String)

    class Todo(val userId: String, val id: String, val title: String, val completed: Boolean)

    @GET("/posts")
    suspend fun getPosts(): List<Post>

    @GET("/albums")
    suspend fun getAlbums(): List<Album>

    @GET("/todos")
    suspend fun getTodos(): List<Todo>
}