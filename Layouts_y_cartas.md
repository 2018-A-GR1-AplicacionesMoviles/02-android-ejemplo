# Layouts y carts

## LayoutActivity

```kotlin
class ActividadLayouts : AppCompatActivity() {
    var mThumbIds = arrayOf<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividad_layouts)
        mThumbIds = arrayOf<Int>(
                R.drawable.poli, R.drawable.poli,
                R.drawable.poli, R.drawable.poli,
                R.drawable.poli, R.drawable.poli,
                R.drawable.poli, R.drawable.poli,
                R.drawable.poli, R.drawable.poli,
                R.drawable.poli, R.drawable.poli,
                R.drawable.poli, R.drawable.poli,
                R.drawable.poli, R.drawable.poli,
                R.drawable.poli, R.drawable.poli,
                R.drawable.poli, R.drawable.poli)
        gridview.adapter = ImageAdapter(this)
        gridview.onItemClickListener =
                AdapterView.OnItemClickListener { parent, v, position, id ->
                    Toast.makeText(this, "$position", Toast.LENGTH_SHORT).show()
                }
    }

    class ImageAdapter(val mContext: ActividadLayouts) : BaseAdapter() {

        override fun getCount(): Int = mContext.mThumbIds.size

        override fun getItem(position: Int): Any? = null

        override fun getItemId(position: Int): Long = 0L

        // create a new ImageView for each item referenced by the Adapter
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val imageView: ImageView
            if (convertView == null) {
                // if it's not recycled, initialize some attributes
                imageView = ImageView(mContext)
                imageView.scaleType = ImageView.ScaleType.CENTER_CROP
                imageView.layoutParams = ViewGroup.LayoutParams(35, 35)
                imageView.setPadding(8, 8, 8, 8)
            } else {
                imageView = convertView as ImageView
            }

            imageView.setImageResource(mContext.mThumbIds[position])
            return imageView
        }
    }
}
```

## layout.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".ActividadLayouts">

    <ImageView
        android:id="@+id/imageView"
        android:layout_width="117dp"
        android:layout_height="85dp"
        android:layout_marginLeft="16dp"
        android:layout_marginStart="16dp"
        android:layout_marginTop="16dp"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:srcCompat="@drawable/poli" />

    <TextView
        android:id="@+id/textView"
        android:layout_width="wrap_content"
        android:layout_height="18dp"
        android:layout_marginTop="76dp"
        android:text="@string/nombre_epn"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toEndOf="@+id/imageView"
        app:layout_constraintTop_toTopOf="parent" />

    <android.support.constraint.Guideline
        android:id="@+id/guideline2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        app:layout_constraintGuide_begin="80dp" />

    <android.support.constraint.ConstraintLayout
        android:id="@+id/constraintLayout"
        android:layout_width="291dp"
        android:layout_height="128dp"
        android:layout_marginBottom="64dp"
        android:layout_marginEnd="8dp"
        android:layout_marginLeft="8dp"
        android:layout_marginRight="8dp"
        android:layout_marginStart="8dp"
        android:layout_marginTop="8dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.0"
        app:layout_constraintStart_toStartOf="@+id/guideline2"
        app:layout_constraintTop_toBottomOf="@+id/constraintLayout2">

        <android.support.v7.widget.CardView
            android:layout_width="match_parent"
            android:layout_height="116dp"
            android:layout_marginBottom="8dp"
            android:layout_marginEnd="8dp"
            android:layout_marginLeft="8dp"
            android:layout_marginRight="8dp"
            android:layout_marginStart="8dp"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintHorizontal_bias="0.348"
            app:layout_constraintStart_toEndOf="@+id/textView3"
            app:layout_constraintTop_toBottomOf="@+id/textView2"
            app:layout_constraintVertical_bias="0.514">

            <android.support.constraint.ConstraintLayout
                android:layout_width="match_parent"
                android:layout_height="match_parent">

                <ImageView
                    android:id="@+id/imageView3"
                    android:layout_width="35dp"
                    android:layout_height="26dp"
                    android:layout_marginBottom="8dp"
                    android:layout_marginLeft="16dp"
                    android:layout_marginStart="16dp"
                    android:layout_marginTop="8dp"
                    app:layout_constraintBottom_toBottomOf="parent"
                    app:layout_constraintStart_toStartOf="parent"
                    app:layout_constraintTop_toTopOf="parent"
                    app:srcCompat="@mipmap/ic_launcher_round" />

                <TextView
                    android:id="@+id/textView4"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_marginBottom="8dp"
                    android:layout_marginEnd="8dp"
                    android:layout_marginLeft="8dp"
                    android:layout_marginRight="8dp"
                    android:layout_marginStart="8dp"
                    android:layout_marginTop="8dp"
                    android:text="TextView"
                    app:layout_constraintBottom_toBottomOf="parent"
                    app:layout_constraintEnd_toEndOf="parent"
                    app:layout_constraintHorizontal_bias="0.0"
                    app:layout_constraintStart_toEndOf="@+id/imageView3"
                    app:layout_constraintTop_toTopOf="parent" />

                <LinearLayout
                    android:id="@+id/linearLayout"
                    android:layout_width="117dp"
                    android:layout_height="80dp"
                    android:layout_marginBottom="8dp"
                    android:layout_marginEnd="8dp"
                    android:layout_marginLeft="8dp"
                    android:layout_marginRight="8dp"
                    android:layout_marginStart="8dp"
                    android:layout_marginTop="8dp"
                    android:orientation="horizontal"
                    app:layout_constraintBottom_toBottomOf="parent"
                    app:layout_constraintEnd_toEndOf="parent"
                    app:layout_constraintHorizontal_bias="0.68"
                    app:layout_constraintStart_toEndOf="@+id/textView4"
                    app:layout_constraintTop_toTopOf="parent">

                    <ImageView
                        android:id="@+id/imageView4"
                        android:layout_width="60dp"
                        android:layout_height="match_parent"
                        android:layout_weight="1"
                        app:srcCompat="@drawable/poli" />

                    <ImageView
                        android:id="@+id/imageView5"
                        android:layout_width="64dp"
                        android:layout_height="match_parent"
                        android:layout_weight="1"
                        app:srcCompat="@mipmap/ic_launcher" />

                    <ImageView
                        android:id="@+id/imageView6"
                        android:layout_width="65dp"
                        android:layout_height="match_parent"
                        android:layout_weight="1"
                        app:srcCompat="@drawable/ic_launcher_background" />
                </LinearLayout>
            </android.support.constraint.ConstraintLayout>
        </android.support.v7.widget.CardView>
    </android.support.constraint.ConstraintLayout>

    <android.support.constraint.ConstraintLayout
        android:id="@+id/constraintLayout2"
        android:layout_width="227dp"
        android:layout_height="73dp"
        android:layout_marginTop="16dp"
        app:layout_constraintTop_toBottomOf="@+id/imageView"
        tools:layout_editor_absoluteX="122dp">

        <android.support.v7.widget.CardView
            android:layout_width="211dp"
            android:layout_height="match_parent"
            android:layout_marginBottom="8dp"
            android:layout_marginTop="8dp"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            tools:layout_editor_absoluteX="8dp" >

            <GridView
                android:id="@+id/gridview"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:columnWidth="45dp"
                android:numColumns="auto_fit"
                android:verticalSpacing="10dp"
                android:horizontalSpacing="10dp"
                android:stretchMode="columnWidth"
                android:gravity="center"/>
        </android.support.v7.widget.CardView>
    </android.support.constraint.ConstraintLayout>

</android.support.constraint.ConstraintLayout>
```
