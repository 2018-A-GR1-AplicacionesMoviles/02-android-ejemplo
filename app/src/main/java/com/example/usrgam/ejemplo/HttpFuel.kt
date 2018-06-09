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
                                Log.i("http-ejemplo", "Creado: ${entrenador.createdAt}")
                                Log.i("http-ejemplo", "Actualizado: ${entrenador.updatedAt}")
                            } else {
                                Log.i("http-ejemplo", "Entrenador nulo")
                            }


                        }
                    }
                }
    }
}

class Entrenador {
    var nombre: String
    var apellido: String
    var edad: String
    var medallas: String
    var createdAt: Date
    var updatedAt: Date
    var id: Int

    constructor(mNombre: String,
                mApellido: String,
                mEdad: String,
                mMedallas: String,
                mCreatedAt: Long,
                mUpdatedAt: Long,
                mId: Int) {
        nombre = mNombre
        apellido = mApellido
        edad = mEdad
        medallas = mMedallas
        createdAt = Date(mCreatedAt)
        updatedAt = Date(mUpdatedAt)
        id = mId

    }

}
