package com.mdrayefenam.karigorbangla.RootActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mdrayefenam.karigorbangla.Model.SessionData;
import com.mdrayefenam.karigorbangla.Network.RetrofitClient;
import com.mdrayefenam.karigorbangla.R;
import com.mdrayefenam.karigorbangla.Responce.LoginModel.LoginResponce;
import com.mdrayefenam.karigorbangla.ServiceProvider.Activity.ServiceProviderHome;
import com.mdrayefenam.karigorbangla.ServiceTaker.Activity.MainActivity;
import com.mdrayefenam.karigorbangla.SessionClass.SessionClass;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText login_email, login_password;
    TextView sign_in;
    Button bottomSinUp;
    private String TAG = "LoginActivity ";

    ProgressBar progress_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_email = findViewById(R.id.login_email);
        login_password = findViewById(R.id.login_password);

        bottomSinUp = findViewById(R.id.bottomSinUp);

        progress_bar = findViewById(R.id.progress_bar);
        progress_bar.setVisibility( View.GONE );

        sign_in = findViewById(R.id.sign_in);


        bottomSinUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String email = login_email.getText().toString().trim();
                String password = login_password.getText().toString().trim();

                if (email.equals("")) {
                    login_email.setError("Enter Your Email");
                } else if (password.equals("")) {
                    login_password.setError("Enter Your password");
                } else {

                    progress_bar.setVisibility( View.VISIBLE );
                    bottomSinUp.setVisibility( View.GONE );

                    doLogin(email, password);
//                    if (email.equals( "habib") && password.equals( "1234" ) ){
//                        Intent intent = new Intent( Login.this, MainActivity.class );
//                        startActivity( intent );
//                        finish();
//                    }else {
//                        Toast.makeText( Login.this, "your email or password is incorrect. please try again", Toast.LENGTH_SHORT ).show();
//                    }


                }
            }
        });

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, NumberAuthentication.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void doLogin(String email, String password) {
        Call<LoginResponce> call = RetrofitClient.getInstance().getApiInterface().userLogin(email, password);
        call.enqueue(new Callback<LoginResponce>() {
            @Override
            public void onResponse(Call<LoginResponce> call, Response<LoginResponce> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.code());
                    LoginResponce loginResponse = response.body();
                    if (loginResponse != null && loginResponse.getStatus() == 1) {
                        Log.e(TAG, "onResponse: " + loginResponse.getToken().getOriginal().getAccessToken());
                        Log.e(TAG, "onResponse: " + loginResponse.getToken().getOriginal().getAccessToken().length());
                        Toast.makeText(LoginActivity.this, loginResponse.getUserId().toString(), Toast.LENGTH_SHORT).show();

                        int id = loginResponse.getUserId();
                        String ID = String.valueOf(id);

                        progress_bar.setVisibility( View.GONE );
                        bottomSinUp.setVisibility( View.VISIBLE );

                        if (loginResponse.getUserInformation().getUserType().equals( "User" )){

                            SessionData sessionData = new SessionData();
                            sessionData.setId( ID );
                            sessionData.setName( loginResponse.getUserInformation().getName() );
                            sessionData.setEmail( loginResponse.getUserInformation().getEmail() );
                            sessionData.setMobile( loginResponse.getUserInformation().getMobile() );
                            sessionData.setAddress( loginResponse.getUserInformation().getAddress() );
                            sessionData.setGender( loginResponse.getUserInformation().getGender() );
                            sessionData.setUser_profile_photo( loginResponse.getUserInformation().getUserProfilePhoto() );
                            Log.e( TAG, "profile_photo: "+ loginResponse.getUserInformation().getUserProfilePhoto() );
                            sessionData.setUser_type( loginResponse.getUserInformation().getUserType() );
                            sessionData.setToken( loginResponse.getToken().getOriginal().getAccessToken() );
                            SessionClass.getInstance(getApplicationContext()).userLogin(sessionData);

                            Intent intent = new Intent( LoginActivity.this, MainActivity.class );
                            startActivity( intent );
                            finish();

                        }else {

                            SessionData sessionData = new SessionData();
                            sessionData.setId( ID );
                            sessionData.setName( loginResponse.getUserInformation().getName() );
                            sessionData.setEmail( loginResponse.getUserInformation().getEmail() );
                            sessionData.setMobile( loginResponse.getUserInformation().getMobile() );
                            sessionData.setAddress( loginResponse.getUserInformation().getAddress() );
                            sessionData.setGender( loginResponse.getUserInformation().getGender() );
                            sessionData.setUser_profile_photo( loginResponse.getUserInformation().getUserProfilePhoto() );
                            sessionData.setUser_type( loginResponse.getUserInformation().getUserType() );
                            sessionData.setToken( loginResponse.getToken().getOriginal().getAccessToken() );
                            SessionClass.getInstance(getApplicationContext()).userLogin(sessionData);
                            sessionData.setCategory( loginResponse.getServiceProviderInformation().getCategory() );
                            sessionData.setHourly_rate( loginResponse.getServiceProviderInformation().getHourlyRate() );
                            SessionClass.getInstance(getApplicationContext()).userLogin(sessionData);

                            Intent intent = new Intent( LoginActivity.this, ServiceProviderHome.class );
                            startActivity( intent );
                            finish();

                        }

//                        SessionData sessionData = new SessionData();

//                        sessionData.setId( userID );
//                        sessionData.setToken( UserToken );
//                        SessionClass.getInstance(getApplicationContext()).userLogin(sessionData);


//                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                        startActivity(intent);
//                        finish();
                    } else {

                        progress_bar.setVisibility( View.GONE );
                        bottomSinUp.setVisibility( View.VISIBLE );

                        showDialog();
//                        Toast.makeText(LoginActivity.this, loginResponse.getStatus(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.d(TAG, "onResponse: " + response.code());
                    progress_bar.setVisibility( View.GONE );
                    bottomSinUp.setVisibility( View.VISIBLE );
                }
            }

            @Override
            public void onFailure(Call<LoginResponce> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                progress_bar.setVisibility( View.GONE );
                bottomSinUp.setVisibility( View.VISIBLE );
            }
        });
    }

    void showDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this, R.style.CustomAlertDialog);
        LayoutInflater inflater = LayoutInflater.from(this);
        final View viewLayout = inflater.inflate(R.layout.login_failed_alart_dialog , null);
        dialog.setView(viewLayout);
        Button ok = (Button) viewLayout.findViewById(R.id.logok);
        final AlertDialog  alertDialog=dialog.create();
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

}
