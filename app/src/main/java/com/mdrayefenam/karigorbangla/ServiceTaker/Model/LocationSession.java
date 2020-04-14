package com.mdrayefenam.karigorbangla.ServiceTaker.Model;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class LocationSession {

    private static final String LOCATION_PREF_NAME = "LocationSession";
    private static final String KEY_LATITUDE = "keylatitude";
    private static final String KEY_LONGITUDE = "keylongitude";


    private static LocationSession mInstance;
    private static Context mCtx;

    private LocationSession(Context context) {
        mCtx = context;
    }

    public static synchronized LocationSession getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new LocationSession(context);
        }
        return mInstance;
    }

    //method to let the user login
    //this method will store the user data in shared preferences
    public void putAddress(LocationClass locationClass) {

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(LOCATION_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_LONGITUDE, locationClass.getLongitude());
        editor.putString(KEY_LATITUDE, locationClass.getLatitude());
        editor.apply();
    }

//    //this method will checker whether user is already logged in or not
//    public boolean isLoggedIn() {
//        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        return sharedPreferences.getString(KEY_EMAIL, null) != null;
//    }
//
//    //this method will give the logged in user
//    public SessionEmail getUser() {
//        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        return new SessionEmail(
//                sharedPreferences.getString(KEY_EMAIL, null)
//        );
//    }
//
//    //this method will logout the user
//    public void logout() {
//        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.clear();
//        editor.apply();
//        mCtx.startActivity(new Intent(mCtx, LoginActivity.class));
//    }



}
