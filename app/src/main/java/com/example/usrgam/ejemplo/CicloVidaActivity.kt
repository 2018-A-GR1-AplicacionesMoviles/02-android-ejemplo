package com.example.usrgam.ejemplo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class CicloVidaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ciclo_vida)
        Log.i("ciclo-vida", "Ejecuto: On Create")
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida", "Ejecuto: On Start")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("ciclo-vida", "Ejecuto: On Restart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("ciclo-vida", "Ejecuto: On Resume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("ciclo-vida", "Ejecuto: On Pause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("ciclo-vida", "Ejecuto: On Stop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("ciclo-vida", "Ejecuto: On Destroy")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        Log.i("ciclo-vida", "Ejecuto: On RestoreInstanceState")
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        Log.i("ciclo-vida", "Ejecuto: On SaveInstanceState")
    }
}
