package com.mdrayefenam.karigorbangla.ServiceTaker.Fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mdrayefenam.karigorbangla.R;
import com.mdrayefenam.karigorbangla.ServiceTaker.Activity.ServiceTakerLocation;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {


    CardView handyman,cleaner,vehicle_services,electrecian,vehicle_services_one,mechanics;


    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.fragment_home, container, false );

        handyman = view.findViewById( R.id.handyman );
        cleaner = view.findViewById( R.id.cleaner );
        vehicle_services = view.findViewById( R.id.vehicle_services );
        electrecian = view.findViewById( R.id.electrecian );
        vehicle_services_one = view.findViewById( R.id.vehicle_services_one );
        mechanics = view.findViewById( R.id.mechanics );




        handyman.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getActivity(), ServiceTakerLocation.class );
                intent.putExtra( "category","Handyman" );
                startActivity( intent );
                getActivity().finish();


            }
        } );


        cleaner.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getActivity(), ServiceTakerLocation.class );
                intent.putExtra( "category","Handyman" );
                startActivity( intent );
                getActivity().finish();
            }
        } );

        vehicle_services.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getActivity(), ServiceTakerLocation.class );
                intent.putExtra( "category","Handyman" );
                startActivity( intent );
                getActivity().finish();
            }
        } );

        electrecian.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getActivity(), ServiceTakerLocation.class );
                intent.putExtra( "category","Handyman" );
                startActivity( intent );
                getActivity().finish();
            }
        } );

        vehicle_services_one.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getActivity(), ServiceTakerLocation.class );
                intent.putExtra( "category","Handyman" );
                startActivity( intent );
                getActivity().finish();
            }
        } );

        mechanics.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getActivity(), ServiceTakerLocation.class );
                intent.putExtra( "category","Handyman" );
                startActivity( intent );
                getActivity().finish();
            }
        } );

        return view;
    }

}
