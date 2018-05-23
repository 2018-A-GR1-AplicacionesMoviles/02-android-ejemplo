# Dialogos

## Alert dialog

### Confirmacion

```kotlin
val builder = AlertDialog.Builder(this)
            builder.setMessage("Hola")
                    .setPositiveButton(
                            "Si", DialogInterface.OnClickListener { dialog, which ->
                        Log.i("a", "si")
                    }
                    )
                    .setNegativeButton(
                            "No", null)
            val dialogo = builder.create()
            dialogo.show()
```

### Single choice

```kotlin
val builder = AlertDialog.Builder(this)
            val listaItems = resources.getStringArray(R.array.listaitems)
            builder.setTitle("Hola")
                    .setSingleChoiceItems(arrayOf("1","2","3"),-1){dialog, which ->
                    }
                    .setNeutralButton("Cancel"){dialog, which ->
                        dialog.cancel()
                    }

            val dialogo = builder.create()
            dialogo.show()
```
