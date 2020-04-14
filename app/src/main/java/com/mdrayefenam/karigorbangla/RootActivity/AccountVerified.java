package com.mdrayefenam.karigorbangla.RootActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mdrayefenam.karigorbangla.R;
import com.mdrayefenam.karigorbangla.ServiceTaker.Activity.MainActivity;

public class AccountVerified extends AppCompatActivity {

    Button done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.account_verified );

        done = findViewById( R.id.done );

        done.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( AccountVerified.this, MainActivity.class );
                startActivity( intent );
            }
        } );
    }
}
