package com.mdrayefenam.karigorbangla.ServiceProvider.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.mdrayefenam.karigorbangla.Network.RetrofitClient;
import com.mdrayefenam.karigorbangla.R;
import com.mdrayefenam.karigorbangla.ServiceProvider.DeletServiceProviderPendingJobOfferModel.ServiceProviderPendingJobOfferDeletResponce;
import com.mdrayefenam.karigorbangla.ServiceProvider.ServiceConfirmModel.ServiceConfirmResponce;
import com.mdrayefenam.karigorbangla.ServiceTaker.Activity.MainActivity;
import com.mdrayefenam.karigorbangla.ServiceTaker.Activity.ProviderRecyllerView;
import com.mdrayefenam.karigorbangla.ServiceTaker.Activity.ServiceProviderProfileInServiceTaker;
import com.mdrayefenam.karigorbangla.ServiceTaker.Activity.ServiceTakerLocation;
import com.mdrayefenam.karigorbangla.ServiceTaker.Model.LocationClass;
import com.mdrayefenam.karigorbangla.ServiceTaker.Model.LocationSession;
import com.mdrayefenam.karigorbangla.ServiceTaker.ServiceTakerInterestModel.ServiceTakerInterestResponce;

public class ServiceProviderJobConfirm extends FragmentActivity implements OnMapReadyCallback {

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

    String providerID,providerName,providerNumber,providerProfileImage,token;


    private GoogleMap mMap;

    private static final int LOCATION_REQUTEST=500;

    ImageView iv;
    TextView service_taker_name,service_taker_address,service_taker_email,service_Date,service_time,service_address;

    String id,serviceTakerName,ServiceTakerNumbre,serviceTakerAddress,serviceTakerEmail,ServiceTakerProfilePhoto,serviceTakerLatitude,serviceTakerLongitude,serviceTakerProfileImage,Image,serviceDate,serviceTime,serviceAddress,serviceTakerId;

    LinearLayout buttons;
    String TAG = "ServiceTakerLocation";

    Button delete,confirm;
    ProgressBar progress_bar;

    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;

    String Latitude,Longitude;
    int Id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_service_provider_job_confirm );

        iv = findViewById( R.id.iv );

        buttons = findViewById( R.id.buttons );

        delete = findViewById( R.id.delete );
        confirm = findViewById( R.id.confirm );

        service_taker_name = findViewById( R.id.service_taker_name );
        service_taker_address = findViewById( R.id.service_taker_address );
        service_taker_email = findViewById( R.id.service_taker_email );

        service_Date = findViewById( R.id.service_Date );
        service_time = findViewById( R.id.service_time );

        service_address = findViewById( R.id.service_address );

        progress_bar = findViewById( R.id.progress_bar );
        progress_bar.setVisibility( View.GONE );

        Image = getIntent().getStringExtra( "ServiceTakerProfilePhoto" );
        serviceTakerName = getIntent().getStringExtra( "ServiceTakerName" );
        serviceTakerAddress = getIntent().getStringExtra( "ServiceTakerEmail" );
        serviceTakerEmail = getIntent().getStringExtra( "ServiceTakerAddress" );
        ServiceTakerProfilePhoto  = getIntent().getStringExtra( "ServiceTakerProfilePhoto" );

        serviceTakerLatitude  = getIntent().getStringExtra( "ServiceTakerLocationLatitude" );
        serviceTakerLongitude  = getIntent().getStringExtra( "ServiceTakerLocationLongitude" );

        serviceTakerId = getIntent().getStringExtra( "ServiceTakerId" );
        ServiceTakerNumbre =  getIntent().getStringExtra( "ServiceTakerNumbre" );

        serviceDate = getIntent().getStringExtra( "ServiceDay" );
        serviceTime = getIntent().getStringExtra( "ServiceTime" );
        serviceAddress = getIntent().getStringExtra( "ServiceAddress" );



        Id = getIntent().getIntExtra("ServiceID", 0);

        Log.e( TAG, "Date&Time: "+Id);



        service_taker_name.setText( serviceTakerName );
        service_taker_address.setText( serviceTakerAddress );
        service_taker_email.setText( serviceTakerEmail );
        service_Date.setText("Service Date : "+serviceDate );
        service_time.setText( "Service Time : "+serviceTime );
        service_address.setText( "Service Address :"+serviceAddress );



        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient( this );

        featchLocation();

        serviceTakerProfileImage = "https://karigor.againwish.com/"+Image;
        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.profile_user_icon).error(R.drawable.profile_user_icon);
        Glide.with(this).load(serviceTakerProfileImage).apply(requestOptions).into(iv);



        delete.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                buttons.setVisibility( View.GONE );
                progress_bar.setVisibility( View.VISIBLE );

                int mStatus = 3;
                id = String.valueOf(Id);



                Call <ServiceProviderPendingJobOfferDeletResponce> call = RetrofitClient.getInstance().getApiInterface().serviceProviderPendingJobOfferDeletResponce(id,mStatus);
                call.enqueue(new Callback <ServiceProviderPendingJobOfferDeletResponce>() {
                    @Override
                    public void onResponse(Call<ServiceProviderPendingJobOfferDeletResponce> call, Response <ServiceProviderPendingJobOfferDeletResponce> response) {
                        if (response.isSuccessful()) {
                            Log.d(TAG, "onResponse: " + response.code());
                            ServiceProviderPendingJobOfferDeletResponce serviceProviderPendingJobOfferDeletResponce = response.body();
                            Log.d(TAG, "ChangeMyPostStatusResponce: " + serviceProviderPendingJobOfferDeletResponce.toString());
                            if (serviceProviderPendingJobOfferDeletResponce != null && serviceProviderPendingJobOfferDeletResponce.getChangeStatus() == 1) {



                                Intent intent = new Intent( ServiceProviderJobConfirm.this, ServiceProviderHome.class );
                                startActivity(intent);
                                finish();

                                buttons.setVisibility( View.VISIBLE );
                                progress_bar.setVisibility( View.GONE );


                            } else {
                                buttons.setVisibility( View.VISIBLE );
                                progress_bar.setVisibility( View.GONE );
                            }



                        } else {
                            Log.d(TAG, "onResponse: " + response.code());
                            buttons.setVisibility( View.VISIBLE );
                            progress_bar.setVisibility( View.GONE );
                        }
                    }

                    @Override
                    public void onFailure(Call<ServiceProviderPendingJobOfferDeletResponce> call, Throwable t) {
//                        Toast.makeText(MyAddView.this,"Data Featching Faild", Toast.LENGTH_SHORT).show();
                        Log.e( TAG, "onFailure: "+ t.getLocalizedMessage() );
                        buttons.setVisibility( View.VISIBLE );
                        progress_bar.setVisibility( View.GONE );
                    }
                });
            }
        } );



        confirm.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttons.setVisibility( View.GONE );
                progress_bar.setVisibility( View.VISIBLE );

                int mStatus = 2;
                id = String.valueOf(Id);



                Call <ServiceProviderPendingJobOfferDeletResponce> call = RetrofitClient.getInstance().getApiInterface().serviceProviderPendingJobOfferDeletResponce(id,mStatus);
                call.enqueue(new Callback <ServiceProviderPendingJobOfferDeletResponce>() {
                    @Override
                    public void onResponse(Call<ServiceProviderPendingJobOfferDeletResponce> call, Response <ServiceProviderPendingJobOfferDeletResponce> response) {
                        if (response.isSuccessful()) {
                            Log.d(TAG, "onResponse: " + response.code());
                            ServiceProviderPendingJobOfferDeletResponce serviceProviderPendingJobOfferDeletResponce = response.body();
                            Log.d(TAG, "ChangeMyPostStatusResponce: " + serviceProviderPendingJobOfferDeletResponce.toString());
                            if (serviceProviderPendingJobOfferDeletResponce != null && serviceProviderPendingJobOfferDeletResponce.getChangeStatus() == 1) {



//                                Intent intent = new Intent( ServiceProviderJobConfirm.this, ServiceProbiderCatagorySelect.class );
//                                startActivity(intent);
//                                finish();
//
//                                buttons.setVisibility( View.VISIBLE );
//                                progress_bar.setVisibility( View.GONE );

                                confirmData();


                            } else {
                                buttons.setVisibility( View.VISIBLE );
                                progress_bar.setVisibility( View.GONE );
                            }



                        } else {
                            Log.d(TAG, "onResponse: " + response.code());
                            buttons.setVisibility( View.VISIBLE );
                            progress_bar.setVisibility( View.GONE );
                        }
                    }

                    @Override
                    public void onFailure(Call<ServiceProviderPendingJobOfferDeletResponce> call, Throwable t) {
//                        Toast.makeText(MyAddView.this,"Data Featching Faild", Toast.LENGTH_SHORT).show();
                        Log.e( TAG, "onFailure: "+ t.getLocalizedMessage() );
                        buttons.setVisibility( View.VISIBLE );
                        progress_bar.setVisibility( View.GONE );
                    }
                });
            }
        } );



    }

    private void confirmData() {


        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        providerID = sharedPreferences.getString(KEY_ID, "");
        token = sharedPreferences.getString(KEY_TOKEN, "");
        providerName = sharedPreferences.getString(KEY_NAME, "");
        providerNumber = sharedPreferences.getString(KEY_MOBILE, "");
        providerProfileImage = sharedPreferences.getString(KEY_PROFILE_IMAGE, "");


        Call <ServiceConfirmResponce> call = RetrofitClient.getInstance(token).getApiInterface().serviceConfirmResponce(
                providerID,providerProfileImage,providerName,Longitude,Latitude,providerNumber,
                serviceTakerId,serviceTakerName,serviceTakerEmail,ServiceTakerNumbre,serviceTakerAddress,
                serviceTakerLongitude,serviceTakerLatitude,Image,serviceDate,serviceTime,serviceAddress);
        call.enqueue( new Callback <ServiceConfirmResponce>() {
            @Override
            public void onResponse(Call <ServiceConfirmResponce> call, Response <ServiceConfirmResponce> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.code());
                    ServiceConfirmResponce serviceConfirmResponce = response.body();
                    if (serviceConfirmResponce != null && serviceConfirmResponce.getStatus() == 1) {


                        Intent intent = new Intent( ServiceProviderJobConfirm.this, ServiceProviderHome.class );
                        startActivity(intent);
                        finish();

                        buttons.setVisibility( View.VISIBLE );
                        progress_bar.setVisibility( View.GONE );

//                                Toast.makeText(ServiceProviderProfileInServiceTaker.this, serviceTakerInterestResponce.getStatus(), Toast.LENGTH_LONG).show();
                    } else {
//                                Toast.makeText(ServiceProviderProfileInServiceTaker.this, serviceTakerInterestResponce.getStatus(), Toast.LENGTH_LONG).show();
                        buttons.setVisibility( View.VISIBLE );
                        progress_bar.setVisibility( View.GONE );
                    }
                }
                else {
                    Log.d(TAG, "onResponse: " + response.code());
                    buttons.setVisibility( View.VISIBLE );
                    progress_bar.setVisibility( View.GONE );
                }
            }

            @Override
            public void onFailure(Call <ServiceConfirmResponce> call, Throwable t) {
                Log.e( TAG, "onFailure: "+t.getLocalizedMessage() );
                buttons.setVisibility( View.VISIBLE );
                progress_bar.setVisibility( View.GONE );
            }
        } );

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap=googleMap;

        LatLng latLng = new LatLng( currentLocation.getLatitude(),currentLocation.getLongitude() );
        MarkerOptions markerOptions = new MarkerOptions().position( latLng ).title( "ME" );
        mMap.animateCamera( CameraUpdateFactory.newLatLng( latLng ) );
        mMap.animateCamera( CameraUpdateFactory.newLatLngZoom( latLng,14 ));
        mMap.addMarker( markerOptions );
//
//        Double lat=Double.parseDouble(serviceTakerLatitude);
//        Double longi=Double.parseDouble(serviceTakerLongitude);
//
//
//        LatLng mLatLng = new LatLng( lat,longi);
//        MarkerOptions mMarkerOptions = new MarkerOptions().position( mLatLng ).title( "Service Location" );
//        mMap.animateCamera( CameraUpdateFactory.newLatLng( mLatLng ) );
//        mMap.addMarker( mMarkerOptions );

//        googleMap.addMarker(new MarkerOptions().position(new LatLng(lat, longi)).title("Service Location"));


//        mMap.getUiSettings().setZoomControlsEnabled(true);
//        LatLng user=new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude() );
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(user));
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUTEST);
//            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(user, 15), 4200, null);
//
//
//
//
//            return;
//        }
//
//        mMap.setMyLocationEnabled(true);
//
//        mMap.getUiSettings().setZoomControlsEnabled(true);
//        mMap.getUiSettings().setMyLocationButtonEnabled(true);

        Double lat=Double.parseDouble(serviceTakerLatitude);
        Double longi=Double.parseDouble(serviceTakerLongitude);

        LatLng myLocation =new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude());
        mMap.addMarker(new MarkerOptions()
                .position(myLocation)
                .title("Service Location")
                //.icon(BitmapDescriptorFactory.fromResource(R.drawable.mi))
                );
        mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation));

    }


    private void featchLocation() {

        if (ActivityCompat.checkSelfPermission( this, Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions( this,new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
            return;
        }
        Task <Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener( new OnSuccessListener <Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null)
                {
                    currentLocation = location;

                    Toast.makeText( ServiceProviderJobConfirm.this, currentLocation.getLatitude()+"\n"+currentLocation.getLongitude(), Toast.LENGTH_LONG ).show();


                    Latitude = new Double(currentLocation.getLatitude()).toString();
                    Longitude = new Double(currentLocation.getLongitude()).toString();

                    SupportMapFragment supportMapFragment = (SupportMapFragment)
                            getSupportFragmentManager().findFragmentById( R.id.map );
                    supportMapFragment.getMapAsync( ServiceProviderJobConfirm.this );
                }
            }
        } );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_CODE:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    featchLocation();
                }
                break;
        }
    }
}
