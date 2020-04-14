package com.mdrayefenam.karigorbangla.ServiceProvider.Activity;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.mdrayefenam.karigorbangla.Model.SessionData;
import com.mdrayefenam.karigorbangla.Network.RetrofitClient;
import com.mdrayefenam.karigorbangla.R;
import com.mdrayefenam.karigorbangla.RootActivity.SingUp;
import com.mdrayefenam.karigorbangla.ServiceProvider.GeocodingLocation.GeocodingLocation;
import com.mdrayefenam.karigorbangla.ServiceProvider.ServiceProviderInformationModel.ServiceProviderInformationResponce;
import com.mdrayefenam.karigorbangla.SessionClass.SessionClass;

public class ServiceProbiderCatagorySelect extends AppCompatActivity {

    EditText rate,editText;
    RadioGroup RG;

    Button submit;
    ProgressBar progressBar;

    String Rate,Category;
    String City,latitude,longitude,Area;
    Spinner spinner_city,spinner_area;

    private String TAG = "ServiceProbiderCatagorySelectActivity ";

    String id,name,email,mobile,address,gender,user_profile_photo,user_type,token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.service_probider_catagory_select );


        spinner_city = findViewById( R.id.spinner_city );
        spinner_area = findViewById( R.id.spinner_area );


        rate = findViewById(R.id.rate);
        RG = findViewById(R.id.RG);

        editText = (EditText) findViewById(R.id.etAdd);

        progressBar = findViewById( R.id.prgress_bar );
        progressBar.setVisibility( View.GONE );


        submit = findViewById(R.id.submit);
        submit.setVisibility( View.VISIBLE );

        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");
        mobile = getIntent().getStringExtra("mobile");
        address = getIntent().getStringExtra("address");
        gender = getIntent().getStringExtra("gender");
        user_profile_photo = getIntent().getStringExtra("user_profile_photo");
        user_type = getIntent().getStringExtra("user_type");
        token = getIntent().getStringExtra("token");

        Log.d(TAG, "Data: "+id+" "+name+" "+email+" "+mobile+" "+address+" "+gender+" "+user_profile_photo+" "+user_type+" "+token);


        ArrayAdapter <String> cityAdapter = new ArrayAdapter<String>( ServiceProbiderCatagorySelect.this,android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.city));
        cityAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner_city.setAdapter(cityAdapter);
        spinner_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                City = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter <String> areaAdapter = new ArrayAdapter<String>( ServiceProbiderCatagorySelect.this,android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.area));
        areaAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner_area.setAdapter(areaAdapter);
        spinner_area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Area = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        RG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Toast.makeText(getBaseContext(), Category, Toast.LENGTH_SHORT).show();
            }
        });

        Log.d(TAG, "Data: "+ Category+" "+Rate+" "+id);


        submit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Address;

                submit.setVisibility( View.GONE );
                progressBar.setVisibility( View.VISIBLE );

                Address = editText.getText().toString();

                address = Address+","+Area+","+City+","+"Bangladesh";

                Category = ((RadioButton)findViewById(RG.getCheckedRadioButtonId())).getText().toString();
                Rate = rate.getText().toString().trim();

                GeocodingLocation locationAddress = new GeocodingLocation();
                locationAddress.getAddressFromLocation(address,
                        getApplicationContext(), new GeocoderHandler());

            }
        } );

    }



    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String Latitude,Longitude;

            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    Latitude = bundle.getString("latitude");
                    Longitude = bundle.getString("longitude");
                    latitude = Latitude;
                    longitude = Longitude;

                    Log.d("latttt",Latitude+"\n"+Longitude);
                    saveCategory();
                    break;
                default:

                    Latitude = null;
                    Longitude = null;

                    latitude = Latitude;
                    longitude = Longitude;

                    submit.setVisibility( View.VISIBLE );
                    progressBar.setVisibility( View.GONE );
            }
        }
    }

    private void saveCategory() {

        Log.d(TAG, "Data: "+ Category+" "+Rate+" "+id);

        Call<ServiceProviderInformationResponce> call = RetrofitClient.getInstance(token).getApiInterface().saveCategoryInfo(Category,Rate,latitude,longitude,id);
        Log.d(TAG, "Data: "+ Category+" "+Rate+" "+id);
        call.enqueue( new Callback <ServiceProviderInformationResponce>() {
            @Override
            public void onResponse(Call <ServiceProviderInformationResponce> call, Response <ServiceProviderInformationResponce> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.code());
                    ServiceProviderInformationResponce serviceProviderInformationResponce = response.body();

                        submit.setVisibility( View.VISIBLE );
                        progressBar.setVisibility( View.GONE );

                        SessionData sessionData = new SessionData();
                        sessionData.setId( id );
                        sessionData.setName( name );
                        sessionData.setEmail( email );
                        sessionData.setMobile( mobile );
                        sessionData.setAddress( address );
                        sessionData.setGender( gender );
                        sessionData.setUser_profile_photo( user_profile_photo );
                        sessionData.setUser_type( user_type );
                        sessionData.setToken( token );
                        sessionData.setCategory( serviceProviderInformationResponce.getServiceProviderInformation().getCategory() );
                        sessionData.setHourly_rate( serviceProviderInformationResponce.getServiceProviderInformation().getHourlyRate() );
                        SessionClass.getInstance(getApplicationContext()).userLogin(sessionData);

                        submit.setVisibility( View.VISIBLE );
                        progressBar.setVisibility( View.GONE );

                        Intent intent = new Intent( ServiceProbiderCatagorySelect.this,ServiceProviderHome.class );
                        startActivity( intent );
                        finish();

                        Toast.makeText(ServiceProbiderCatagorySelect.this, serviceProviderInformationResponce.getSuccess().toString(), Toast.LENGTH_LONG).show();

                }
                else {
                    Log.d(TAG, "onResponse: " + response.code());
                    submit.setVisibility( View.VISIBLE );
                    progressBar.setVisibility( View.GONE );
                }
            }

            @Override
            public void onFailure(Call <ServiceProviderInformationResponce> call, Throwable t) {
                submit.setVisibility( View.VISIBLE );
                progressBar.setVisibility( View.GONE );
                Log.d(TAG, "onFailure: "+ t.getLocalizedMessage());
            }
        } );
    }
}
