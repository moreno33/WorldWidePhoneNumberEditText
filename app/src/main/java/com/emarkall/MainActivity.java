package com.emarkall;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.emarkall.worldwidephonenumberedittext.Country;
import com.emarkall.worldwidephonenumberedittext.CountryListActivity;
import com.emarkall.worldwidephonenumberedittext.WorldWidePhoneNumberEditText;


public class MainActivity extends AppCompatActivity {

    private WorldWidePhoneNumberEditText edx1, edx2, edx3;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edx1= findViewById(R.id.edx1);
        edx1.switchCountry("us");
        edx2= findViewById(R.id.edx2);
        edx2.setDefaultCountry("ht");
        edx3= findViewById(R.id.edx3);

        button= findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(! edx1.isValid()){
                    edx1.setError("Error");
                }
                if(! edx2.isValid()){
                    edx2.setError("Error");
                }
                if(! edx3.isValid()){
                    edx3.setError("Error");
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== RESULT_OK){
            if(requestCode== WorldWidePhoneNumberEditText.REQUEST_CODE){
                Country country= (Country) data.getSerializableExtra(CountryListActivity.COUNTRY_EXTRA);
                edx1.switchCountry(country.getmIsoCode());
                edx2.switchCountry(country.getmIsoCode());
                edx3.switchCountry(country.getmIsoCode());
            }
        }
    }
}
