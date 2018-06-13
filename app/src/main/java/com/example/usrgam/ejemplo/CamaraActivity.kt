package com.example.usrgam.ejemplo

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import kotlinx.android.synthetic.main.activity_camara.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class CamaraActivity : AppCompatActivity() {
    var directorioActualImagen = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camara)

        boton_tomar_foto.setOnClickListener { view ->
            tomarFoto()
        }
    }

    private fun tomarFoto() {
        val archivoImagen = crearArchivo(
                "JPEG_",
                Environment.DIRECTORY_PICTURES,
                ".jpg")
        directorioActualImagen = archivoImagen.absolutePath
        enviarIntentFoto(archivoImagen)
    }

    private fun crearArchivo(prefijo: String,
                             directorio: String,
                             extension: String): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss")
                .format(Date())

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


        val photoURI: Uri = FileProvider
                .getUriForFile(
                        this,
                        "com.example.usrgam.ejemplo.fileprovider",
                        archivo)

        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);

        if (takePictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takePictureIntent, TOMAR_FOTO_REQUEST);
        }

    }

    companion object {
        val TOMAR_FOTO_REQUEST = 1
    }


}


class GenericFileProvider : FileProvider() {

}





