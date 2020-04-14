package com.mdrayefenam.karigorbangla.SessionClass;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.mdrayefenam.karigorbangla.RootActivity.LoginActivity;
import com.mdrayefenam.karigorbangla.Model.SessionData;
import com.mdrayefenam.karigorbangla.ServiceTaker.Model.LocationClass;

public class SessionClass {
    private static final String SHARED_PREF_NAME = "simplifiedcodingsharedpref";
    private static final String KEY_ID = "keyid";

    private static final String KEY_NAME = "keyname";
    private static final String KEY_EMAIL = "keyemail";
    private static final String KEY_MOBILE = "keymobile";
    private static final String KEY_ADDRESS = "keyaddress";
    private static final String KEY_GENDER = "keygender";
    private static final String KEY_USER_TYPE = "keyusertype";
    private static final String KEY_PROFILE_IMAGE = "keyeprofileimage";

    private static final String KEY_CATEGORY = "keycategory";
    private static final String KEY_RATE = "keyrate";

    private static final String KEY_TOKEN = "keytoken";

    private static SessionClass mInstance;
    private static Context mCtx;

    public SessionClass(Context context) {
        mCtx = context;
    }

    public static synchronized SessionClass getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SessionClass(context);
        }
        return mInstance;
    }

    //method to let the user login
    //this method will store the user data in shared preferences
    public void userLogin(SessionData sessionData) {

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_ID, sessionData.getId());

        editor.putString( KEY_NAME, sessionData.getName());
        editor.putString( KEY_EMAIL, sessionData.getEmail());
        editor.putString( KEY_ADDRESS, sessionData.getAddress());
        editor.putString( KEY_MOBILE, sessionData.getMobile());
        editor.putString( KEY_GENDER, sessionData.getGender());
        editor.putString( KEY_USER_TYPE, sessionData.getUser_type());
        editor.putString( KEY_PROFILE_IMAGE,sessionData.getUser_profile_photo() );

        editor.putString( KEY_CATEGORY,sessionData.getCategory() );
        editor.putString( KEY_RATE,sessionData.getHourly_rate() );

        editor.putString(KEY_TOKEN, sessionData.getToken());
        editor.apply();
    }


    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_TOKEN, null) != null;
    }

    //this method will give the logged in user
    public SessionData getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new SessionData(
                sharedPreferences.getString(KEY_ID, null)
        );
    }



    //this method will logout the user
    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
//        mCtx.startActivity(new Intent(mCtx, Login.class));

    }


}
