# WorldWidePhoneNumberEditText
### Motivation 
I was developing an Android application for a client that requires the user to provide its phone number to which it will send an OTP (one time password) to login. At the first time I check on github to see if I can find a library that allows me to accept phone number from the World. I found many, but any of them didn’t satisfy my requirements.
I understand this may be the situation of many other developers like me, for this reason I create this highly customizable library that allows you to accept and verify phone number form the entire world.

### Quick Demo
<img src="https://github.com/moreno33/WorldWidePhoneNumberEditText/blob/master/worldwidephonenumberedittext/src/main/res/drawable/Screenshot_20190811-153952.png" alt="" style="width=300px; margin-right=30px"/><img src="https://github.com/moreno33/WorldWidePhoneNumberEditText/blob/master/worldwidephonenumberedittext/src/main/res/drawable/Screenshot_20190811-154012.png" alt="" style="width=300px; margin-right=30px" /><img src="https://github.com/moreno33/WorldWidePhoneNumberEditText/blob/master/worldwidephonenumberedittext/src/main/res/drawable/Screenshot_20190811-154030.png" alt="" style="width=300px; margin-right=30px" /><img src="https://github.com/moreno33/WorldWidePhoneNumberEditText/blob/master/worldwidephonenumberedittext/src/main/res/drawable/Screenshot_20190811-154036.png" alt="" style="width=300px; margin-right=30px" />

### Add dependencies
```gradle
allprojects {
  repositories {
    ...
    maven {
      url 'https://jitpack.io' 
    }
  }
}

dependencies {
  implementation 'com.github.moreno33:WorldWidePhoneNumberEditText:1.0'
}
```

## Usage
### Without any customization
#### Add this to your layout file
```xml
<com.emarkall.worldwidephonenumberedittext.WorldWidePhoneNumberEditText
        android:id="@+id/edx1"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginBottom="20dp"/>
```
#### Add some customization
```xml
<com.emarkall.worldwidephonenumberedittext.WorldWidePhoneNumberEditText
        android:id="@+id/edx3"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="@drawable/drw_background"
        app:flag_form="round_flag"
        app:textSize="20sp"
        app:textColor="#333"
        app:textStyle="bold|italic"
        app:hintColor="#C2C2C2"
        android:layout_marginBottom="40dp"
        app:arrow_drawable="@drawable/ic_arrow_drop_down_24dp"/>
```



