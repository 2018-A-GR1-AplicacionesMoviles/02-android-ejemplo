package com.example.usrgam.ejemplo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_crear_usuario.*

class CrearUsuarioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_usuario)

        boton_crear_usuario
                .setOnClickListener { view: View -> crearUsuario() }
    }
    fun crearUsuario() {
        val nombre = edit_text_nombre.text.toString()
        val apellido = edit_text_apellido.text.toString()
        val color = edit_text_color.text.toString()
        Factory.usuarios.add(Usuario(nombre, apellido, color))
        irAActividadSpinner()
    }
    fun irAActividadSpinner() {
        val intent = Intent(this, SpinnerActivity::class.java)
        startActivity(intent)
    }
}
