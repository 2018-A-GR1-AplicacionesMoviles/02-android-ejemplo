# Android cheatsheet

## Resources
Los recursos para ser utilizados en la aplicación están dentro de la carpeta `res`
Algunos recursos son:
- Strings
- Colors
- Imágenes
- Fuentes
- etc...
### Strings
Los recursos se los localizan en la carpeta `values`. Normalmente su utiliza el nombre del archivo `xml` usado para guardar los valores de strings como `strings.xml`.

Ejemplo:
```
<resources>
    <string name="app_name">Ejemplo</string>
    <string name="action_settings">Settings</string>
</resources>
```
La etiqueta `<resources></resources>` contiene los valores del recurso, en este caso usamos solo strings pero podemos usar otros recursos.

La etiqueta `<string>` es la que contiene el nombre de la variable y el valor.

### Dimensiones
También se puede guardar valores de dimensiones u otros valores usados en las propiedades de un componente de interfaz como por ejemplo `dp` ( píxeles de densidad independiente ). Podriamos usar el nombre `dimens.xml` para dimensiones.

Ejemplo:
```
<resources>
    <dimen name="fab_margin">16dp</dimen>
</resources>
```

### Colores
También podemos definir colores como variables globales. Se podría usar el nombre de archivo `colors.xml`.

Ejemplo:
```
<resources>
    <color name="colorPrimary">#3F51B5</color>
    <color name="colorPrimaryDark">#303F9F</color>
    <color name="colorAccent">#FF4081</color>
</resources>
```

### Estilos
Los estilos de ciertos componentes pueden ser definidos y luego aplicados a ciertos elementos.

Ejemplo:
```
<resources>
    <style name="myEstilo">
          <item name="android:background">#817c7c</item>
          <item name="android:textColor">@android:color/holo_red_dark</item>
    </style>
</resources>
```
En este ejemplo el nombre del estilo se llama `myEstilo` y tiene dos valores que aplicar a los componentes en donde se especifique su use.
La primera propiedad a aplicarse es el `android:background` con el color `#817c7c`.
La segunda propiedad a aplicarse es el `android:textColor` referenciando a la variable tipo `string` de android llamada: `@android:color/holo_red_dark`.

Para usar ese estilo se lo hace de la siguiente manera:

```
<Button
        ....
        ....
        style="@style/myEstilo"
```

Para una guía completa puede visitar el sitio oficial: [Estilos android](https://developer.android.com/guide/topics/ui/look-and-feel/themes.html)

### Fuentes
Las fuentes pueden ser descargadas o generadas con algún programa y deben de guardarse dentro de la carpeta `font` si no se tiene esta carpeta se la debe crear.
Luego podemos guardar directamente los archivos `.ttf` y referenciarlos en el archivo xml de la siguiente manera:

 ```
<?xml version="1.0" encoding="utf-8"?>
<font-family xmlns:android="http://schemas.android.com/apk/res/android">
    <font
        android:font="@font/gugi_regular"
        android:fontWeight="600" />
</font-family>
 ```
 
 En nuestra carpeta se encontrarian estos dos archivos:
```
res/
     /gugi_regular.ttf
     /gugi.xml
```
Por último para poder usarlo se usa de la siguiente manera:
```
    <Button
        ....
        ....
        android:fontFamily="@font/gugi"/>
```

 ### Drawable
 En esta carpeta llamada `drawable` se encuentran los archivos que pueden ser dibujados en la interfaz. Algunos de ellos son .png, .jpg, o .gif. 
 En este caso solo necesitamos copiar a esta carpeta los archivos y usarlos en el xml de la siguiente manera:
 
 ```
<ImageView
    ....
    ....
    app:srcCompat="@drawable/imagen" />
 ```
### Midmap
 Terminando, los midmap son archivos para definir los tamaños de imagenes o a su ves los vectores a dibujarse como iconos de la aplicación.
 Estos archivos pueden ser originalmente archivos `.svg` y luego transformados al esquema de android de vector `http://schemas.android.com/apk/res/android"`.
 
### Layout
La carpeta `layout` es la encargada de guardar los xml de las interfaces de nuestra aplicación. Normalmente se las usa una por actividad pero podemos reutilizar layouts en otros lugares como en un `RecyclerView` para cambiar el estilo por defecto de las listas, así como en otros lugares.



 
 
 
 

