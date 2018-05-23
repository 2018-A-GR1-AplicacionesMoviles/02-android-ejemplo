# Menus

## menu resource xml

Podemos crear recursos de menu en la carpeta de res/menu/nombre_del_menu.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:android="http://schemas.android.com/apk/res/android">

    <item
        android:id="@+id/help"
        android:icon="@drawable/icono"
        android:title="Ayuda"
        app:showAsAction="always" />

    <item
        android:id="@+id/help2"
        android:icon="@drawable/icono"
        android:title="Ayuda otra"
        app:showAsAction="always" />

    <item android:id="@+id/new_game2"
        android:title="Nuevo juego 2"
        android:icon="@drawable/icono"/>


    <item android:id="@+id/file"
        android:title="Titulo" >
        <!-- "file" submenu -->
        <menu>
            <item android:id="@+id/create_new"
                android:title="Nuevo" />
            <item android:id="@+id/open"
                android:title="Abrir" />
        </menu>
    </item>

    <item android:id="@+id/file2"
        android:title="Titulo 2" >
        <!-- "file" submenu -->
        <menu>
            <item android:id="@+id/create_new2"
                android:title="Nuevo2" />
            <item android:id="@+id/open2"
                android:title="Abrir2" />
        </menu>
    </item>
</menu>
```

## Options menu

```kotlin
override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)
        return true
    }
```

cuando se selecciona un menuItem de cualquier tipo de menu se utiliza:

```kotlin
 override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        when (item.getItemId()) {
            R.id.create_new2 -> {

                return true
            }
            R.id.help -> {
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
```

## Context menu

Los menus contextuales sirven en listas como los ListView o GridView u otros parecidos

Primero creamos un ListView

```kotlin
val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, paises)
        lista.adapter = adapter
        registerForContextMenu(lista)
```

Luego creamos el context menu

```kotlin
override fun onCreateContextMenu(menu: ContextMenu, v: View,
                                     menuInfo: ContextMenuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.contextual_menu, menu)
    }
```
