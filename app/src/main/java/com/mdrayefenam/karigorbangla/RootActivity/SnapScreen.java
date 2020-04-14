package com.mdrayefenam.karigorbangla.RootActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Criteria;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.mdrayefenam.karigorbangla.R;
import com.mdrayefenam.karigorbangla.ServiceProvider.Activity.ServiceProbiderCatagorySelect;
import com.mdrayefenam.karigorbangla.ServiceProvider.Activity.ServiceProviderHome;
import com.mdrayefenam.karigorbangla.ServiceTaker.Activity.MainActivity;
import com.mdrayefenam.karigorbangla.SessionClass.SessionClass;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class SnapScreen extends AppCompatActivity {

    private static final String SHARED_PREF_NAME = "simplifiedcodingsharedpref";
    private static final String KEY_USER_TYPE = "keyusertype";

    SessionClass sessionClass;
    String userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.snap_screen);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        userType = sharedPreferences.getString(KEY_USER_TYPE, "");

        sessionClass = SessionClass.getInstance(this);

        if (!haveNetwork()) {
            showDialog();
        }else if(!isGpsEnabled()){
            showDialogOne();
        }
        else {

            if (sessionClass.isLoggedIn()) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (userType.equals( "User" ))
                        {
                            Intent intent = new Intent(SnapScreen.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }else {
                            Intent intent = new Intent( SnapScreen.this, ServiceProviderHome.class );
                            startActivity( intent );
                            finish();
                        }




                    }
                }, 2000);
            } else {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent intent = new Intent(SnapScreen.this, LoginActivity.class);
                        startActivity(intent);
                        finish();


                    }
                }, 2000);
            }

        }


    }


    public boolean haveNetwork() {
        boolean have_WIFI=false;
        boolean have_Internet=false;
        ConnectivityManager connectivityManager=(ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfos=connectivityManager.getAllNetworkInfo();

        for (NetworkInfo info : networkInfos) {
            if (info.getTypeName().equalsIgnoreCase("WIFI")) {
                if (info.isConnected())
                    have_WIFI=true;
            }
            if (info.getTypeName().equalsIgnoreCase("MOBILE")) {
                if (info.isConnected())
                    have_Internet=true;

            }
        }

        return have_Internet || have_WIFI;
    }



    private boolean isGpsEnabled()
    {
        LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
        return service.isProviderEnabled(LocationManager.GPS_PROVIDER)&&service.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }



    void showDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this, R.style.CustomAlertDialog);
        LayoutInflater inflater = LayoutInflater.from(this);
        final View viewLayout = inflater.inflate(R.layout.no_internet_alart_dialog , null);
        dialog.setView(viewLayout);
        Button ok = (Button) viewLayout.findViewById(R.id.logok);
        final AlertDialog  alertDialog=dialog.create();
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                finish();
            }
        });
        alertDialog.show();
    }


    void showDialogOne() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this, R.style.CustomAlertDialog);
        LayoutInflater inflater = LayoutInflater.from(this);
        final View viewLayout = inflater.inflate(R.layout.no_location_alart_dialog , null);
        dialog.setView(viewLayout);
        Button ok = (Button) viewLayout.findViewById(R.id.logok);
        final AlertDialog  alertDialog=dialog.create();
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                finish();
            }
        });
        alertDialog.show();
    }





}
