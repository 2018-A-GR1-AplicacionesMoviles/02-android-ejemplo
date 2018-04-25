# Permisos

Para establecer los permisos de la aplicacion:
```xml
<uses-permission android:name="android.permission.SEND_SMS" />
<uses-permission android:name="android.permission.CAMERA" /> 
```
    
Para pedir un permiso en el codigo:

```kotlin
val permission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA
        )
        Log.i("tag", "Crear permiso ${permission}")
        if (permission != PackageManager.PERMISSION_GRANTED) {
            Log.i("tag", "Entrando a pedir permiso")
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), REQUEST_PERMISSION)
        } else {
            Log.i("tag", "Ya tiene este permiso")
        }
```