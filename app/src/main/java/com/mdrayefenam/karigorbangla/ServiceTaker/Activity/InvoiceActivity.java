package com.mdrayefenam.karigorbangla.ServiceTaker.Activity;

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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mdrayefenam.karigorbangla.Model.SessionData;
import com.mdrayefenam.karigorbangla.Network.RetrofitClient;
import com.mdrayefenam.karigorbangla.R;
import com.mdrayefenam.karigorbangla.Responce.LoginModel.LoginResponce;
import com.mdrayefenam.karigorbangla.RootActivity.LoginActivity;
import com.mdrayefenam.karigorbangla.ServiceProvider.Activity.ServiceProviderHome;
import com.mdrayefenam.karigorbangla.ServiceTaker.InvoiceModel.InvoiceResponce;
import com.mdrayefenam.karigorbangla.SessionClass.SessionClass;

import java.text.DecimalFormat;

public class InvoiceActivity extends AppCompatActivity {

    TextView userName,userEmail,userAddress,date,category,time,amounth;
    Button pay_button;
    CircleImageView profile_image;

    String Name,ID,Mobile,serviceDay,TimeLenth,token,Image,ProfileImage;

    String houreRate,Catagory;
    Double TotalRate,ResultRate,ProviderTime;

    int timeS;

    String resultRate;

    RelativeLayout content;
    ProgressBar progress_bar;

    String TAG = "InvoiceActivity";


    private static final String SHARED_PREF_NAME = "simplifiedcodingsharedpref";
    private static final String KEY_TOKEN = "keytoken";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_invoice );


        userName = findViewById( R.id.userName );
        userEmail = findViewById( R.id.userEmail );
        userAddress = findViewById( R.id.userAddress );
        date = findViewById( R.id.date );
        category = findViewById( R.id.category );
        time = findViewById( R.id.time );
        amounth = findViewById( R.id.amounth );

        pay_button =  findViewById( R.id.pay_button );

        profile_image = findViewById( R.id.profile_image );

        content = findViewById( R.id.content );
        content.setVisibility( View.GONE );

        progress_bar = findViewById( R.id.progress_bar );
        progress_bar.setVisibility( View.VISIBLE );




        ID = getIntent().getStringExtra("ProviderId");
        Log.e( TAG, "ID : "+ID );
        Name = getIntent().getStringExtra("ProviderName");
        Mobile = getIntent().getStringExtra("ProviderMobile");
        serviceDay = getIntent().getStringExtra("ServiceDay");
        TimeLenth = getIntent().getStringExtra("TimeLenth");

        Image = getIntent().getStringExtra("profileImage");




        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        token = sharedPreferences.getString(KEY_TOKEN, "");

        fetchData();


//        userName.setText("Provider Id : #"+ID );
//        userEmail.setText( Name );
//        userAddress.setText( Mobile );
//        date.setText( serviceDay );
//
//        time.setText( TimeLenth );


//        ProviderTime = Double.parseDouble(TimeLenth.toString());

//        Log.e( TAG, "ProviderTime: "+"ProviderTime" +"\n"+ "TotalRate :"+TotalRate);
//        ResultRate = ProviderTime * TotalRate;









        pay_button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( InvoiceActivity.this, MainActivity.class );
                startActivity(intent);
                finish();
            }
        } );




    }

    private void fetchData() {

        Toast.makeText( this, "Enter The functiuon", Toast.LENGTH_SHORT ).show();

        Call <InvoiceResponce> call = RetrofitClient.getInstance(token).getApiInterface().invoiceResponce(ID);
        Log.e( TAG, "fetchData: "+token );
        call.enqueue( new Callback <InvoiceResponce>() {
            @Override
            public void onResponse(Call <InvoiceResponce> call, Response <InvoiceResponce> response) {
                if (response.isSuccessful()) {

                    Log.e( TAG, "onResponse: "+ response.body() );



                    InvoiceResponce invoiceResponce = response.body();

                    houreRate = invoiceResponce.getInvoice().getHourlyRate();

                    Log.e( TAG, "houreRate: "+  invoiceResponce.getInvoice().getHourlyRate());
                    Catagory = invoiceResponce.getInvoice().getCategory();

                    Log.e( TAG, "houreRate"+houreRate +"\n"+"Catagory" +Catagory);

                    Double HoureRate=Double.parseDouble(houreRate.toString());
                    TotalRate = HoureRate / 60;

                    Log.e( TAG, "HoureRate"+HoureRate +"\n"+"TotalRate" +TotalRate);


                    ProviderTime = Double.parseDouble(TimeLenth.toString());

                    Log.e( TAG, "ProviderTime: "+"ProviderTime" +"\n"+ "TotalRate :"+TotalRate);
                    ResultRate = ProviderTime * TotalRate;

                    category.setText( Catagory );

//                    resultRate = String.valueOf(ResultRate);
//                    amounth.setText( resultRate +"  Tk" );


                    amounth.setText(new DecimalFormat("##.##").format(ResultRate)+" Tk");

                    userName.setText("Provider Id : #"+ID );
                    userEmail.setText( Name );
                    userAddress.setText( Mobile );
                    date.setText( serviceDay );

                    time.setText( TimeLenth );

                    ProfileImage = "http://karigor.againwish.com/"+Image;

                    RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.profile_user_icon).error(R.drawable.profile_user_icon);
                    Glide.with(InvoiceActivity.this).load(ProfileImage).apply(requestOptions).into(profile_image);


                    progress_bar.setVisibility( View.GONE );
                    content.setVisibility( View.VISIBLE );



                }else {
                    Log.d( TAG, "onResponse: " + response.code() );

                    progress_bar.setVisibility( View.GONE );
                    content.setVisibility( View.VISIBLE );

                    Toast.makeText( InvoiceActivity.this, "Else Error", Toast.LENGTH_SHORT ).show();

                }
            }

            @Override
            public void onFailure(Call <InvoiceResponce> call, Throwable t) {
                Toast.makeText( InvoiceActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT ).show();

            }
        } );

    }
}
