package com.mdrayefenam.karigorbangla.ServiceTaker.Fragment;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.android.material.tabs.TabLayout;
import com.mdrayefenam.karigorbangla.R;
import com.mdrayefenam.karigorbangla.ServiceProvider.Fragment.JobOffer;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFeed extends Fragment {

    TabLayout tabLayout;
    FrameLayout viewPager;


    public NewsFeed() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.fragment_news_feed, container, false );

        tabLayout = (TabLayout)view.findViewById(R.id.tabLayout);
        viewPager = (FrameLayout)view.findViewById(R.id.viewPager);

        ((AppCompatActivity) getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.viewPager, new All()).commit();


        TabLayout.Tab all = tabLayout.newTab();
        all.setText("Pending Job Offer");
        tabLayout.addTab(all);

        TabLayout.Tab featured = tabLayout.newTab();
        featured.setText("Confirm Job List");
        tabLayout.addTab(featured);

        TabLayout.Tab favorite = tabLayout.newTab();
        favorite.setText("Provider Arrived");
        tabLayout.addTab(favorite);

        TabLayout.Tab ServiceTakerJobHistory = tabLayout.newTab();
        ServiceTakerJobHistory.setText("Job History");
        tabLayout.addTab(ServiceTakerJobHistory);



// perform setOnTabSelectedListener event on TabLayout
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
// get the current selected tab's position and replace the fragment accordingly
                Fragment fragment = null;
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new All();
                        break;
                    case 1:
                        fragment = new Featured();
                        break;

                    case 2:
                        fragment = new Favorite();
                        break;

                    case 3:
                        fragment = new ServiceTakerJobHistory();
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
