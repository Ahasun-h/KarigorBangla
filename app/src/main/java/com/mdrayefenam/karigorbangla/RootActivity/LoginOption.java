package com.mdrayefenam.karigorbangla.RootActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mdrayefenam.karigorbangla.R;

public class LoginOption extends AppCompatActivity {


    Button buttonProvider,buttonTaker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.login_option );


        buttonProvider = findViewById( R.id.buttonProvider );
        buttonTaker = findViewById( R.id.buttonTaker );


        buttonProvider.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        } );


        buttonTaker.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( LoginOption.this, LoginActivity.class );
                startActivity( intent );
            }
        } );


    }
}
