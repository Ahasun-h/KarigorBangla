package com.mdrayefenam.karigorbangla.ServiceProvider.Fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mdrayefenam.karigorbangla.Network.RetrofitClient;
import com.mdrayefenam.karigorbangla.R;
import com.mdrayefenam.karigorbangla.Responce.NotificationModel.Notification;
import com.mdrayefenam.karigorbangla.Responce.NotificationModel.NotificationResponce;
import com.mdrayefenam.karigorbangla.ServiceTaker.Adapter.NotificationAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServiceProviderNotification extends Fragment {

    RecyclerView notification_recyler_view;


    NotificationAdapter notificationAdapter;

    LinearLayout loding;

    private List <Notification> postList = new ArrayList <>();
    private NotificationResponce notificationResponce;

    String TAG="NotificationFragment ";
    private Context context;
    private Context mCobtext;
    private static final String SHARED_PREF_NAME = "simplifiedcodingsharedpref";
    private static final String KEY_TOKEN = "keytoken";
    String token;
    // String TAG="AddPostFragment ";




    @Override
    public void onAttach(Context context) {
        this.context=context;
        super.onAttach(context);
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        token = sharedPreferences.getString(KEY_TOKEN, "");
        Log.e( TAG, "onAttach: "+token  );

    }


    public ServiceProviderNotification() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.fragment_service_provider_notification, container, false );

        notification_recyler_view = view.findViewById(R.id.notification_recyler_view);
        notification_recyler_view.setVisibility( View.GONE );

        loding = view.findViewById( R.id.loding );
        loding.setVisibility( View.VISIBLE );

        notificationAdapter = new NotificationAdapter(getContext(),postList);
        notification_recyler_view.setLayoutManager(new LinearLayoutManager(getContext()));
        notification_recyler_view.setAdapter(notificationAdapter);


        fetchNotification();

        return view;
    }

    private void fetchNotification() {

        Log.e(TAG, "onAttach: "+token );

        Call <NotificationResponce> call = RetrofitClient.getInstance( token ).getApiInterface().notificationData();
        call.enqueue( new Callback <NotificationResponce>() {
            @Override
            public void onResponse(Call<NotificationResponce> call, Response <NotificationResponce> response) {
                notificationResponce = response.body();

                Log.e( TAG, "onResponseBB: "+response.body().toString()  );
                if (response.isSuccessful())
                {
                    postList = notificationResponce.getNotification();

                    Log.e( TAG, "onResponseAA: "+notificationResponce.getNotification().size()  );

                    notificationAdapter.updateList( postList );
                    loding.setVisibility( View.GONE );
                    notification_recyler_view.setVisibility( View.VISIBLE );

                }else
                {
                    Toast.makeText( getContext(), "Fail", Toast.LENGTH_SHORT ).show();
                }
            }

            @Override
            public void onFailure(Call<NotificationResponce> call, Throwable t) {
                Toast.makeText( getContext(), "onFailure", Toast.LENGTH_SHORT ).show();
            }
        } );

    }

}
