package com.mdrayefenam.karigorbangla.ServiceProvider.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.android.material.tabs.TabLayout;
import com.mdrayefenam.karigorbangla.R;
import com.mdrayefenam.karigorbangla.ServiceProvider.Activity.ServiceProviderHome;
import com.mdrayefenam.karigorbangla.ServiceTaker.Fragment.All;
import com.mdrayefenam.karigorbangla.ServiceTaker.Fragment.Favorite;
import com.mdrayefenam.karigorbangla.ServiceTaker.Fragment.Featured;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServiceProviderSearch extends Fragment {

    TabLayout tabLayout;
    FrameLayout viewPager;


    public ServiceProviderSearch() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.fragment_service_provider_search, container, false );

        tabLayout = (TabLayout)view.findViewById(R.id.tabLayout);
        viewPager = (FrameLayout)view.findViewById(R.id.viewPager);

        TabLayout.Tab job_offer = tabLayout.newTab();
        job_offer.setText("Job Offer");
        tabLayout.addTab(job_offer);

        TabLayout.Tab featured = tabLayout.newTab();
        featured.setText("Job List");
        tabLayout.addTab(featured);

        ((AppCompatActivity) getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.viewPager, new JobOffer()).commit();

// perform setOnTabSelectedListener event on TabLayout
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
// get the current selected tab's position and replace the fragment accordingly
                Fragment fragment = null;
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new JobOffer();
                        break;
                    case 1:
                        fragment = new ProviderJobList();
                        break;

                }
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.viewPager, fragment);
                ft.setTransition( FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }


        });




        return view;

    }

}
