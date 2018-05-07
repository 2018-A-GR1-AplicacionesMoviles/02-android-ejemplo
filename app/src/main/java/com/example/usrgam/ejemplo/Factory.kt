package com.example.usrgam.ejemplo

/**
 * Created by USRGAM on 07/05/2018.
 */
class Factory {
    companion object {
        var usuarios:ArrayList<Usuario> = ArrayList()

        init {
            usuarios.add(Usuario("Adrian", "Eguez", "Azul"))
            usuarios.add(Usuario("Vicente", "Sarzosa", "Cafe"))
            usuarios.add(Usuario("Wendy", "Eguez", "Blanco"))
        }

    }
}