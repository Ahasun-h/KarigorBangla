package com.mdrayefenam.karigorbangla.ServiceTaker.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.mdrayefenam.karigorbangla.R;
import com.mdrayefenam.karigorbangla.ServiceTaker.Model.LocationClass;
import com.mdrayefenam.karigorbangla.ServiceTaker.Model.LocationSession;


import java.security.Permission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import static android.os.Build.ID;

public class ServiceTakerLocation extends FragmentActivity implements OnMapReadyCallback {



    Button choose;


    String TAG = "ServiceTakerLocation";

    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;

    String Category,Latitude,Longitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_service_taker_location );


        choose = findViewById( R.id.choose );


        Category = getIntent().getStringExtra("category");
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient( this );

        featchLocation();


        choose.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( ServiceTakerLocation.this,ProviderRecyllerView.class );
                intent.putExtra("Latitude", Latitude);
                intent.putExtra( "Longitude",Longitude );
                startActivity( intent );
                finish();
            }
        } );



    }

    private void featchLocation() {

        if (ActivityCompat.checkSelfPermission( this, Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions( this,new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener( new OnSuccessListener <Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null)
                {
                    currentLocation = location;

                    Toast.makeText( ServiceTakerLocation.this, currentLocation.getLatitude()+"\n"+currentLocation.getLongitude(), Toast.LENGTH_LONG ).show();


                    Latitude = new Double(currentLocation.getLatitude()).toString();
                    Longitude = new Double(currentLocation.getLongitude()).toString();

                    LocationClass locationClass = new LocationClass();
                    locationClass.setLatitude( Latitude );
                    locationClass.setLongitude( Longitude );
                    LocationSession.getInstance( getApplicationContext()).putAddress(locationClass);



                    Log.e( TAG, "Result : \n"+"Category :"+Category+"\n"+"Latitude :"+currentLocation.getLatitude()+"\n"+"Longitude :"+currentLocation.getLongitude());

                    SupportMapFragment supportMapFragment = (SupportMapFragment)
                            getSupportFragmentManager().findFragmentById( R.id.map );
                    supportMapFragment.getMapAsync( ServiceTakerLocation.this );
                }
            }
        } );
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        LatLng latLng = new LatLng( currentLocation.getLatitude(),currentLocation.getLongitude() );
        MarkerOptions markerOptions = new MarkerOptions().position( latLng ).title( "ME" );
        googleMap.animateCamera( CameraUpdateFactory.newLatLng( latLng ) );
        googleMap.animateCamera( CameraUpdateFactory.newLatLngZoom( latLng,14 ));
        googleMap.addMarker( markerOptions );

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
