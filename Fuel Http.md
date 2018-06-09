# Fuel http - libreria para request http

## Activity

```kotlin

"https://jsonplaceholder.typicode.com/posts"
                .httpGet()
                .responseString() { request, response, result ->
                    when (result) {
                        is Result.Failure -> {
                            val ex = result.getException()
                            Log.i("http", "$ex")
                        }
                        is Result.Success -> {
                            val data = result.get()

                            val result = Klaxon()
                                    .parseArray<Post>(data)

                            result!!.forEach { post: Post ->
                                Log.i("http", "${post.id}")
                                Log.i("http", "${post.body}")
                                Log.i("http", "${post.title}")
                                Log.i("http", "${post.userId}")

                            }

                        }
                    }
                }
                
```

## Gradle aplicacion

```gradle
dependencies {
    implementation 'com.github.kittinunf.fuel:fuel-android:1.13.0'
}

```
