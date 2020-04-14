package com.mdrayefenam.karigorbangla.ServiceProvider.Activity;

import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mdrayefenam.karigorbangla.Network.RetrofitClient;
import com.mdrayefenam.karigorbangla.R;
import com.mdrayefenam.karigorbangla.ServiceProvider.DeletServiceProviderPendingJobOfferModel.ServiceProviderPendingJobOfferDeletResponce;
import com.mdrayefenam.karigorbangla.ServiceProvider.ProviderConfirmJobListModel.ProviderConfirmJobListResponce;
import com.mdrayefenam.karigorbangla.ServiceProvider.ServiceProviderArriveStatusChangeModel.ServiceProviderArriveStatusChangeResmpoce;
import com.mdrayefenam.karigorbangla.ServiceTaker.Activity.MainActivity;

public class ServiceProviderJobViewActivity extends AppCompatActivity {

    String TAG ="ServiceProviderJobViewActivity";

    private static final String SHARED_PREF_NAME = "simplifiedcodingsharedpref";
    private static final String KEY_TOKEN = "keytoken";
    private Context context;

    String token;

    Button button;
    ProgressBar progress_bar;

    CircleImageView profile_image;
    TextView job_id,provider_name,provider_email,provider_address,provider_date,provider_time,provider_Address;

    String serviceTakerName,serviceTakerEmail,serviceTakerAddress,serviceTakerNumber,jobId,serviceTakerProfileImage,serviceDate,sertviceTime,serviceAddress;

    int ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_service_provider_job_view );

        profile_image = findViewById( R.id.profile_image );

        job_id = findViewById( R.id.job_id );

        provider_name = findViewById( R.id.provider_name );
        provider_email = findViewById( R.id.provider_email );
        provider_address = findViewById( R.id.provider_address );

        provider_date = findViewById( R.id.provider_date );
        provider_time = findViewById( R.id.provider_time );
        provider_Address = findViewById( R.id.provider_Address );

        progress_bar = findViewById( R.id.progress_bar );
        progress_bar.setVisibility( View.GONE );

        button = findViewById( R.id.button );

        ID = getIntent().getIntExtra( "ServiceID",0 );
        serviceTakerProfileImage = getIntent().getStringExtra( "ServiceTakerProfilePhoto" );
        serviceTakerName = getIntent().getStringExtra( "ServiceTakerName" );
        serviceTakerEmail  = getIntent().getStringExtra( "ServiceTakerEmail" );
        serviceTakerAddress = getIntent().getStringExtra( "ServiceTakerAddress" );
        serviceTakerNumber = getIntent().getStringExtra( "ServiceTakerNumbre" );
        serviceDate = getIntent().getStringExtra( "ServiceDay" );
        sertviceTime = getIntent().getStringExtra( "ServiceTime" );
        serviceAddress = getIntent().getStringExtra( "ServiceAddress" );

        jobId = String.valueOf(ID);
        job_id.setText( "Job Id : #"+jobId );

        provider_name.setText( "User Name : "+ serviceTakerName);
        provider_email.setText( "Email : "+ serviceTakerEmail);
        provider_address.setText( "Address : "+ serviceTakerAddress);
        provider_date.setText( "Service Date : "+ serviceDate);
        provider_time.setText( "Service Time : "+ sertviceTime);
        provider_Address.setText( "Service Address : "+ serviceAddress);


        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progress_bar.setVisibility( View.VISIBLE );
                button.setVisibility( View.GONE );

                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
                token = sharedPreferences.getString(KEY_TOKEN, "");

                int provider_arrived_status = 1;


                Log.e( TAG, "onClickData: "+jobId+"\n"+provider_arrived_status );

                Call <ServiceProviderArriveStatusChangeResmpoce> call = RetrofitClient.getInstance(token).getApiInterface().serviceProviderArriveStatusChangeResmpoce(ID,provider_arrived_status);
                Log.e( TAG, "onClickData: "+jobId+"\n"+provider_arrived_status );
                call.enqueue(new Callback <ServiceProviderArriveStatusChangeResmpoce>() {
                    @Override
                    public void onResponse(Call<ServiceProviderArriveStatusChangeResmpoce> call, Response <ServiceProviderArriveStatusChangeResmpoce> response) {
                        if (response.isSuccessful()) {
                            Log.d(TAG, "onResponse: " + response.code());
                            ServiceProviderArriveStatusChangeResmpoce serviceProviderArriveStatusChangeResmpoce = response.body();
                            Log.d(TAG, "ChangeMyPostStatusResponce: " + response.body());
                            if (serviceProviderArriveStatusChangeResmpoce != null && serviceProviderArriveStatusChangeResmpoce.getChangeStatus() == 1) {

                                progress_bar.setVisibility( View.GONE );
                                button.setVisibility( View.VISIBLE );

                                Intent intent = new Intent( ServiceProviderJobViewActivity.this, ServiceProviderHome.class );
                                startActivity(intent);
                                finish();


                            } else {
                                progress_bar.setVisibility( View.GONE );
                                button.setVisibility( View.VISIBLE );
                            }



                        } else {
                            Log.d(TAG, "onResponse: " + response.code());
                            progress_bar.setVisibility( View.GONE );
                            button.setVisibility( View.VISIBLE );
                        }
                    }

                    @Override
                    public void onFailure(Call<ServiceProviderArriveStatusChangeResmpoce> call, Throwable t) {
//                        Toast.makeText(MyAddView.this,"Data Featching Faild", Toast.LENGTH_SHORT).show();
                        progress_bar.setVisibility( View.GONE );
                        button.setVisibility( View.VISIBLE );
                    }
                });



            }


        } );

    }
}
