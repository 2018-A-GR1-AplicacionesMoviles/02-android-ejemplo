package com.example.usrgam.ejemplo

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.github.kittinunf.fuel.httpGet
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async

class AnkoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anko)
        Log.i("async", "Empezo")
        async(UI) {
            llamarPost("1")
            Log.i("async", "Luego de llamar post")
        }
        Log.i("async", "Termino")
    }
    fun llamarPost(idPost: String) {
        val url = "https://jsonplaceholder.typicode.com/posts/$idPost"
        url.httpGet()
                .responseString { request, response, result ->
                    Log.i("async", "request: $request")
                    Log.i("async", "response: $response")
                    Log.i("async", "result: $result")
                }
    }
}
