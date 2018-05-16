# SQlite

## Objeto de tablas y base

```kotlin
class DatabaseSQLite {
    companion object {
        val DB_NAME = "user"
        val USR_TABLE_NAME = "usuario"
        val COL_ID = "id"
        val COL_NOMBRE = "nombre"
        val COL_EDAD = "edad"
        val COL_CASADO = "casado"
    }
}
```

## Objeto de DbHandler

```kotlin
class DbHandler(context: Context) : SQLiteOpenHelper(context, DatabaseSQLite.DB_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTableSQL = "CREATE TABLE ${DatabaseSQLite.USR_TABLE_NAME} (${DatabaseSQLite.COL_ID} INTEGER PRIMARY KEY AUTOINCREMENT, ${DatabaseSQLite.COL_NOMBRE} VARCHAR(50))"
        db?.execSQL(createTableSQL)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun insertarUsuario(nombre: String) {
        val dbWriteable = writableDatabase
        var cv = ContentValues()
        cv.put(DatabaseSQLite.COL_NOMBRE, nombre)
        val resultado = dbWriteable.insert(DatabaseSQLite.USR_TABLE_NAME, null, cv)
        Log.i("database", "Si es -1 hubo error, sino exito: Respuesta:$resultado")
        dbWriteable.close()
    }

    fun leerDatos() {
        val dbReadable = readableDatabase
        val query = "SELECT * FROM ${DatabaseSQLite.USR_TABLE_NAME}"
        val resultado = dbReadable.rawQuery(query, null)
        if (resultado.moveToFirst()) {
            do {
                val idActual = resultado.getString(0).toInt()
                val nombreActual = resultado.getString(1)
                Log.i("database", "El nombre es $nombreActual con id $idActual")
            } while (resultado.moveToNext())
        }
        resultado.close()
        dbReadable.close()
    }

}
```
