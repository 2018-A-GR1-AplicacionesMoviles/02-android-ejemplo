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
