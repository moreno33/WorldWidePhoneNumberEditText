package com.emarkall.worldwidephonenumberedittext;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.makeramen.roundedimageview.RoundedImageView;
import com.vicmikhailau.maskededittext.MaskedEditText;

import java.util.List;
import java.util.Locale;


/**
 * This class is a composite view that allows the
 * user to pick a country and contraint the user
 * to provide the right phone number format for
 * this country.
 * @author Moreno Jn Baptiste
 */
public class WorldWidePhoneNumberEditText extends LinearLayout {

    /**
     * This is the context where the api is going to be used
     */
    private Context mContext;

    /**
     * To display the flag of picture of the country
     */
    private RoundedImageView imgCountryFlag;

    /**
     * To keep the country area code
     */
    private TextView txtAreaCode;

    /**
     * To hold the phone number
     */
    private MaskedEditText edxNumber;

    /**
     * default position
     */
    private int position= 0;

    /**
     * This is a library of google to manage phone number
     */
    private PhoneNumberUtil phoneNumberUtil= PhoneNumberUtil.getInstance();

    /**
     * This list of country.
     */
    private List<Country> countries= CountryList.COUNTRIES;

    /**
     * This is the requestCode to get the country data from the intent
     */
    public final static int REQUEST_CODE= 1000;

    /**
     * The current country
     */
    private Country country;

    /**
     * The size of the text.
     */
    private float textSize;

    /**
     * The color of the text.
     */
    private int textColor;

    /**
     * The hint color
     */
    private int hintColor;

    /**
     * Here we have the text style
     */
    private TextStyle textStyle;

    /**
     * To change the drawable in the arrow close to
     * the area code text view.
     */
    private Drawable arrowDrawable;

    public WorldWidePhoneNumberEditText(Context context) {
        super(context);
        mContext= context;
        init();
    }

    public WorldWidePhoneNumberEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext= context;
        setUpAttributes(attrs);
        init();

    }

    public WorldWidePhoneNumberEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext= context;
        setUpAttributes(attrs);
        init();

    }

    private void setUpAttributes(AttributeSet attrs) {
        TypedArray typedArray= mContext.getTheme().obtainStyledAttributes(attrs, R.styleable.WorldWidePhoneNumberEditText, 0, 0);
        try{
            //Color
            textColor= typedArray.getColor(R.styleable.WorldWidePhoneNumberEditText_textColor,
                    Color.parseColor("#929292"));

            hintColor= typedArray.getColor(R.styleable.WorldWidePhoneNumberEditText_hintColor,
                    Color.parseColor("#C2C2C2"));

            //Size
            textSize= typedArray.getDimensionPixelSize(R.styleable.WorldWidePhoneNumberEditText_textSize, 61);

            //Text style
            textStyle= TextStyle.values()[typedArray.getInt(R.styleable.WorldWidePhoneNumberEditText_textStyle, 0)];

            arrowDrawable= typedArray.getDrawable(R.styleable.WorldWidePhoneNumberEditText_arrow_drawable);

        }finally {
            typedArray.recycle();
        }
    }

    /**
     * Here we initialize the necessary view
     */
    private void init(){

        inflate(mContext, R.layout.phone_number_layout, this);

        imgCountryFlag= findViewById(R.id.img_country);
        txtAreaCode= findViewById(R.id.txt_code);
        edxNumber= findViewById(R.id.edx_number);

        //if user add a background for the view we need to take off background of the edittext
        if(getBackground() != null){
            edxNumber.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.drw_transparent_background));
        }

        setup();
        addAttributes();
    }

    /**
     * This method allows to parse the attributes to the view
     */
    private void addAttributes() {
        //Add the arrow drawable
        if(arrowDrawable != null){
            txtAreaCode.setCompoundDrawablesWithIntrinsicBounds(arrowDrawable, null, null, null);
        }
        //Add size to text
        edxNumber.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        //Add color to hint
        edxNumber.setHintTextColor(hintColor);
        //Add size to area code
        txtAreaCode.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        //add color to text
        edxNumber.setTextColor(textColor);
        //add color to area code
        txtAreaCode.setTextColor(textColor);
        //add text style
        if(textStyle== TextStyle.BOLD){
            edxNumber.setTypeface(null, Typeface.BOLD);
            txtAreaCode.setTypeface(null, Typeface.BOLD);
        }
        if(textStyle== TextStyle.ITALIC){
            edxNumber.setTypeface(null, Typeface.ITALIC);
            txtAreaCode.setTypeface(null, Typeface.ITALIC);
        }
        if(textStyle==TextStyle.BOLD_ITALIC){
            edxNumber.setTypeface(edxNumber.getTypeface(), Typeface.BOLD_ITALIC);
            txtAreaCode.setTypeface(txtAreaCode.getTypeface(), Typeface.BOLD_ITALIC);
        }
    }

    /**
     * Here we set them up with their default value
     */
    private void setup(){

        //To clear the edit text every time we change a country
        edxNumber.getText().clear();

        //By default the country is Afganistan
        country= countries.get(position);

        txtAreaCode.setText("+"+country.getDialCode());

        imgCountryFlag.setImageResource(country.getResId(mContext));

        /*
        Here we get a phone number example, we format it and
        we pass it has place holder in our editext.
         */
        Phonenumber.PhoneNumber phoneNumber= phoneNumberUtil.getExampleNumber(country.getmIsoCode().toUpperCase());

        final String formattedNumber = phoneNumberUtil.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.NATIONAL);

        edxNumber.setHint(formattedNumber);

        String mask= formattedNumber.replaceAll("[0-8]", "#");

        edxNumber.setMask(mask);

        imgCountryFlag.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity)mContext).startActivityForResult(new Intent(mContext, CountryListActivity.class), REQUEST_CODE);
            }
        });
    }

    /**
     * We keep this method private because the maxlength of a edittext should be generated
     * by the api
     * @param maxLength
     */
    private void setMaxLength(int maxLength){
        InputFilter[] fArray = new InputFilter[1];
        fArray[0] = new InputFilter.LengthFilter(maxLength);
        edxNumber.setFilters(fArray);
    }

    /**
     * This method allows us to switch country using the dialCode
     * @param dialCode
     */
    public void switchCountry(int dialCode){
        for(int i = 0; i< CountryList.COUNTRIES.size(); i++){
            if(countries.get(i).getDialCode()== dialCode){
                position= i;
                setup();
            }
        }
    }

    /**
     * This method allows us to switch country using the country
     * ISO code
     * @param code
     */
    public void switchCountry(String code){
        for(int i = 0; i< CountryList.COUNTRIES.size(); i++){
            if(countries.get(i).getmIsoCode().equalsIgnoreCase(code)){
                position= i;
                setup();
            }
        }
    }

    /**
     * This method allows us to set a phone number, an while we are setting
     * the phone the number if it's phone another country
     * it's going to switch to right country.
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber){

        String defaultRegion = country != null ? country.getmIsoCode().toUpperCase() : "";

        try {

            Phonenumber.PhoneNumber phone= phoneNumberUtil.parseAndKeepRawInput(phoneNumber, defaultRegion);
            switchCountry(phone.getCountryCode());

            edxNumber.setText(Long.toString(phone.getNationalNumber()));
            edxNumber.setSelection(edxNumber.length());
        } catch (NumberParseException e) {
            e.printStackTrace();
        }

    }

    public boolean isValid(){
        try {
            if(phoneNumberUtil.isValidNumber(phoneNumberUtil.parse(country.getDialCode()+edxNumber.getUnMaskedText(),
                    country.getmIsoCode().toUpperCase()))){
                return true;
            }

            return false;
        } catch (NumberParseException e) {
            return false;
        }
    }

    /**
     * This method allows to set a default country
     */
    public void setDefaultCountry(String isoCode){
        switchCountry(isoCode);
    }

    public void setDefaultCountry(int areaCode){
        switchCountry(areaCode);
    }

    /**
     * This method returned a clean and unmasked phone number.
     * @return
     */
    public String getText(){
        return country.getDialCode()+this.edxNumber.getUnMaskedText();
    }

    public String getMaskedText(){
        return country.getDialCode()+this.edxNumber.getText().toString();
    }

    public void setError(String errorMessage){
        this.edxNumber.setError(errorMessage);
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
        addAttributes();
    }

    public int getTextColor() {
        return textColor;
    }

    public String getRegion(){
        return country != null ? country.getmIsoCode().toUpperCase(): "";
    }

    public Integer getCountryCode(){
        return  country != null ? country.getDialCode(): 1;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
        addAttributes();
    }

    public float getTextSize() {
        return textSize;
    }

    public int getHintColor() {
        return hintColor;

    }

    public void setHintColor(int hintColor) {
        this.hintColor = hintColor;
        addAttributes();
    }

    public TextStyle getTextStyle() {
        return textStyle;
    }

    public void setTextStyle(TextStyle textStyle) {
        this.textStyle = textStyle;
        addAttributes();
    }

    public void setArrowDrawable(Drawable arrowDrawable) {
        this.arrowDrawable = arrowDrawable;
        addAttributes();
    }

    public Drawable getArrowDrawable() {
        return arrowDrawable;
    }
}
