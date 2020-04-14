package com.mdrayefenam.karigorbangla.ServiceTaker.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mdrayefenam.karigorbangla.R;

public class Test extends AppCompatActivity {

    Button notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_test );


        notification = findViewById( R.id.notification);

        notification.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NotificationCompat.Builder builder = new NotificationCompat.Builder( Test.this )
                        .setSmallIcon( R.drawable.bell )
                        .setContentTitle( "notification title" )
                        .setContentText( "This is a Test Notification" );




            }
        } );




    }
}
