package com.example.usrgam.ejemplo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result

class HttpFuel : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http_fuel)

        "http://172.29.64.52:1337/Entrenador/3"
                .httpGet().responseString { request, response, result ->
                    when (result) {
                        is Result.Failure -> {
                            val ex = result.getException()
                            Log.i("http-ejemplo", "Error ${ex.response}")
                        }
                        is Result.Success -> {
                            val data = result.get()
                            Log.i("http-ejemplo", "Exito ${data}")
                        }
                    }
                }
    }
}

class Entrenador(var nombre:String,
                 var apellido:String,
                 var edad:String,
                 var medallas:String,
                 var id:Int,
                 var createdAt:Int,
                 var updatedAt:Int) {

}
