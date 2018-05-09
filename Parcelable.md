# Parcelable

## Uso

Parcelable es una forma de transformar los objetos para que puedan ser serializados y ser mandados a travez de intents o cuando el dispositivo es destruido y creado de nuevo por cambios de hardware por ejemplo.

### Usuario

```kotlin
class Usuario(val nombre: String,
              val edad: Int,
              val fechaNacimiento: Date?,
              val casado: Boolean) : Parcelable {


    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readInt(),
            parcel.readDate(),
            parcel.readByte() != 0.toByte()) {
    }

    override fun describeContents(): Int {
        return 0
    }


    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(nombre)
        dest?.writeInt(edad)
        dest?.writeDate(fechaNacimiento)
        dest?.writeByte((if (casado) 1 else 0).toByte())
    }

    companion object CREATOR : Parcelable.Creator<Usuario> {
        override fun createFromParcel(parcel: Parcel): Usuario {
            return Usuario(parcel)
        }

        override fun newArray(size: Int): Array<Usuario?> {
            return arrayOfNulls(size)
        }
    }
}

fun Parcel.writeDate(date: Date?) {
    writeLong(date?.time ?: -1)
}

fun Parcel.readDate(): Date? {
    val long = readLong()
    return if (long != -1L) Date(long) else null
}
```

### Activity

```kotlin
var adrian: Usuario? = Usuario("Adrian", 1, Date(), true)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parcelable_main)
        val usuarioParcelable: Usuario? = savedInstanceState?.getParcelable<Usuario?>("usuarioActual")
        val existeUsuario = usuarioParcelable != null
        if (existeUsuario) {
            adrian = usuarioParcelable
        } else {
            adrian = Usuario("Vicente", 2, Date(), true)
        }
        val intent = Intent(this, Otra::class.java)
        intent.putExtra("usuario", adrian)
        startActivity(intent)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.run {
            putParcelable("usuarioActual", adrian)
        }
        super.onSaveInstanceState(outState)
    }
```
