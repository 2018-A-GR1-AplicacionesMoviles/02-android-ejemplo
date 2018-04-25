# Spinner
Para popular un spinner se usa el siguiente codigo:

```kotlin
val items = arrayOf("A","B","C")
val adaterSpinnerItems = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items)
adaterSpinnerItems.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
spinner_id.adapter = adaterSpinnerItems

spinner_id.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
        override fun onNothingSelected(parent: AdapterView<*>?) {

        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        
        }

    }
 
```