# ML kit de google

## Codigo Activity 

```kotlin
class MainActivity : AppCompatActivity() {
    var mPathActualFoto = ""
    val TOMAR_FOTO_REQUEST = 1
    var respuestasBarCode = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button_photo.setOnClickListener { v ->
            tomarFoto()
        }
    }

    private fun tomarFoto() {
        val archivoImagen = crearArchivo("JPEG_", Environment.DIRECTORY_PICTURES, ".jpg")
        mPathActualFoto = archivoImagen.absolutePath
        enviarIntentFoto(archivoImagen)
    }

    private fun crearArchivo(prefijo: String, directorio: String, extension: String): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = prefijo + timeStamp + "_"
        val storageDir = getExternalFilesDir(directorio)
        return File.createTempFile(
                imageFileName, /* prefix */
                extension, /* suffix */
                storageDir      /* directory */
        )
    }

    private fun enviarIntentFoto(archivo: File) {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val photoURI: Uri = FileProvider.getUriForFile(
                this,
                "com.example.adrianeguez.mlkit.fileprovider",
                archivo)
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takePictureIntent, TOMAR_FOTO_REQUEST);
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            TOMAR_FOTO_REQUEST -> {
                if (resultCode == Activity.RESULT_OK) {
                    mostrarFotoImageView()
                    obtenerInfoCodigoBarras(obtenerBitmapDeArchivo(mPathActualFoto))
                }
            }

        }
    }

    fun mostrarFotoImageView() {
        imageView_qr.setImageBitmap(obtenerBitmapDeArchivo(mPathActualFoto))
    }

    fun obtenerBitmapDeArchivo(path: String): Bitmap {
        val archivoImagen = File(path)
        return BitmapFactory.decodeFile(archivoImagen.getAbsolutePath())
    }

    fun obtenerInfoCodigoBarras(bitmap: Bitmap) {
        val image = FirebaseVisionImage.fromBitmap(bitmap)
        val detector = FirebaseVision.getInstance()
                .visionBarcodeDetector
        Log.i("info", "------- Entro a detectar")
        val result = detector.detectInImage(image)
                .addOnSuccessListener { barCodes ->
                    Log.i("info", "------- tamano del barcode ${barCodes.size}")
                    respuestasBarCode.add("Ejemplo")
                    for (barcode in barCodes) {
                        val bounds = barcode.getBoundingBox()
                        val corners = barcode.getCornerPoints()

                        val rawValue = barcode.getRawValue()

                        Log.i("info", "------- $bounds")
                        Log.i("info", "------- $corners")
                        Log.i("info", "------- $rawValue")

                        respuestasBarCode.add(rawValue.toString())
                    }

                    val adaptadorListView = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, respuestasBarCode)
                    list_view.adapter = adaptadorListView
                }
                .addOnFailureListener {
                    Log.i("info", "------- No reconocio nada")
                }
    }
}

class GenericFileProvider : FileProvider()
```

## Gradle app

```gradle
dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation"org.jetbrains.kotlin:kotlin-stdlib-jre7:$kotlin_version"
    implementation 'com.android.support:appcompat-v7:27.1.1'
    implementation 'com.android.support.constraint:constraint-layout:1.1.1'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.2'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.2'
    compile 'com.google.firebase:firebase-core:16.0.0'
    implementation 'com.google.firebase:firebase-ml-vision:16.0.0'
}
```
## Gradle project

```gradle
dependencies {
        classpath 'com.android.tools.build:gradle:3.1.2'
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
        classpath 'com.google.gms:google-services:4.0.0'
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
```
## File path

```xml
<?xml version="1.0" encoding="utf-8"?>
<paths xmlns:android="http://schemas.android.com/apk/res/android">
    <external-path
        name="my_images"
        path="Android/data/com.example.adrianeguez.mlkit/files/Pictures" />
</paths>
```

## Manifest

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.adrianeguez.mlkit">

    <uses-permission android:name="android.permission.CAMERA" android:required="true"/>

    <uses-permission android:name="android.permission.RECORD_AUDIO" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />

    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-feature android:name="android.hardware.camera" />
    <uses-feature android:name="android.hardware.location.gps" />


    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <meta-data
            android:name="com.google.firebase.ml.vision.DEPENDENCIES"
            android:value="barcode" />
        <provider
            android:name="GenericFileProvider"
            android:authorities="com.example.adrianeguez.mlkit.fileprovider"
            android:exported="false"
            android:grantUriPermissions="true">
            <meta-data
                android:name="android.support.FILE_PROVIDER_PATHS"
                android:resource="@xml/file_paths"></meta-data>
        </provider>
        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>
```


