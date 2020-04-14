package com.mdrayefenam.karigorbangla.RootActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.mdrayefenam.karigorbangla.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class NumberAuthenticationCode extends AppCompatActivity {

    EditText code;
    Button submit;
    String number;
    TextView mOtpFeedback;

    private String mAuthVerificationId;

    ProgressBar progress_bar;


    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.number_authentication_code );


        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();

        number = getIntent().getStringExtra("number");
        mAuthVerificationId = getIntent().getStringExtra("AuthCredentials");

        code = findViewById(R.id.code);
        submit = findViewById(R.id.submit);
        progress_bar = findViewById(R.id.progress_bar);
        progress_bar.setVisibility( View.GONE );



        mOtpFeedback = findViewById(R.id.otp_form_feedback);

        submit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otp = code.getText().toString();


                if (otp.equals( "" ))
                {
                    code.setError( "Enter Your 6 digit Code" );
                }
//                else if(otp.equals( "123456" )){
//                    Intent intent = new Intent( NumberAuthenticationCode.this,SingUp.class );
//                    intent.putExtra("number",number);
//                    startActivity( intent );
//                    finish();
//                }
                else {
//                    Toast.makeText( NumberAuthenticationCode.this, "invalid Code!", Toast.LENGTH_SHORT ).show();


                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mAuthVerificationId, otp);
                    signInWithPhoneAuthCredential(credential);
                }
            }
        } );
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(NumberAuthenticationCode.this, new OnCompleteListener <AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task <AuthResult> task) {
                        if (task.isSuccessful()) {
                            sendUserToHome();
                        } else {
                            Toast.makeText( NumberAuthenticationCode.this, "Enter Wrong Code!", Toast.LENGTH_SHORT ).show();
                            Intent intent = new Intent( NumberAuthenticationCode.this,NumberAuthentication.class );
                            startActivity( intent );
                            finish();
                        }
//                        progress_bar.setVisibility(View.GONE);
//                        submit.setVisibility(View.VISIBLE);
                    }
                });
    }


//    @Override
//    protected void onStart() {
//        super.onStart();
//        if(mCurrentUser != null){
//            sendUserToHome();
//        }
//    }

    public void sendUserToHome() {
        Intent homeIntent = new Intent(NumberAuthenticationCode.this, SingUp.class);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        homeIntent.putExtra("number",number);
        startActivity(homeIntent);
        finish();
    }



}
