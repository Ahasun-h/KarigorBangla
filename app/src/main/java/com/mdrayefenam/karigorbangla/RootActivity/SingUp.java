package com.mdrayefenam.karigorbangla.RootActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.mdrayefenam.karigorbangla.Model.SessionData;
import com.mdrayefenam.karigorbangla.Responce.SignUpModel.SinUpResponce;
import com.mdrayefenam.karigorbangla.ServiceProvider.Activity.ServiceProbiderCatagorySelect;
import com.mdrayefenam.karigorbangla.R;
import com.mdrayefenam.karigorbangla.Network.RetrofitClient;
import com.mdrayefenam.karigorbangla.ServiceProvider.Activity.ServiceProviderHome;
import com.mdrayefenam.karigorbangla.ServiceTaker.Activity.MainActivity;
import com.mdrayefenam.karigorbangla.SessionClass.SessionClass;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingUp extends AppCompatActivity {

    Spinner spinner1;

    ImageView profile_image;
    private Bitmap bitmap;
    String encodedImage;

    String name,email,mobile,Gender,Address,password,userType,imageString;

    RadioGroup RG;
    int genderIcrement;
    int imageInt = 0;


    EditText userName,userEmail,Password,confirm_password,userAddress;
    CheckBox check_box;
    Button sign_up;
    private String TAG = "RegisterActivity ";
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.sign_up);

        userName = findViewById( R.id.userName );
        userEmail = findViewById( R.id.userEmail );
        Password = findViewById( R.id.password );
        confirm_password = findViewById( R.id.confirm_password );
        userAddress = findViewById( R.id.userAddress );

        spinner1 = findViewById( R.id.spinner1 );

        profile_image =  findViewById( R.id.profile_image );

        RG = findViewById(R.id.RG);

        ArrayAdapter <String> userTypeAdapter = new ArrayAdapter<String>(SingUp.this,android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.user_type));
        userTypeAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner1.setAdapter(userTypeAdapter);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                userType = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        genderIcrement = 1;

//        RG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                switch (checkedId)
//                {
//                    case 1:
//                        if (genderIcrement<2)
//                        {
//                            Gender = "Male";
//                        }
//                        genderIcrement++;
//                        break;
//
//                    case 2:
//                        if (genderIcrement<2)
//                        {
//                            Gender = "Female";
//                        }
//                        genderIcrement++;
//                        break;
//
//                }
//
//
//            }
//
//        });

        RG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Toast.makeText(getBaseContext(), Gender, Toast.LENGTH_SHORT).show();
            }
        });

        profile_image.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseFile();
            }
        } );



        check_box = findViewById( R.id.check_box );

       sign_up = findViewById( R.id.sign_up );

        progressBar = findViewById( R.id.progressBar );

        mobile = getIntent().getStringExtra("number");

        sign_up.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 name = userName.getText().toString();
                 email = userEmail.getText().toString();
                 password = Password.getText().toString();
                String ConfirmPassword = confirm_password.getText().toString();
                Address = userAddress.getText().toString();

                Gender = ((RadioButton)findViewById(RG.getCheckedRadioButtonId())).getText().toString();

                sign_up.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);

                if (name.equals( "" ) ){
                    userName.setError( "Enter Your Name" );
                    sign_up.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }else if (email.equals( "" ) ){
                    userEmail.setError( "Enter Your Email" );
                    sign_up.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }else if (password.equals( "" )){
                    Password.setError( "Enter Your password" );
                    sign_up.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }else if (ConfirmPassword.equals( "" )){
                    confirm_password.setError( "Enter Your password" );
                    sign_up.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }
//                else if (!Gender.equals( "Male") || !Gender.equals( "Female" )){
//                    Toast.makeText( SingUp.this, "Please! Select Your Gender.", Toast.LENGTH_SHORT ).show();
//                    sign_up.setVisibility(View.VISIBLE);
//                    progressBar.setVisibility(View.GONE);
//                }
                else if (userType.equals(null) || userType.equals( "Select your account type" )){
                    Toast.makeText( SingUp.this, "Please! Select Your User Type.", Toast.LENGTH_SHORT ).show();
                    sign_up.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                } else if (imageInt == 0){
                    Toast.makeText( SingUp.this, "Please! Select Your Profile image.", Toast.LENGTH_SHORT ).show();
                    sign_up.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }
                else if (!password.equals(ConfirmPassword)){
                    Toast.makeText( SingUp.this, "Password not match!", Toast.LENGTH_SHORT ).show();
                    sign_up.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }else if(!check_box.isChecked())//checked then
                {
                    check_box.setError( "Please Confirm Our terms and condition." );
                    sign_up.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }else {

                   /* Intent intent = new Intent( SingUp.this, MainActivity.class );
                    startActivity( intent );
                    finish(); */

                   doRegister();

//                    Log.e( TAG, "Data: "+name+" "+email+" "+mobile+" "+Gender+" "+password+" "+userType+" "+imageString );
                }


            }
        } );






    }

    private void doRegister() {

        Call<SinUpResponce> call = RetrofitClient.getInstance().getApiInterface().userRegister( name,email,mobile,Gender,Address,password,userType,imageString );

        Log.e( TAG, "Data: "+name+" "+email+" "+mobile+" "+Gender+" "+Address+" "+password+" "+userType+" "+imageString );

        call.enqueue( new Callback <SinUpResponce>() {
            @Override
            public void onResponse(Call <SinUpResponce> call, Response <SinUpResponce> response) {



                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.code());
                    SinUpResponce sinUpResponce = response.body();
                    if (sinUpResponce != null && sinUpResponce.getStatus() == 1) {
                        Log.e(TAG, "onResponse: " + sinUpResponce.getToken().getOriginal().getAccessToken());
                        Log.e(TAG, "onResponse: " + sinUpResponce.getToken().getOriginal().getAccessToken().length());

                        imageInt = 0;
                        int id = sinUpResponce.getUserId();
                        String ID = String.valueOf(id);
                        sign_up.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);

                        if(!userType.equals("User"))
                        {
                           Intent intent = new Intent( SingUp.this, ServiceProbiderCatagorySelect.class );
                           intent.putExtra("id", ID);
                           intent.putExtra("name", sinUpResponce.getUserInformation().getName());
                           intent.putExtra("email", sinUpResponce.getUserInformation().getEmail());
                           intent.putExtra("mobile", sinUpResponce.getUserInformation().getMobile());
                           intent.putExtra("address", sinUpResponce.getUserInformation().getAddress());
                           intent.putExtra("gender", sinUpResponce.getUserInformation().getGender());
                           intent.putExtra("user_profile_photo", sinUpResponce.getUserInformation().getUserProfilePhoto());
                           intent.putExtra("user_type", sinUpResponce.getUserInformation().getUserType());
                           intent.putExtra("token",sinUpResponce.getToken().getOriginal().getAccessToken());
                           startActivity( intent );
                            finish();
                        }else {

                            SessionData sessionData = new SessionData();
                            sessionData.setId( ID );
                            sessionData.setName( sinUpResponce.getUserInformation().getName() );
                            sessionData.setEmail( sinUpResponce.getUserInformation().getEmail() );
                            sessionData.setMobile( sinUpResponce.getUserInformation().getMobile() );
                            sessionData.setAddress( sinUpResponce.getUserInformation().getAddress() );
                            sessionData.setGender( sinUpResponce.getUserInformation().getGender() );
                            sessionData.setUser_profile_photo( sinUpResponce.getUserInformation().getUserProfilePhoto() );
                            sessionData.setUser_type( sinUpResponce.getUserInformation().getUserType() );
                            sessionData.setToken( sinUpResponce.getToken().getOriginal().getAccessToken() );
                            SessionClass.getInstance(getApplicationContext()).userLogin(sessionData);

                            Intent intent = new Intent( SingUp.this, MainActivity.class );
                            startActivity( intent );
                            finish();


                        }

                        Toast.makeText(SingUp.this, sinUpResponce.getUserId().toString(), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(SingUp.this, sinUpResponce.getStatus(), Toast.LENGTH_LONG).show();
                        sign_up.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                    }
                }
                else {
                    Log.d(TAG, "onResponse: " + response.code());
                    sign_up.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call <SinUpResponce> call, Throwable t) {
                showDialog();
                Log.e( TAG, "onFailure: "+t.getLocalizedMessage() );
                Toast.makeText(SingUp.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                sign_up.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
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
            imageInt = 1;
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
        final View viewLayout = inflater.inflate(R.layout.sign_up_alart_dialog , null);
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
