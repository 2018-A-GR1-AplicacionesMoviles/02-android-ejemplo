# Ciclo de vida

El ciclo de vida de las Actividades tiene varios eventos que sirven para distintos tipos de necesidades.

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("create", "Se esta creando la actividad")

        super.onCreate(savedInstanceState)
        }
        
override fun onStart() {
        super.onStart()
        Log.i("create", "Se esta starteando la actividad")
    }

override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        Log.i("create", "Se esta restorando la instancia de la actividad")
        texto_central.text = savedInstanceState?.getString("GAME_STATE_KEY")
    }
    
override fun onSaveInstanceState(outState: Bundle?) {
        outState?.run {
            Log.i("create", "Se esta guardando el estado de la instancia de esta actividad")
            numeroDeVeces++
            putString("GAME_STATE_KEY", "$estadoDelJuego $numeroDeVeces")
            put
        }
        // call superclass to save any view hierarchy
        super.onSaveInstanceState(outState)
    }
    
verride fun onResume() {
        super.onResume()
        Log.i("create", "Resumiendo, iniciando recursos como la camara por ejemplo")
    }
    
override fun onPause() {
        super.onPause()
        Log.i("create", "Pausando, momento para liberar la memoria y recursos")
    }
    
    

override fun onStop() {
        super.onStop()
        Log.i("create", "Se esta parando la actividad aqui se debe de liberar todos los recursos de interfaz, tambien es un buen lugar para almacenar informacion no volatil")
    }
    
    
override fun onDestroy() {
        super.onDestroy()
        Log.i("create", "Va a destruirse la vista, adios a todos")
    }
    
override fun finish() {
        super.finish()
        Log.i("create", "Finalizando, El usuario dijo que se quiere ir de esta Actividad")
    }
    
    
    
```
