package com.example.usrgam.ejemplo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_spinner.*

class SpinnerActivity : AppCompatActivity() {

    var arregloUsuarios: ArrayList<Usuario> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spinner)

        arregloUsuarios = Factory.usuarios

        val adaptadorSpinnerUsuarios = ArrayAdapter<Usuario>(
                this,
                android.R.layout.simple_spinner_item,
                arregloUsuarios)

        adaptadorSpinnerUsuarios
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner_usuario.adapter = adaptadorSpinnerUsuarios


        spinner_usuario.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Log.i("spinner", "ID: $id Posicion: $position ")

                val usuarioSeleccionado = arregloUsuarios.get(position)

                text_view_apellido_grande.text = usuarioSeleccionado.apellido
                text_view_apellido.text = usuarioSeleccionado.apellido
                text_view_nombre.text = usuarioSeleccionado.nombre
                text_view_color.text = usuarioSeleccionado.colorFavorito


            }

        }

    }
}
