# Intends

Para abrir una actividad:

```kotlin
 val intent = Intent(this, NombreDeLaClaseDeLaActividad::class.java)
        startActivity(intent)
```

Para abrir una actividad y mandar datos:

```kotlin
 val intent = Intent(this, NombreDeLaClaseDeLaActividad::class.java)
        intent.putExtra("llave", "Valor")
        startActivity(intent)
```


Para abrir una actividad de correo:
```kotlin
val addressees = arrayOf("direccion@unocom", "direccion@dos.com")
val intent = Intent(Intent.ACTION_SEND)
intent.type = "text/html"
intent.putExtra(Intent.EXTRA_EMAIL, addressees)
intent.putExtra(Intent.EXTRA_SUBJECT, "Sujeto")
intent.putExtra(Intent.EXTRA_TEXT, "Texto.")
startActivity(intent)
```

Para abrir una actividad con resultado:
```kotlin
startActivityForResult(Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI), IDENTIFICADOR_DE_RESPUESTA)
```
Para recibir la respuesta:

```kotlin
override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        when (requestCode) {
            IDENTIFICADOR_DE_RESPUESTA -> {
                if (resultCode == RESULT_OK) {
                    var cursor: Cursor? = null
                    try {
                        var phoneNo: String? = null
                        var name: String? = null
                        val uri = intent?.data
                        cursor = contentResolver.query(uri, null, null, null, null)
                        cursor!!.moveToFirst()
                        val phoneIndex = cursor!!.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                        val nameIndex = cursor!!.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                        phoneNo = cursor!!.getString(phoneIndex)
                        name = cursor!!.getString(nameIndex)
                        Log.i("create", "Nombre ${name}")
                        Log.i("create", "Numero de telefono ${phoneNo}")
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                if (resultCode == RESULT_CANCELED) {
                    Log.i("create", "Algo salio mal")
                }
            }
        }
    }
```

Para establecer una actividad que puede responder a cierto tipo de `intent`:
```xml
<intent-filter>
                <action android:name="android.intent.action.SEND" />
                <category android:name="android.intent.category.DEFAULT" />
                <data android:mimeType="text/plain" />
</intent-filter>
```

Para establecer una actividad como principal:

```xml
<intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
</intent-filter>
```
