# Alerter

## Gradle app

```gradle
dependencies {
    implementation 'com.tapadoo.android:alerter:2.0.6'
}
```

## Activity
```
Alerter.create(this@DemoActivity)
       .setTitle("Alert Title")
       .setText("Alert text...")
       .show()
       
Alerter.create(this@DemoActivity)
       .setTitle("Alert Title")
       .setText("Alert text...")
       .setBackgroundColorRes(R.color.colorAccent) // or setBackgroundColorInt(Color.CYAN)
       .show()
```
