package com.mdrayefenam.karigorbangla.ServiceProvider.Fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mdrayefenam.karigorbangla.Network.RetrofitClient;
import com.mdrayefenam.karigorbangla.R;
import com.mdrayefenam.karigorbangla.ServiceProvider.Adapter.JobOfferRecylerViewAdapter;
import com.mdrayefenam.karigorbangla.ServiceProvider.Adapter.ProviderJobListAdapter;
import com.mdrayefenam.karigorbangla.ServiceProvider.ProviderConfirmJobListModel.ProviderConfirmJobListResponce;
import com.mdrayefenam.karigorbangla.ServiceProvider.ProviderConfirmJobListModel.ServiceConfirmDatum;
import com.mdrayefenam.karigorbangla.ServiceProvider.ServiceTakerJobOfferModel.MyInterest;
import com.mdrayefenam.karigorbangla.ServiceProvider.ServiceTakerJobOfferModel.ServiceProviderJobOfferResponce;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProviderJobList extends Fragment {


    private static final String SHARED_PREF_NAME = "simplifiedcodingsharedpref";
    private static final String KEY_ID = "keyid";
    private static final String KEY_TOKEN = "keytoken";
    private Context context;

    String TAG = "ProviderRecyllerView";

    RecyclerView AdRecylerView;

    ProviderJobListAdapter providerJobListAdapter;
    LinearLayout loding;
    ProgressBar progress_bar_pagination;
    EditText search;

    private List <ServiceConfirmDatum> myInterests = new ArrayList <>(  );

    LinearLayoutManager linearLayoutManager;

    ProviderConfirmJobListResponce providerConfirmJobListResponce;

    String token,id;

    ImageView no_result;


    @Override
    public void onAttach(Context context) {
        this.context=context;
        super.onAttach(context);
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        token = sharedPreferences.getString(KEY_TOKEN, "");
        id = sharedPreferences.getString(KEY_ID,"");
        Log.e(TAG, "MyPostID: "+token );

    }


    public ProviderJobList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.fragment_provider_job_list, container, false );

        linearLayoutManager = new LinearLayoutManager( getContext(), LinearLayoutManager.VERTICAL, false);

        progress_bar_pagination = view.findViewById(R.id.progress_bar_pagination);

        loding = view.findViewById(R.id.loding);
        loding.setVisibility( View.VISIBLE );

        no_result = view.findViewById( R.id.no_result );
        no_result.setVisibility( View.GONE );

        AdRecylerView = view.findViewById(R.id.ad_recyler_view);

        providerJobListAdapter = new ProviderJobListAdapter(getContext(),myInterests);
//        AdRecylerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        AdRecylerView.setAdapter(adPostRecylerViewAdapter);

        AdRecylerView.setLayoutManager( linearLayoutManager );
        AdRecylerView.setItemAnimator(new DefaultItemAnimator());
        AdRecylerView.setAdapter(providerJobListAdapter);

        fetchJobOffer();



        return view;
    }


    private void fetchJobOffer() {

        Call <ProviderConfirmJobListResponce> call = RetrofitClient.getInstance( token ).getApiInterface().providerConfirmJobListResponce(id);
        call.enqueue( new Callback <ProviderConfirmJobListResponce>() {
            @Override
            public void onResponse(Call<ProviderConfirmJobListResponce> call, Response <ProviderConfirmJobListResponce> response) {
                providerConfirmJobListResponce = response.body();

                Log.e( TAG, "onResponseBB: "+response.body());
                if (response.isSuccessful())
                {
                    myInterests = providerConfirmJobListResponce.getServiceConfirmData();

                    Log.e( TAG, "onResponseAA: "+response.body().toString()  );
                    if (providerConfirmJobListResponce.getServiceConfirmData().size() != 0){
                        providerJobListAdapter.updateList( myInterests );
                        loding.setVisibility( View.GONE );
                        AdRecylerView.setVisibility( View.VISIBLE );
                    }
                    else {
                        no_result.setVisibility( View.VISIBLE );
                        loding.setVisibility( View.GONE );
                        Log.e( TAG,"no_result : Its Work" );
                    }


                }else
                {
                    Toast.makeText( getContext(), "Fail", Toast.LENGTH_SHORT ).show();
                }
            }

            @Override
            public void onFailure(Call<ProviderConfirmJobListResponce> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.e( TAG, "onFailure: "+ t.getLocalizedMessage() );
            }
        } );

    }

}
