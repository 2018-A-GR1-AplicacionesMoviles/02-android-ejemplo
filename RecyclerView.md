# Recycler view

## ActividadPrincipal

```kotlin
class ActividadPrincipal : AppCompatActivity() {
    var personas = ArrayList<Persona>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividad_principal)
        crearDatosPrueba()


        val layoutManager = LinearLayoutManager(this)
        personas.addAll(crearDatosPrueba())
        val adaptador = PersonasAdaptador(personas)
        recycler_view.layoutManager = layoutManager
        recycler_view.itemAnimator = DefaultItemAnimator()
        recycler_view.adapter = adaptador
        personas.addAll(crearDatosPrueba())
        adaptador.notifyDataSetChanged()
        Log.i("vista-principal", "Cambio personas ${personas.size}")
    }

    fun crearDatosPrueba(): ArrayList<Persona> {
        var personas = ArrayList<Persona>()
        personas.add(Persona("Vicente", "Eguez", "1718137159"))
        personas.add(Persona("Adrian", "Sarzosa", "1718138183"))
        personas.add(Persona("Wendy", "Eguez", "1718154323"))
        return personas
    }
}

class Persona(var nombre: String, var apellido: String, var cedula: String) {}

class PersonasAdaptador(private val listaPersonas: List<Persona>) : RecyclerView.Adapter<PersonasAdaptador.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nombre: TextView
        var apellido: TextView
        var cedula: TextView

        init {
            nombre = view.findViewById(R.id.txtv_nombre) as TextView
            apellido = view.findViewById(R.id.txtv_apellido) as TextView
            cedula = view.findViewById(R.id.txtv_cedula) as TextView
            val left = apellido.paddingLeft
            val top = apellido.paddingTop
            Log.i("vista-principal", "Hacia la izquierda es $left y hacia arriba es $top")

            val layout = view.findViewById(R.id.relative_layout) as RelativeLayout
            layout.setOnClickListener({ v ->
                val nombreActual = v.findViewById(R.id.txtv_nombre) as TextView

                Log.i("vista-principal", "El nombre actual es: ${nombreActual.text}")
                nombreActual.text = "Otro texto"

            })
            cedula.setOnClickListener { v ->
                val cedulaActual = v.findViewById(R.id.txtv_cedula) as TextView
                val toast = Toast.makeText(v.context, "Hola ${cedulaActual.text}", Toast.LENGTH_LONG)
                toast.show()
                val intent = Intent(v.context, ActividadLayouts::class.java)
                startActivity(v.context, intent, null)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.lista_fila_usuario, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val persona = listaPersonas[position]
        holder.nombre.setText(persona.nombre)
        holder.apellido.setText(persona.apellido)
        holder.cedula.setText(persona.cedula)
    }

    override fun getItemCount(): Int {
        return listaPersonas.size
    }

}

```


## activity_principal

```xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".ActividadPrincipal">

    <TextView
        android:id="@+id/txtv_principal"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hello World!"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.186" />

    <Button
        android:id="@+id/boton_principal"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="28dp"
        android:text="@string/boton_principal"
        app:layout_constraintTop_toTopOf="parent"
        tools:layout_editor_absoluteX="0dp" />

    <android.support.v7.widget.RecyclerView
        android:id="@+id/recycler_view"
        android:layout_width="match_parent"
        android:layout_height="142dp"
        android:layout_marginTop="40dp"
        android:scrollbars="vertical"
        app:layout_constraintTop_toBottomOf="@+id/txtv_principal"
        tools:layout_editor_absoluteX="0dp" />


</android.support.constraint.ConstraintLayout>
```


## lista_fila_usuario.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/relative_layout"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:background="?android:attr/selectableItemBackground"
    android:clickable="true"
    android:focusable="true"
    android:orientation="vertical"
    android:paddingBottom="@dimen/row_padding_vertical"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/row_padding_vertical">

    <TextView
        android:id="@+id/txtv_nombre"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_alignParentTop="true"
        android:textStyle="bold" />

    <TextView
        android:id="@+id/txtv_apellido"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_below="@id/txtv_nombre" />

    <TextView
        android:id="@+id/txtv_cedula"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentEnd="true"
        android:layout_alignParentRight="true" />

</RelativeLayout>
```
