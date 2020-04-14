package com.mdrayefenam.karigorbangla.ServiceProvider.Fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mdrayefenam.karigorbangla.Model.SessionData;
import com.mdrayefenam.karigorbangla.Network.ApiInterface;
import com.mdrayefenam.karigorbangla.Network.RetrofitClient;
import com.mdrayefenam.karigorbangla.R;
import com.mdrayefenam.karigorbangla.Responce.LoginModel.LoginResponce;
import com.mdrayefenam.karigorbangla.RootActivity.LoginActivity;
import com.mdrayefenam.karigorbangla.ServiceProvider.Activity.ServiceProviderHome;
import com.mdrayefenam.karigorbangla.ServiceProvider.Adapter.ImageSliderAdapter;
import com.mdrayefenam.karigorbangla.ServiceProvider.Adapter.ShowServiceProviderJobHistoryAdapter;
import com.mdrayefenam.karigorbangla.ServiceProvider.ImageSliderModel.ActiveImage;
import com.mdrayefenam.karigorbangla.ServiceProvider.ImageSliderModel.ImageSliderResponce;
import com.mdrayefenam.karigorbangla.ServiceProvider.ShowServiceProviderJobHistoryModel.ShowServiceProviderJobHistory;
import com.mdrayefenam.karigorbangla.ServiceProvider.ShowServiceProviderJobHistoryModel.ShowServiceProviderJobHistoryResponce;
import com.mdrayefenam.karigorbangla.ServiceTaker.Activity.MainActivity;
import com.mdrayefenam.karigorbangla.ServiceTaker.Adapter.ServiceProviderPendingJobOfferRecylerViewAdapter;
import com.mdrayefenam.karigorbangla.ServiceTaker.ServiceProviderPendingJobOfferModel.ShowServiceTakerPendingJobOffer;
import com.mdrayefenam.karigorbangla.ServiceTaker.ServiceProviderPendingJobOfferModel.ShowServiceTakerPendingJobOfferResmpoce;
import com.mdrayefenam.karigorbangla.SessionClass.SessionClass;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServiceProviderCategory extends Fragment {

     String TAG = "ServiceProviderCategory";

    private AdapterViewFlipper adapterViewFlipper;

    ImageSliderResponce imageSliderResponce;
    private List<ActiveImage> activeImages = new ArrayList<>();

    public static final String BASE_URL = "https://www.simplifiedcoding.net/demos/view-flipper/";

    private Context context;
    private static final String SHARED_PREF_NAME = "simplifiedcodingsharedpref";
    private static final String KEY_TOKEN = "keytoken";
    private static final String KEY_ID = "keyid";

    String token,id;
    // String TAG="AddPostFragment ";

    ImageSliderAdapter adapter;

    RecyclerView AdRecylerView;

    ShowServiceProviderJobHistoryAdapter showServiceProviderJobHistoryAdapter;
    LinearLayout loding;
    ProgressBar progress_bar_pagination;
    EditText search;

    private List <ShowServiceProviderJobHistory> myInterests = new ArrayList <>(  );

    LinearLayoutManager linearLayoutManager;

    ShowServiceProviderJobHistoryResponce showServiceProviderJobHistoryResponce;

    ImageView no_result;




    @Override
    public void onAttach(Context context) {
        this.context=context;
        super.onAttach(context);
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        token = sharedPreferences.getString(KEY_TOKEN, "");
        id = sharedPreferences.getString(KEY_ID,"");
        Log.e( TAG, "onAttach: "+token  );

    }


    public ServiceProviderCategory() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_service_provider_category, container, false );

        adapterViewFlipper = (AdapterViewFlipper) view.findViewById(R.id.adapterViewFlipper);

        fetchImages();



        linearLayoutManager = new LinearLayoutManager( getContext(), LinearLayoutManager.VERTICAL, false);

        progress_bar_pagination = view.findViewById(R.id.progress_bar_pagination);

        loding = view.findViewById(R.id.loding);
        loding.setVisibility( View.VISIBLE );

        no_result = view.findViewById( R.id.no_result );
        no_result.setVisibility( View.GONE );

        AdRecylerView = view.findViewById(R.id.ad_recyler_view);

        showServiceProviderJobHistoryAdapter = new ShowServiceProviderJobHistoryAdapter(getContext(),myInterests);
        AdRecylerView.setLayoutManager( linearLayoutManager );
        AdRecylerView.setItemAnimator(new DefaultItemAnimator());
        AdRecylerView.setAdapter(showServiceProviderJobHistoryAdapter);

        fetchPendingJobOffer();



        return view;
    }

    private void fetchImages() {

        Call<ImageSliderResponce> call = RetrofitClient.getInstance( token ).getApiInterface().imageSlider();
        call.enqueue( new Callback<ImageSliderResponce>() {
            @Override
            public void onResponse(Call<ImageSliderResponce> call, Response<ImageSliderResponce> response) {
                imageSliderResponce = response.body();

                Log.e( TAG, "onResponseBB: "+response.body().toString()  );
                if (response.isSuccessful())
                {
                    ImageSliderAdapter adapter = new ImageSliderAdapter(getContext(), (ArrayList <ActiveImage>) response.body().getActiveImage() );
                    adapterViewFlipper.setAdapter(adapter);
                    adapterViewFlipper.setFlipInterval(1000);
                    adapterViewFlipper.startFlipping();

                }else
                {
                    Toast.makeText( getContext(), "Fail", Toast.LENGTH_SHORT ).show();
                }
            }

            @Override
            public void onFailure(Call<ImageSliderResponce> call, Throwable t) {
                Toast.makeText( getContext(), "onFailure", Toast.LENGTH_SHORT ).show();
                Log.e( TAG, "onFailure: "+ t.getLocalizedMessage() );
            }
        } );

    }


    private void fetchPendingJobOffer() {

        Call <ShowServiceProviderJobHistoryResponce> call = RetrofitClient.getInstance( token ).getApiInterface().showServiceProviderJobHistoryResponce(id);
        call.enqueue( new Callback <ShowServiceProviderJobHistoryResponce>() {
            @Override
            public void onResponse(Call<ShowServiceProviderJobHistoryResponce> call, Response <ShowServiceProviderJobHistoryResponce> response) {
                showServiceProviderJobHistoryResponce = response.body();

                Log.e( TAG, "onResponseBB: "+response.body());
                if (response.isSuccessful())
                {
                    myInterests = showServiceProviderJobHistoryResponce.getShowServiceProviderJobHistory();

                    Log.e( TAG, "onResponseAA: "+response.body().toString()  );
                    if (showServiceProviderJobHistoryResponce.getShowServiceProviderJobHistory().size() != 0){
                        showServiceProviderJobHistoryAdapter.updateList( myInterests );

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
            public void onFailure(Call<ShowServiceProviderJobHistoryResponce> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.e( TAG, "onFailure: "+ t.getLocalizedMessage() );
            }
        } );

    }

}
