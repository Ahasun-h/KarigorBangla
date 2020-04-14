package com.mdrayefenam.karigorbangla.ServiceTaker.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mdrayefenam.karigorbangla.Network.RetrofitClient;
import com.mdrayefenam.karigorbangla.R;
import com.mdrayefenam.karigorbangla.ServiceTaker.Adapter.ServiceProviderInServiceTakerRecylerView;
import com.mdrayefenam.karigorbangla.ServiceTaker.ServiceProviderLocationModel.ServiceProvider;
import com.mdrayefenam.karigorbangla.ServiceTaker.ServiceProviderLocationModel.ServiceProviderLocationForServiceTakerResponce;

import java.util.ArrayList;
import java.util.List;

public class ProviderRecyllerView extends AppCompatActivity {

    String TAG = "ProviderRecyllerView";

    RecyclerView AdRecylerView;

    ServiceProviderInServiceTakerRecylerView serviceProviderInServiceTakerRecylerView;
    LinearLayout loding;
    ProgressBar progress_bar_pagination;
    EditText search;

    private List <ServiceProvider> serviceProviders = new ArrayList <>(  );

    LinearLayoutManager linearLayoutManager;

    private static final String SHARED_PREF_NAME = "simplifiedcodingsharedpref";
    private static final String KEY_TOKEN = "keytoken";

    String token,Latitude,Longitude;

    ServiceProviderLocationForServiceTakerResponce serviceProviderLocationForServiceTakerResponce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_provider_recyller_view );

        linearLayoutManager = new LinearLayoutManager(ProviderRecyllerView.this, LinearLayoutManager.VERTICAL, false);

        progress_bar_pagination = findViewById(R.id.progress_bar_pagination);

        loding = findViewById(R.id.loding);
        loding.setVisibility( View.VISIBLE );

        AdRecylerView = findViewById(R.id.ad_recyler_view);

        serviceProviderInServiceTakerRecylerView = new ServiceProviderInServiceTakerRecylerView(ProviderRecyllerView.this,serviceProviders);
//        AdRecylerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        AdRecylerView.setAdapter(adPostRecylerViewAdapter);

        AdRecylerView.setLayoutManager( linearLayoutManager );
        AdRecylerView.setItemAnimator(new DefaultItemAnimator());
        AdRecylerView.setAdapter(serviceProviderInServiceTakerRecylerView);

        Latitude = getIntent().getStringExtra("Latitude");
        Longitude = getIntent().getStringExtra("Longitude");


        Log.e( TAG, "onCreate: "+"\n"+"Latitude :"+Latitude+"\n"+"Longitude :"+Longitude );
        Toast.makeText( this, "Latitude :"+Latitude+"\n"+"Longitude :"+Longitude , Toast.LENGTH_SHORT ).show();


        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        token = sharedPreferences.getString(KEY_TOKEN, "");

        fetchProvider();


    }

    private void fetchProvider() {


        Log.e( TAG, "fetchProvider Data: "+ Longitude+"\n"+Latitude);





        Call <ServiceProviderLocationForServiceTakerResponce> call = RetrofitClient.getInstance( token ).getApiInterface().ServiceProviderLocationForServiceTakeData( Longitude,Latitude);
        call.enqueue( new Callback <ServiceProviderLocationForServiceTakerResponce>() {
            @Override
            public void onResponse(Call<ServiceProviderLocationForServiceTakerResponce> call, Response <ServiceProviderLocationForServiceTakerResponce> response) {
                serviceProviderLocationForServiceTakerResponce = response.body();

                Log.e( TAG, "onResponseBB: "+response.body());
                if (response.isSuccessful())
                {
                    serviceProviders = serviceProviderLocationForServiceTakerResponce.getServiceProviders();

                    Log.e( TAG, "onResponseAA: "+response.body().toString()  );

                    serviceProviderInServiceTakerRecylerView.updateList( serviceProviders );
                    loding.setVisibility( View.GONE );
                    AdRecylerView.setVisibility( View.VISIBLE );


                }else
                {
                    Toast.makeText( ProviderRecyllerView.this, "Fail", Toast.LENGTH_SHORT ).show();
                }
            }

            @Override
            public void onFailure(Call<ServiceProviderLocationForServiceTakerResponce> call, Throwable t) {
                Toast.makeText(ProviderRecyllerView.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.e( TAG, "onFailure: "+ t.getLocalizedMessage() );
            }
        } );

    }
}
