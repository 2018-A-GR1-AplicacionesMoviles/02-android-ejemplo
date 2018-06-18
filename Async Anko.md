# Anko

## Async

```kotlin
doAsync {
            Log.i("async", "ASINCRONO")
            uiThread {
                toast("regrese")
                Log.i("async", "hola")
            }
        }
```
