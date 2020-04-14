package com.mdrayefenam.karigorbangla.ServiceTaker.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.mdrayefenam.karigorbangla.RootActivity.LoginActivity;
import com.mdrayefenam.karigorbangla.ServiceTaker.Fragment.Home;
import com.mdrayefenam.karigorbangla.ServiceTaker.Fragment.NewsFeed;
import com.mdrayefenam.karigorbangla.ServiceTaker.Fragment.Search;
import com.mdrayefenam.karigorbangla.R;
import com.mdrayefenam.karigorbangla.ServiceTaker.Fragment.ServiceTakerProfileFragment;
import com.mdrayefenam.karigorbangla.SessionClass.SessionClass;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends AppCompatActivity {

    private static final String SHARED_PREF_NAME = "simplifiedcodingsharedpref";
    private static final String KEY_ID = "keyid";
    private static final String KEY_TOKEN = "keytoken";


    final String TAG = "MainActivity";

    BottomNavigationView bottomNavigationView;
    FrameLayout fragmentContiner;

    private Home home;
    private NewsFeed newsFeed;
    private Search search;

    private Menu action;

    String mtoken,userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setIcon(R.drawable.main_logo);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        userID = sharedPreferences.getString(KEY_ID, "");
        mtoken = sharedPreferences.getString(KEY_TOKEN, "");



        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        fragmentContiner = findViewById(R.id.fragmentContiner);

        home = new Home();
        newsFeed = new NewsFeed();
        search = new Search();

        ((AppCompatActivity) MainActivity.this).getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContiner, new Home()).commit();





        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home_item:
                        InitialFragment(home);
                        return true;

                    case R.id.news_feed_item:
                        InitialFragment(newsFeed);
                        return true;

                    case R.id.search_item:
                        InitialFragment(search);
                        return true;

                }
             return false;

            }
        });




    }





   public void  InitialFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContiner,fragment);
        fragmentTransaction.commit();
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        action = menu;

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.menu_logout:
                SessionClass.getInstance(getApplicationContext()).logout();
                Intent intent = new Intent( MainActivity.this, LoginActivity.class );
                startActivity( intent );
                finish();
                return true;

            case R.id.menu_profile:
                ((AppCompatActivity) MainActivity.this).getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContiner, new ServiceTakerProfileFragment()).commit();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }


}
