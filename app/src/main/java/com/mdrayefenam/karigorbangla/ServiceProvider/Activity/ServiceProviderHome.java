package com.mdrayefenam.karigorbangla.ServiceProvider.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mdrayefenam.karigorbangla.R;
import com.mdrayefenam.karigorbangla.RootActivity.LoginActivity;
import com.mdrayefenam.karigorbangla.ServiceProvider.Fragment.ServiceProviderCategory;
import com.mdrayefenam.karigorbangla.ServiceProvider.Fragment.ServiceProviderNotification;
import com.mdrayefenam.karigorbangla.ServiceProvider.Fragment.ServiceProviderProfileFragment;
import com.mdrayefenam.karigorbangla.ServiceProvider.Fragment.ServiceProviderSearch;
import com.mdrayefenam.karigorbangla.ServiceTaker.Activity.MainActivity;
import com.mdrayefenam.karigorbangla.ServiceTaker.Fragment.Home;
import com.mdrayefenam.karigorbangla.ServiceTaker.Fragment.NewsFeed;
import com.mdrayefenam.karigorbangla.ServiceTaker.Fragment.Search;
import com.mdrayefenam.karigorbangla.ServiceTaker.Fragment.ServiceTakerProfileFragment;
import com.mdrayefenam.karigorbangla.SessionClass.SessionClass;

public class ServiceProviderHome extends AppCompatActivity {


    final String TAG = "MainActivity";

    BottomNavigationView bottomNavigationView;
    FrameLayout fragmentContiner;

    private ServiceProviderCategory serviceProviderCategory;
    private ServiceProviderSearch serviceProviderSearch;
    private ServiceProviderNotification serviceProviderNotification;
    private ServiceProviderProfileFragment serviceProviderProfileFragment;

    private Menu action;

    private static final String SHARED_PREF_NAME = "simplifiedcodingsharedpref";
    private static final String KEY_PROFILE_IMAGE = "keyeprofileimage";

    String ProfileImageURL;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.service_provider_home );


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setIcon(R.drawable.karigorbanglapng);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        ProfileImageURL = sharedPreferences.getString( KEY_PROFILE_IMAGE,"" );;




        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        fragmentContiner = findViewById(R.id.fragmentContiner);

        serviceProviderCategory = new ServiceProviderCategory();
        serviceProviderSearch = new ServiceProviderSearch();
        serviceProviderNotification = new ServiceProviderNotification();
        serviceProviderProfileFragment = new ServiceProviderProfileFragment();

        ((AppCompatActivity) ServiceProviderHome.this).getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContiner, new ServiceProviderCategory()).commit();


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home_item:
                        InitialFragment(serviceProviderCategory);
                        return true;

                    case R.id.news_feed_item:
                        InitialFragment(serviceProviderSearch);
                        return true;

                    case R.id.search_item:
                        InitialFragment(serviceProviderNotification);
                        return true;

                    case R.id.profile:
                        InitialFragment(serviceProviderProfileFragment);
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
        menuInflater.inflate(R.menu.service_provider_main_menu, menu);

        action = menu;

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.menu_logout:
                SessionClass.getInstance(getApplicationContext()).logout();
                Intent intent = new Intent( ServiceProviderHome.this, LoginActivity.class );
                startActivity( intent );
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

}
