package com.mdrayefenam.karigorbangla.ServiceTaker.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.mdrayefenam.karigorbangla.Model.SessionData;
import com.mdrayefenam.karigorbangla.Network.RetrofitClient;
import com.mdrayefenam.karigorbangla.R;
import com.mdrayefenam.karigorbangla.Responce.SignUpModel.SinUpResponce;
import com.mdrayefenam.karigorbangla.RootActivity.SingUp;
import com.mdrayefenam.karigorbangla.ServiceProvider.Activity.ServiceProbiderCatagorySelect;
import com.mdrayefenam.karigorbangla.ServiceTaker.Fragment.All;
import com.mdrayefenam.karigorbangla.ServiceTaker.Fragment.Home;
import com.mdrayefenam.karigorbangla.ServiceTaker.Model.LocationSession;
import com.mdrayefenam.karigorbangla.ServiceTaker.ServiceTakerInterestModel.ServiceTakerInterestResponce;
import com.mdrayefenam.karigorbangla.SessionClass.SessionClass;

import java.util.Calendar;

public class ServiceProviderProfileInServiceTaker extends AppCompatActivity {

    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;



    private static final String LOCATION_PREF_NAME = "LocationSession";
    private static final String KEY_LATITUDE = "keylatitude";
    private static final String KEY_LONGITUDE = "keylongitude";


    EditText hour,minute,service_area;
    Spinner day_spiner;

    String  Hour,Minute,SpinerTime,MainTime,serviceArea;

    String TAG = "ServiceProviderProfileInServiceTaker" ;
    private static final String SHARED_PREF_NAME = "simplifiedcodingsharedpref";
    private static final String KEY_ID = "keyid";
    private static final String KEY_NAME = "keyname";
    private static final String KEY_EMAIL = "keyemail";
    private static final String KEY_MOBILE = "keymobile";
    private static final String KEY_ADDRESS = "keyaddress";
    private static final String KEY_GENDER = "keygender";
    private static final String KEY_USER_TYPE = "keyusertype";
    private static final String KEY_PROFILE_IMAGE = "keyeprofileimage";
    private static final String KEY_TOKEN = "keytoken";

    DatePickerDialog.OnDateSetListener mDateSetListener;

    String userName,userEmail,userAddress,userGender,imageString,token,userProfileImage,userMobile,userType,userID,Latitude,Longitude;


    TextView provider_name,provider_address,provider_distance,provider_gender,rent_date_piker;
    Button bottonUpdate;
    ImageButton call,back;

    LinearLayout button_layout;
    ProgressBar progress_bar;

    String providerName,providerAddress,providerDistance,providerGender,number,providerId,serviceDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.service_provider_profile_in_service_taker );

        provider_name = findViewById( R.id.provider_name );
        provider_address = findViewById( R.id.provider_address );
        provider_distance = findViewById( R.id.provider_distance );
        provider_gender = findViewById( R.id.provider_gender );

        service_area = findViewById( R.id.service_area );

        back = findViewById( R.id.back );
        call = findViewById( R.id.call );

        rent_date_piker = findViewById( R.id.rent_date_piker );

        bottonUpdate = findViewById( R.id.bottonUpdate );

        hour = findViewById( R.id.hour );
        minute = findViewById( R.id.minute );
        day_spiner = findViewById( R.id.day_spiner );



        button_layout = findViewById( R.id.button_layout );
        button_layout.setVisibility( View.VISIBLE );

        progress_bar = findViewById( R.id.progress_bar );
        progress_bar.setVisibility( View.GONE );

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        userID = sharedPreferences.getString(KEY_ID, "");
        token = sharedPreferences.getString(KEY_TOKEN, "");
        userName = sharedPreferences.getString(KEY_NAME, "");
        userEmail = sharedPreferences.getString(KEY_EMAIL, "");
        userMobile = sharedPreferences.getString(KEY_MOBILE, "");
        userAddress = sharedPreferences.getString(KEY_ADDRESS, "");
        userProfileImage = sharedPreferences.getString(KEY_PROFILE_IMAGE, "");
        userGender = sharedPreferences.getString(KEY_GENDER, "");
        userType = sharedPreferences.getString(KEY_USER_TYPE, "");

        SharedPreferences locationSession = getSharedPreferences( LOCATION_PREF_NAME,Context.MODE_PRIVATE );
        Latitude = locationSession.getString(KEY_LATITUDE, "");
        Longitude = locationSession.getString( KEY_LONGITUDE,"" );

        Log.e( TAG, "Location: "+"\n"+Latitude +"\n"+Longitude);




        providerName = getIntent().getStringExtra( "provider_name" );
        providerAddress = getIntent().getStringExtra( "provider_address" );
        providerDistance = getIntent().getStringExtra( "provider_distance" );
        providerGender  = getIntent().getStringExtra( "provider_gender" );
        number = getIntent().getStringExtra( "provider_mobile" );
        providerId = getIntent().getStringExtra( "provider_id" );



        provider_name.setText( "Provider Name : "+providerName);
        provider_address.setText( "Provider Address : " +providerAddress);
        provider_distance.setText( "Provider Distance : " +providerDistance);
        provider_gender.setText( "Gender : " +providerGender);

        ArrayAdapter <String> spinerAdapter = new ArrayAdapter<String>(ServiceProviderProfileInServiceTaker.this,android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.time));
        spinerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        day_spiner.setAdapter(spinerAdapter);
        day_spiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinerTime = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        rent_date_piker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mCalendar = Calendar.getInstance();
                int day = mCalendar.get(Calendar.DAY_OF_MONTH);
                int month = mCalendar.get(Calendar.MONTH);
                int year = mCalendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        ServiceProviderProfileInServiceTaker.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        mDateSetListener,
                        day,month,year);

                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable( Color.TRANSPARENT));
                datePickerDialog.show();

            }
        });


        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                serviceDate = dayOfMonth+"/"+month+"/"+year;
                rent_date_piker.setText(serviceDate);

            }
        };






        call.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( Intent.ACTION_DIAL );
                intent.setData( Uri.parse( "tel:"+number ) );
                startActivity(intent);
                finish();
            }
        } );

        back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        } );

        bottonUpdate.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Hour = hour.getText().toString();
                Minute = minute.getText().toString();

                serviceArea = service_area.getText().toString();

                Log.e( TAG, "Time_AA"+ Hour+":"+Minute+" "+SpinerTime );
                MainTime = Hour+":"+Minute+" "+SpinerTime;
                Log.e( TAG, "Time: "+MainTime  );

                button_layout.setVisibility( View.GONE );
                progress_bar.setVisibility( View.VISIBLE );


                Call <ServiceTakerInterestResponce> call = RetrofitClient.getInstance(token).getApiInterface().serviceTakerInterestResponce( providerId,providerName,number,userID,userName,userEmail,userMobile,userAddress,Longitude,Latitude,userProfileImage,serviceDate,MainTime,serviceArea);
                call.enqueue( new Callback <ServiceTakerInterestResponce>() {
                    @Override
                    public void onResponse(Call <ServiceTakerInterestResponce> call, Response <ServiceTakerInterestResponce> response) {
                        if (response.isSuccessful()) {
                            Log.d(TAG, "onResponse: " + response.code());
                            ServiceTakerInterestResponce serviceTakerInterestResponce = response.body();
                            if (serviceTakerInterestResponce != null && serviceTakerInterestResponce.getStatus() == 1) {

                                button_layout.setVisibility( View.VISIBLE );
                                progress_bar.setVisibility( View.GONE );

                               Intent intent = new Intent( ServiceProviderProfileInServiceTaker.this,MainActivity.class );
                               startActivity( intent );
                               finish();


//                                Toast.makeText(ServiceProviderProfileInServiceTaker.this, serviceTakerInterestResponce.getStatus(), Toast.LENGTH_LONG).show();
                            } else {
//                                Toast.makeText(ServiceProviderProfileInServiceTaker.this, serviceTakerInterestResponce.getStatus(), Toast.LENGTH_LONG).show();
                                button_layout.setVisibility( View.VISIBLE );
                                progress_bar.setVisibility( View.GONE );
                            }
                        }
                        else {
                            Log.d(TAG, "onResponse: " + response.code());
                            button_layout.setVisibility( View.VISIBLE );
                            progress_bar.setVisibility( View.GONE );
                        }
                    }

                    @Override
                    public void onFailure(Call <ServiceTakerInterestResponce> call, Throwable t) {
                        Log.e( TAG, "onFailure: "+t.getLocalizedMessage() );
                        Toast.makeText(ServiceProviderProfileInServiceTaker.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        button_layout.setVisibility( View.VISIBLE );
                        progress_bar.setVisibility( View.GONE );
                    }
                } );
            }
        } );







    }



}
