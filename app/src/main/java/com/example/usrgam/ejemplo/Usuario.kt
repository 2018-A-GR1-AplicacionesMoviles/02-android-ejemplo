package com.example.usrgam.ejemplo

/**
 * Created by USRGAM on 07/05/2018.
 */

class Usuario constructor(var nombre: String,
                          var apellido: String,
                          var colorFavorito: String) {

    override fun toString(): String {
        return "$nombre $apellido"
    }
}

