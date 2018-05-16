# Fragmentos

## Main Activity

```kotlin
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragmentOne = BlankFragment()
        fragmentTransaction.add(R.id.constraint_layout, fragmentOne)
        fragmentTransaction.commit()
    
```

```kotlin
    fun cambiarFragmento(fragmentoNuevo: android.support.v4.app.Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.constraint_layout, fragmentoNuevo)
        fragmentTransaction.commit()
    }
```

## Fragmento

```kotlin
override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank_fragment3, container, false)
    }
```
