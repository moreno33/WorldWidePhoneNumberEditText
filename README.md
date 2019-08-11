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

### Java code
```java
public class MainActivity extends AppCompatActivity {

    private WorldWidePhoneNumberEditText edx3;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        edx3= findViewById(R.id.edx3);
        //Provide the iso code to set a default country.
        edx3.setDefaultCountry("us");
      
        button= findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                *You can validate it by calling is valide method, and if it's not valid
                *you can add an error using setError method.
                */
                if(! edx3.isValid()){
                    edx3.setError("Error");
                }
            }
        });

    }

     /*
      * You need to override the onActiviResult method so you can
      * switch the country.
      */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== RESULT_OK){
            if(requestCode== WorldWidePhoneNumberEditText.REQUEST_CODE){
                Country country= (Country) data.getSerializableExtra(CountryListActivity.COUNTRY_EXTRA);
                edx1.switchCountry(country.getmIsoCode());
            }
        }
    }
}
```
## FAQ
- [x] Does the libary have internationalization for the name of the countries? Yes
- [x] Does the libary format the phone number on the fly? Yes
- [x] Does the library add the phone number hint for every country? Yes


