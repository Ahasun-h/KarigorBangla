package com.mdrayefenam.karigorbangla.ServiceTaker.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.mdrayefenam.karigorbangla.Model.SessionData;
import com.mdrayefenam.karigorbangla.Network.RetrofitClient;
import com.mdrayefenam.karigorbangla.R;
import com.mdrayefenam.karigorbangla.RootActivity.LoginActivity;
import com.mdrayefenam.karigorbangla.ServiceTaker.ServiceTakerProfileUpdateModel.ServiceTakerProfileUpdateResponce;
import com.mdrayefenam.karigorbangla.SessionClass.SessionClass;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceTakerProfileUpdate extends AppCompatActivity {

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

    String TAG = "ServiceTakerProfileUpdate";
    ImageView profile_image;
    private Bitmap bitmap;
    String encodedImage;

    String name,email,Address,Gender,imageString,token,ProfileImage,mobile,userType,userID;

    EditText userName,userEmail,userAddress;
    Button profile_update;
    ProgressBar progress_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_service_taker_profile_update );

        progress_bar = findViewById( R.id.progress_bar );

        userName = findViewById( R.id.userName );
        userEmail = findViewById( R.id.userEmail );
        userAddress = findViewById( R.id.userAddress );

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
        Log.e( TAG, "Gender: "+Gender  );

        userName.setText( name );
        userEmail.setText( email );
        userAddress.setText( Address );

        profile_image =  findViewById( R.id.profile_image );
        profile_update = findViewById( R.id.profile_update );


        profile_update.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                profile_update.setVisibility( View.GONE );
                progress_bar.setVisibility( View.VISIBLE );

                name = userName.getText().toString();
                email = userEmail.getText().toString();
                Address = userAddress.getText().toString();


                Call <ServiceTakerProfileUpdateResponce> call = RetrofitClient.getInstance(token).getApiInterface().serviceTakerProfileUpdateResponce(name,email,Gender,Address,imageString);



                call.enqueue(new Callback <ServiceTakerProfileUpdateResponce>() {
                    @Override
                    public void onResponse(Call<ServiceTakerProfileUpdateResponce> call, Response <ServiceTakerProfileUpdateResponce> response) {
                        if (response.isSuccessful()) {

                            ServiceTakerProfileUpdateResponce serviceTakerProfileUpdateResponce = response.body();
                            if (serviceTakerProfileUpdateResponce != null && serviceTakerProfileUpdateResponce.getStatus() == 1) {


//                                Log.e(TAG, "Name: " + name);
//                                Log.e(TAG, "Email: " + email);
//                                Log.e(TAG, "Address: " + Address);
//                                Toast.makeText(ServiceTakerProfileUpdate.this, "Profile Update", Toast.LENGTH_SHORT).show();
//
//                                ProfileImage = serviceTakerProfileUpdateResponce.getUser().getUserProfilePhoto().toString();
//
//                                SessionData sessionData = new SessionData();
//                                sessionData.setUser_id( userID );
//                                sessionData.setName( name );
//                                sessionData.setEmail( email );
//                                sessionData.setAddress( Address );
//                                sessionData.setToken( token );
//                                sessionData.setMobile( mobile );
//                                sessionData.setGender( Gender );
//                                sessionData.setUser_type( userType );
//                                sessionData.setUser_profile_photo( ProfileImage );
//
//                                //SessionData.(getApplicationContext()).userLogin(sessionData);
//                                SessionClass.getInstance(getApplicationContext()).userLogin(sessionData);
//
//                                Log.e( TAG, "Data: "+name+"\n"+email+"\n"+Gender+"\n"+Address+"\n"+token+"\n"+imageString  );
//
//                                Intent intent = new Intent( ServiceTakerProfileUpdate.this,MainActivity.class );
//                                startActivity( intent );

                                SessionClass.getInstance(getApplicationContext()).logout();
                                showDialog();

                                profile_update.setVisibility( View.VISIBLE );
                                progress_bar.setVisibility( View.GONE );


                            } else {
                                Toast.makeText(ServiceTakerProfileUpdate.this, "Error", Toast.LENGTH_SHORT).show();

                                profile_update.setVisibility( View.VISIBLE );
                                progress_bar.setVisibility( View.GONE );
                            }
                        } else {
                            Toast.makeText(ServiceTakerProfileUpdate.this, "", Toast.LENGTH_SHORT).show();

                            profile_update.setVisibility( View.VISIBLE );
                            progress_bar.setVisibility( View.GONE );
                        }
                    }

                    @Override
                    public void onFailure(Call<ServiceTakerProfileUpdateResponce> call, Throwable t) {


                        profile_update.setVisibility( View.VISIBLE );
                        progress_bar.setVisibility( View.GONE );

                        Log.e( TAG, "onFailure: "+ t.getLocalizedMessage() );
                    }
                });
            }
        } );

        profile_image.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseFile();
            }
        } );


    }


    private void chooseFile(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction( Intent.ACTION_GET_CONTENT);
        startActivityForResult( Intent.createChooser(intent, "Select Picture"), 1);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {

                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                profile_image.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }

            imageString = getStringImage(bitmap);

            Log.e( TAG, "Image_sTRING: "+ imageString );

        }
    }

    public String getStringImage(Bitmap bitmap){

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress( Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

        byte[] imageByteArray = byteArrayOutputStream.toByteArray();
        encodedImage = Base64.encodeToString(imageByteArray, Base64.DEFAULT);

        return encodedImage;
    }

    void showDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this, R.style.CustomAlertDialog);
        LayoutInflater inflater = LayoutInflater.from(this);
        final View viewLayout = inflater.inflate(R.layout.profile_update_alart_dialog , null);
        dialog.setView(viewLayout);
        Button ok = (Button) viewLayout.findViewById(R.id.logok);
        final AlertDialog  alertDialog=dialog.create();
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( ServiceTakerProfileUpdate.this, LoginActivity.class );
                startActivity( intent );
                finish();
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }


}
