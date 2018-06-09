package com.example.usrgam.ejemplo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import java.util.*

class HttpFuel : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http_fuel)

        "http://172.29.64.52:1337/Entrenador/3"
                .httpGet()
                .responseString { request, response, result ->
                    when (result) {
                        is Result.Failure -> {
                            val ex = result.getException()
                            Log.i("http-ejemplo", "Error ${ex.response}")
                        }
                        is Result.Success -> {
                            val jsonStringEntrenador = result.get()
                            Log.i("http-ejemplo", "Exito ${jsonStringEntrenador}")

                            val entrenador: Entrenador? = Klaxon()
                                    .parse<Entrenador>(jsonStringEntrenador)

                            if (entrenador != null) {
                                Log.i("http-ejemplo", "Nombre: ${entrenador.nombre}")
                                Log.i("http-ejemplo", "Apellido: ${entrenador.apellido}")
                                Log.i("http-ejemplo", "Id: ${entrenador.id}")
                                Log.i("http-ejemplo", "Medallas: ${entrenador.medallas}")
                                Log.i("http-ejemplo", "Edad: ${entrenador.edad}")
                                Log.i("http-ejemplo", "Creado: ${entrenador.createdAtDate}")
                                Log.i("http-ejemplo", "Actualizado: ${entrenador.updatedAtDate}")
                            } else {
                                Log.i("http-ejemplo", "Entrenador nulo")
                            }


                        }
                    }
                }
    }
}

class Entrenador(var nombre: String,
                 var apellido: String,
                 var edad: String,
                 var medallas: String,
                 var createdAt: Long,
                 var updatedAt: Long,
                 var id: Int,
                 var pokemons: ArrayList<Pokemon> = ArrayList()) {
    var createdAtDate = Date(updatedAt)
    var updatedAtDate = Date(createdAt)


}

class Pokemon(var nombre: String,
              var numero: Int,
              var tipo: String,
              var createdAt: Long,
              var updatedAt: Long,
              var id: Int,
              var entrenadorId: Int) {
    var createdAtDate = Date(updatedAt)
    var updatedAtDate = Date(createdAt)
}
