# Imagenes intent

## Codigo 

```kotlin
class MainActivity : AppCompatActivity() {
    val REQUEST_IMAGE_CAPTURE = 1
    val REQUEST_TAKE_PHOTO = 2
    val REQUEST_IMAGE_TO_GALLERY = 3
    var mCurrentPhotoPath: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val file = File("/sdcard/Android/data/com.example.adrianeguez.camara/files/Pictures/JPEG_20180606_124732_4785118857597101135.jpg")
        val fileDest = File("/sdcard/DCIM/Camera/JPEG_20180606_124732_4785118857597101135.jpg")
        val uri = Uri.fromFile(file)


        val scanFileIntent = Intent(
                Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri)
        sendBroadcast(scanFileIntent)


        val myBitmap = BitmapFactory.decodeFile(file.getAbsolutePath())

        val input = FileInputStream(file)
        val output = FileOutputStream(fileDest)
        val buf = ByteArray(1024)
        var len = input.read(buf)
        while (len > 0) {
            output.write(buf, 0, len)
        }
        mImageView.setImageBitmap(myBitmap)


        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            dispatchTakePictureIntent()
        }
        button_image_view.setOnClickListener { v ->
            dispatchTakePictureToImageViewIntent()
        }
        button_gallery.setOnClickListener { v ->
            galleryAddPic()
        }
    }

    private fun dispatchTakePictureToImageViewIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"

        val image2 = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM + "/Camera"), imageFileName + ".jpg")
        Log.i("imagen", "${image2.absolutePath}")

        val uri = FileProvider.getUriForFile(this, "com.example.adrianeguez.camara.fileprovider", image2)

        Log.i("imagen", "uri $uri")

        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
        takePictureIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }
    }

    private fun dispatchTakePictureToSaveIntoGallery() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val imageFile = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "ejemplo" + SimpleDateFormat("yyyyMMdd_HHmmss").format(Date()) + ".jpg")
        val uri = Uri.fromFile(imageFile)
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_TO_GALLERY)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val extras = data.extras
            val imageBitmap = extras!!.get("data") as Bitmap
            mImageView.setImageBitmap(imageBitmap)
        }

        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == Activity.RESULT_OK) {
            galleryAddPic()
        }

        if (requestCode == REQUEST_IMAGE_TO_GALLERY && resultCode == Activity.RESULT_OK) {
        }
    }

    //
    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)

//        val storageDir: File = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        Log.i("imagen", "$storageDir")
        val image = File.createTempFile(
                imageFileName, /* prefix */
                ".jpg", /* suffix */
                storageDir      /* directory */
        )
        val image2 = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM + "/Camera"), imageFileName + ".jpg")
        Log.i("imagen", "${image2.absolutePath}")
        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath()
        galleryAddPic()
        return image
    }

    //
    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            var photoFile: File? = null;
            try {
                photoFile = createImageFile()
            } catch (ex: IOException) {
                Log.i("imagen", "Exep: $ex")

            }
            // Continue only if the File was successfully created
            Log.i("imagen", "photoFile: $photoFile")
            if (photoFile != null) {

                val photoURI: Uri = FileProvider.getUriForFile(this,
                        "com.example.adrianeguez.camara.fileprovider",
                        photoFile)
                Log.i("imagen", "photoURI: $photoURI")
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    private fun galleryAddPic() {
        val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
        val f = File(mCurrentPhotoPath)
        val contentUri = Uri.fromFile(f)
        mediaScanIntent.data = contentUri
        this.sendBroadcast(mediaScanIntent)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}

```
