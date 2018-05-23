package com.example.usrgam.ejemplo

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.PopupMenu

import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.content_menu.*
import java.nio.file.Files.delete
import android.R.id.edit
import android.widget.AdapterView.AdapterContextMenuInfo



class MenuActivity : AppCompatActivity(), PopupMenu.OnMenuItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        setSupportActionBar(toolbar)

        val numeros = arrayListOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, numeros)

        list_view_numeros.adapter = adapter

        registerForContextMenu(list_view_numeros)












        fab.setOnClickListener { view ->

            val popup = PopupMenu(this, view)

            popup.setOnMenuItemClickListener(this)

            val inflater = popup.menuInflater

            inflater.inflate(R.menu.pop_up_menu, popup.menu)

            popup.show()



            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()


        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.item_menu_pokemon -> {
                Log.i("menu", "Menu pokemon")
                return true
            }
            R.id.item_menu_settings -> {
                Log.i("menu", "Menu Settings")
                return true
            }
            R.id.item_menu_nuevo1 -> {
                Log.i("menu", "Menu Nuevo 1")
                return true
            }
            R.id.item_menu_aceptar -> {
                Log.i("menu", "Aceptar")
                return true
            }
            R.id.item_menu_cancelar -> {
                Log.i("menu", "Cancelar")
                return true
            }
            else -> {
                Log.i("menu", "Todos los demas")
                return super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.item_menu_aceptar -> {
                Log.i("menu", "Aceptar")
                return true
            }
            R.id.item_menu_cancelar -> {
                Log.i("menu", "Cancelar")
                return true
            }
            else -> {
                Log.i("menu", "Todos los demas")
                return super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onCreateContextMenu(
            menu: ContextMenu,
            v: View,
            menuInfo: ContextMenu.ContextMenuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo)

        val inflater = menuInflater
        inflater.inflate(R.menu.context_menu, menu)


    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterContextMenuInfo
        when (item.getItemId()) {
            R.id.item_menu_guardar -> {
                Log.i("menu", "Guardar ${info.id} ${info.position}")
                return true
            }
            R.id.item_menu_editar -> {
                Log.i("menu", "Editar")
                return true
            }
            R.id.item_menu_borrar -> {
                Log.i("menu", "Borrar")
                return true
            }
            else -> {
                Log.i("menu", "Todos los demas")
                return super.onOptionsItemSelected(item)
            }
        }
    }


}
