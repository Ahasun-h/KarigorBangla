package com.mdrayefenam.karigorbangla.RootActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.mdrayefenam.karigorbangla.R;

import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class NumberAuthentication extends AppCompatActivity {

    EditText number;
    TextView mLoginFeedbackText;
    ImageButton next;
    ProgressBar progress_bar;

    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;

    String complete_phone_number;

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.number_authentication );

        number = findViewById(R.id.number);

        next = findViewById(R.id.next);

        mLoginFeedbackText = findViewById(R.id.login_form_feedback);

        progress_bar = findViewById(R.id.progress_bar);

        next.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                next.setVisibility( View.GONE );
                progress_bar.setVisibility( View.VISIBLE );

                String phone_number = number.getText().toString();
                String country_code = "+88";
                complete_phone_number =country_code + phone_number;

                if (phone_number.equals( "" ))
                {
                    number.setError( "Enter Your Phone Number" );

                    next.setVisibility( View.VISIBLE );
                    progress_bar.setVisibility( View.GONE );
                }
                else if(phone_number.length() != 11 ){

                    number.setError( "Enter Valid Phon1e number" );
                    Toast.makeText( NumberAuthentication.this, "invalid phone number!", Toast.LENGTH_SHORT ).show();
                    next.setVisibility( View.VISIBLE );
                    progress_bar.setVisibility( View.GONE );
                }
                else {
//                    Intent intent = new Intent( NumberAuthentication.this,NumberAuthenticationCode.class );
//                    intent.putExtra("number",phone_number);
//                    startActivity( intent );
//                    finish();

                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            complete_phone_number,
                            120,
                            TimeUnit.SECONDS,
                            NumberAuthentication.this,
                            mCallbacks
                    );
                }
            }
        } );


        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                mLoginFeedbackText.setText("Verification Failed, please try again.");
                mLoginFeedbackText.setVisibility(View.VISIBLE);
                next.setVisibility( View.VISIBLE );
                progress_bar.setVisibility(View.GONE);
                Log.e("", "onVerificationFailed: "+e.getLocalizedMessage() );
            }


            @Override
            public void onCodeSent(final String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);

                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {

                                next.setVisibility( View.VISIBLE );
                                progress_bar.setVisibility( View.GONE );

                                Intent otpIntent = new Intent(NumberAuthentication.this, NumberAuthenticationCode.class);
                                otpIntent.putExtra("AuthCredentials", s);
                                otpIntent.putExtra("number", complete_phone_number);
                                startActivity(otpIntent);
                                finish();
                            }
                        },
                        10000);
            }
        };

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mCurrentUser != null){
            sendUserToHome();
        }
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(NumberAuthentication.this, new OnCompleteListener <AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task <AuthResult> task) {
                        if (task.isSuccessful()) {
                            sendUserToHome();
                            // ...
                        } else {

                        }
                        progress_bar.setVisibility(View.GONE);
                        next.setVisibility(View.VISIBLE);
                    }
                });
    }

    private void sendUserToHome() {
        Intent homeIntent = new Intent(NumberAuthentication.this, NumberAuthenticationCode.class);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(homeIntent);
        finish();

    }


}
