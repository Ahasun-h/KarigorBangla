package com.mdrayefenam.karigorbangla.ServiceTaker.Activity;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mdrayefenam.karigorbangla.Network.RetrofitClient;
import com.mdrayefenam.karigorbangla.R;
import com.mdrayefenam.karigorbangla.ServiceProvider.DeletServiceProviderPendingJobOfferModel.ServiceProviderPendingJobOfferDeletResponce;
import com.mdrayefenam.karigorbangla.ServiceTaker.AddJobHistoryModel.AddJobHistoryRespoce;
import com.mdrayefenam.karigorbangla.ServiceTaker.ChageJobCompleteStatusModel.JobCompleteStatusResmpoce;

import java.util.Calendar;
import java.util.Locale;

public class ServiceTime extends AppCompatActivity {


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

    String TAG = "ServiceTime";

    TextView timer ;
    Button start, pause, reset;
    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L ;
    Handler handler;
    int Seconds, Minutes, MilliSeconds ;

    ImageView iv;
    TextView service_taker_name,service_taker_address,service_taker_email,service_Date,service_time,service_address;

    String ProviderId,ProviderName,ProviderMobile,ProviderProfileImage,ServiceDay,serviceTime,ServiceAddress,Image,JOBID,token;
    int ID,joBID;

    String TimeValue;

    String name,email,Address,Gender,TimeLenth,ProfileImage,mobile,userType,userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_service_time );

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        userID = sharedPreferences.getString(KEY_ID, "");
        token = sharedPreferences.getString(KEY_TOKEN, "");
        name = sharedPreferences.getString(KEY_NAME, "");
        email = sharedPreferences.getString(KEY_EMAIL, "");
        mobile = sharedPreferences.getString(KEY_MOBILE, "");
        Address = sharedPreferences.getString(KEY_ADDRESS, "");
        ProfileImage = sharedPreferences.getString(KEY_PROFILE_IMAGE, "");
        Gender = sharedPreferences.getString(KEY_GENDER, "");
        userType = sharedPreferences.getString(KEY_USER_TYPE, "");

        iv = findViewById( R.id.iv );
        service_taker_name = findViewById( R.id.service_taker_name );
        service_taker_address = findViewById( R.id.service_taker_address );
        service_taker_email = findViewById( R.id.service_taker_email );
        service_Date = findViewById( R.id.service_Date );
        service_time = findViewById( R.id.service_time );
        service_address = findViewById( R.id.service_address );

        timer = findViewById(R.id.tvTimer);

        handler = new Handler() ;
        StartTime = SystemClock.uptimeMillis();
        handler.postDelayed(runnable, 0);



        joBID = getIntent().getIntExtra( "JoBId",0 );
        ProviderId = getIntent().getStringExtra( "ProviderId");

        ProviderName = getIntent().getStringExtra( "ProviderName");
        ProviderMobile = getIntent().getStringExtra( "ProviderMobile");
        Image = getIntent().getStringExtra( "ProviderProfileImage");
        ServiceDay = getIntent().getStringExtra( "ServiceDay");
        serviceTime = getIntent().getStringExtra( "ServiceTime");
        ServiceAddress = getIntent().getStringExtra( "ServiceAddress");


        ProviderProfileImage = "https://karigor.againwish.com/"+Image;
        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.profile_user_icon).error(R.drawable.profile_user_icon);
        Glide.with(ServiceTime.this).load(ProviderProfileImage).apply(requestOptions).into(iv);


        JOBID = String.valueOf(joBID);
        service_taker_name.setText("Provider Id : #"+ProviderId);
        service_taker_address.setText( "Provider Name : "+ProviderName);
        service_taker_email.setText( "Provider Number : "+ ProviderMobile);
        service_Date.setText( "Service Date : " +ServiceDay);
        service_time.setText( "Service Time : " + serviceTime);
        service_address.setText( "Service Address : " + ServiceAddress);;


        Button buttonOne = findViewById(R.id.workfinish);
        buttonOne.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

//                Log.e( TAG, "Data :"+token +"\n"+Id);

                int mStatus = 2;

                Call <JobCompleteStatusResmpoce> call = RetrofitClient.getInstance(token).getApiInterface().jobCompleteStatusResmpoce(joBID,mStatus);
                call.enqueue(new Callback <JobCompleteStatusResmpoce>() {
                    @Override
                    public void onResponse(Call<JobCompleteStatusResmpoce> call, Response <JobCompleteStatusResmpoce> response) {
                        if (response.isSuccessful()) {
//                            Log.d(TAG, "onResponse: " + response.code());
                            JobCompleteStatusResmpoce jobCompleteStatusResmpoce = response.body();
//                            Log.d(TAG, "ChangeMyPostStatusResponce: " + changeMyPostStatusResponce.toString());
                            if (jobCompleteStatusResmpoce != null && jobCompleteStatusResmpoce.getJobCompleteStatus() == 1) {


                                addHistory();




                            } else {

                            }



                        } else {
//                            Log.d(TAG, "onResponse: " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<JobCompleteStatusResmpoce> call, Throwable t) {
//                        Toast.makeText(MyAddView.this,"Data Featching Faild", Toast.LENGTH_SHORT).show();
                    }
                });

            }

        });





    }

    private void addHistory() {





        Call <AddJobHistoryRespoce> call = RetrofitClient.getInstance(token).getApiInterface().addJobHistoryRespoce(ProviderId,Image,ProviderName,ProviderMobile,userID,name,email,mobile,Address,ProfileImage,ServiceDay,serviceTime,ServiceAddress,TimeLenth);
        call.enqueue(new Callback <AddJobHistoryRespoce>() {
            @Override
            public void onResponse(Call<AddJobHistoryRespoce> call, Response <AddJobHistoryRespoce> response) {
                if (response.isSuccessful()) {
//                            Log.d(TAG, "onResponse: " + response.code());
                    AddJobHistoryRespoce addJobHistoryRespoce = response.body();
//                            Log.d(TAG, "ChangeMyPostStatusResponce: " + changeMyPostStatusResponce.toString());
                    if (addJobHistoryRespoce != null && addJobHistoryRespoce.getStatus() == 1) {

                        Intent intent = new Intent( ServiceTime.this, InvoiceActivity.class );

                        TimeValue = Integer.toString(Minutes);

                        intent.putExtra( "profileImage", Image);
                        intent.putExtra( "ProviderId", ProviderId );
                        intent.putExtra( "ProviderName", ProviderName );
                        intent.putExtra( "ProviderMobile", ProviderMobile );

                        intent.putExtra( "ServiceDay", ServiceDay );
                        intent.putExtra( "TimeLenth", TimeValue );


                        startActivity(intent);
                        finish();


                    } else {

                    }



                } else {
//                            Log.d(TAG, "onResponse: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<AddJobHistoryRespoce> call, Throwable t) {
//                        Toast.makeText(MyAddView.this,"Data Featching Faild", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public Runnable runnable = new Runnable() {

        public void run() {
            MillisecondTime = SystemClock.uptimeMillis() - StartTime;
            UpdateTime = TimeBuff + MillisecondTime;
            Seconds = (int) (UpdateTime / 1000);
            Minutes = Seconds / 60;
            Seconds = Seconds % 60;
            MilliSeconds = (int) (UpdateTime % 1000);
            timer.setText("" + Minutes + ":"
                    + String.format("%02d", Seconds) + ":"
                    + String.format("%03d", MilliSeconds));
             TimeLenth = timer.getText().toString();

            handler.postDelayed(this, 0);
        }
    };
}
