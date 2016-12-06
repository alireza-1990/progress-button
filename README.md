# progress-button
This is a Android Button with a progress bar over it. This is useful when you do something that takes a while like networking, etc.

<img src="/screenshots/normal.png" width="300px">

<img src="/screenshots/inprogress.png" width="300px">

##Usage
Import it via gradle
```gradle
  compile 'com.alirezaahmadi:progress-button:1.1.1'
```

You can add this to view like this
```xml
<com.alirezaahmadi.progressbutton.ProgressButtonComponent
    android:id="@+id/testBtn"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:buttonText="test name"
    app:buttonTextSize="32dp"
    app:buttonTextColor="@color/text_color"
    app:progressColor="#ffffff"
    app:progressWidth="3dp"
    android:background="@drawable/btn_background"
    android:padding="16dp"
    />
       
```
In java code you can set view state to inProgress whenever you want like this
```java
@Override
public void onClick(View view) {
    if(view.getId() == R.id.testBtn){
        progressButtonComponent.setInProgress(true);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressButtonComponent.setInProgress(false);
            }
        }, 3000);
    }
}
```

##Caution
This library is created by extending RelativeLayout and not Button and may not provide all the functionalities a normal Button has so using codes like this will cause exception.
```java
(Button) findViewbyId(R.id.my-progress-button);
```

##Thanks
Special thanks to https://github.com/pnikosis/materialish-progress as i used this for progress bar in this library.





