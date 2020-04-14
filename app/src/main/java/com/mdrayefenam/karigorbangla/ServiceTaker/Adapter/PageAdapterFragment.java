package com.mdrayefenam.karigorbangla.ServiceTaker.Adapter;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PageAdapterFragment extends FragmentStatePagerAdapter {
    int mTab;
    private Context myContext;
    int totalTabs;
    private final List <Fragment> fragmentList = new ArrayList <>(  );
    private final List<String> FragmentListTitle = new ArrayList <>(  );
    public PageAdapterFragment(Context context, FragmentManager fm, int Tabs) {
        super(fm);
        this.mTab = Tabs;
        myContext = context;
        this.totalTabs = totalTabs;
    }

    @Override
    public Fragment getItem(int position) {

        return fragmentList.get( position );
    }

    @Override
    public int getCount() {
        return totalTabs;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return FragmentListTitle.get( position );
    }

    public void  AddFragment(Fragment fragment, String Title){
        fragmentList.add( fragment );
        FragmentListTitle.add( Title );
    }


}
