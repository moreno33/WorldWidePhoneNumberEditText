package com.emarkall.worldwidephonenumberedittext;

import android.content.Context;
import android.content.res.Resources;

import java.io.Serializable;
import java.util.Locale;

/**
 * This class is a conceptual representation of a country
 * @author Moreno Jn Baptiste
 */
public class Country implements Serializable {

    /**
     * This is the ISO code of the country, generally it's a 2 characters words
     * that reprensents the name of the country. For example, for Haiti we have "ht", for
     * the United States we have "us", for Canada we have "ca".
     */
    private final String mIsoCode;

    /**
     * This is the full name of the country.
     */
    private final String mName;

    /**
     * This the area code of phone number in this country. That means every single phone number
     * should start by this code. There is not a general standard, every country has its own.
     */
    private final int mDialCode;

    public Country(String code, String name, int dialCode) {
        mIsoCode = code;
        mName = name;
        mDialCode = dialCode;
    }

    public String getmIsoCode() {
        return mIsoCode;
    }

    public String getName() {
        return mName;
    }

    public int getDialCode() {
        return mDialCode;
    }

    /**
     * This method allows to show the comple name of the country
     * @return
     */
    public String getDisplayName() {
        return new Locale(Locale.getDefault().getLanguage(), mIsoCode).getDisplayCountry(Locale.getDefault());
    }

    /**
     * This method accept the context and return the flag id of the flag resource.
     * @param context
     * @return
     */
    public int getResId(Context context) {
        String name = String.format("flag_%s",mIsoCode.toLowerCase());
        final Resources resources = context.getResources();
        return resources.getIdentifier(name, "mipmap", context.getPackageName());
    }
}
