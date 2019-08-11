package com.emarkall.worldwidephonenumberedittext;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static com.emarkall.worldwidephonenumberedittext.CountryList.COUNTRIES;

/**
 * This is the activity that will show the list of the
 * country where the user is going to select a country
 * @author Moreno Jn Baptiste
 */
public class CountryListActivity extends AppCompatActivity {


    /**
     * The recycler view that will show the countries
     */
    private RecyclerView rclCountryList;

    /**
     * The edit text to filter the countries
     */
    private EditText edxSearch;

    /**
     * The adapter for the countries
     */
    private CountryAdapter countryAdapter;

    /**
     * The name of the extra that we are going
     * to use to return the country to the user
     * activity.
     */
    public static final  String COUNTRY_EXTRA= "com.woolib.COUNTRY_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_list);
        /*
        This allows me to have a white icon in the status bar
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = getWindow().getDecorView();
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        init();


        edxSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                countryAdapter.setCountries(filter(editable.toString(), COUNTRIES));
                countryAdapter.notifyDataSetChanged();
            }
        });
    }

    private void init() {
        rclCountryList= findViewById(R.id.rcl_country_list);
        edxSearch= findViewById(R.id.edx_search);

        /*
        We instantiate the adapter
         */
        countryAdapter= new CountryAdapter(this);

        //We give a layout manager to the adapter
        rclCountryList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rclCountryList.setAdapter(countryAdapter);
    }

    private List<Country> filter(String query, List<Country> countries){
        List<Country> returnedCountries= new ArrayList<>();

        for(Country country : countries){
            if(country.getDisplayName().toLowerCase().startsWith(query.toLowerCase())
                    || country.getmIsoCode().toLowerCase().startsWith(query.toLowerCase())){
                returnedCountries.add(country);
            }
        }
        return  returnedCountries;
    }
}
