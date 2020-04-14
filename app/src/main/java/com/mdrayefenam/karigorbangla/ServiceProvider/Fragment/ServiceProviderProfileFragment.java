package com.mdrayefenam.karigorbangla.ServiceProvider.Fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.tabs.TabLayout;
import com.mdrayefenam.karigorbangla.R;
import com.mdrayefenam.karigorbangla.ServiceProvider.Activity.ServiceProviderProfileUpdate;
import com.mdrayefenam.karigorbangla.ServiceTaker.Activity.ServiceTakerProfileUpdate;

import androidx.fragment.app.Fragment;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServiceProviderProfileFragment extends Fragment {

    TextView userName,userEmail,userAddress,userNumber;
    CircleImageView profile_image;
    Button update_profile;

    TabLayout tabLayout;

    String name,email,mobile,address,profileImage,mProfilteImage,token;
    String ID,UserType;
    private Context context;

    String TAG="ServiceTakerProfileFragment ";

    private static final String SHARED_PREF_NAME = "simplifiedcodingsharedpref";
    private static final String KEY_ID = "keyid";
    private static final String KEY_NAME = "keyname";
    private static final String KEY_EMAIL = "keyemail";
    private static final String KEY_MOBILE = "keymobile";
    private static final String KEY_ADDRESS = "keyaddress";
    private static final String KEY_GENDER = "keygender";
    private static final String KEY_USER_TYPE = "keyusertype";
    private static final String KEY_PROFILE_IMAGE = "keyeprofileimage";
    private static final String KEY_CATEGORY = "keycategory";
    private static final String KEY_RATE = "keyrate";
    private static final String KEY_TOKEN = "keytoken";


    public ServiceProviderProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        this.context=context;
        super.onAttach(context);
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        token = sharedPreferences.getString(KEY_TOKEN, "");
        name = sharedPreferences.getString( KEY_NAME,"" );
        email = sharedPreferences.getString( KEY_EMAIL,"" );
        address = sharedPreferences.getString( KEY_ADDRESS,"" );
        mobile = sharedPreferences.getString( KEY_MOBILE,"" );
        profileImage = sharedPreferences.getString( KEY_PROFILE_IMAGE,"" );
        UserType =  sharedPreferences.getString( KEY_USER_TYPE,"" );
        ID = sharedPreferences.getString( KEY_ID,"" );

        Log.e( TAG, "ID: "+ID  );

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.fragment_service_taker_profile, container, false );

        userName =(TextView)view.findViewById(R.id.userName);
        userEmail =(TextView)view.findViewById(R.id.userEmail);
        userAddress =(TextView)view.findViewById(R.id.userAddress);
        userNumber = (TextView)view.findViewById(R.id.userNumber);
        profile_image =(CircleImageView) view.findViewById(R.id.profile_image);

        update_profile = (Button) view.findViewById(R.id.update_profile);

        userName.setText( name );
        userEmail.setText( email );
        userAddress.setText( address );
        userNumber.setText( mobile );

        mProfilteImage = "https://karigor.againwish.com/"+profileImage;

        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.profile_user_icon).error(R.drawable.profile_user_icon);
        Glide.with(getActivity()).load(mProfilteImage).apply(requestOptions).into(profile_image);

        update_profile.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getContext(), ServiceProviderProfileUpdate.class );
                intent.putExtra( "Id",ID );
                intent.putExtra( "Mobile",mobile );
                intent.putExtra( "USER_TYPE",UserType );

                startActivity( intent );
            }
        } );

        return view;
    }

}
