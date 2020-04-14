package com.mdrayefenam.karigorbangla.ServiceTaker.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mdrayefenam.karigorbangla.Network.RetrofitClient;
import com.mdrayefenam.karigorbangla.R;
import com.mdrayefenam.karigorbangla.ServiceProvider.DeletServiceProviderPendingJobOfferModel.ServiceProviderPendingJobOfferDeletResponce;
import com.mdrayefenam.karigorbangla.ServiceTaker.Activity.MainActivity;
import com.mdrayefenam.karigorbangla.ServiceTaker.ServiceProviderPendingJobOfferModel.ShowServiceTakerPendingJobOffer;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceProviderPendingJobOfferRecylerViewAdapter extends RecyclerView.Adapter<ServiceProviderPendingJobOfferRecylerViewAdapter.MyViewHolder> {


    private static final String SHARED_PREF_NAME = "simplifiedcodingsharedpref";
    private static final String KEY_TOKEN = "keytoken";

    private Context mContext;
    private List <ShowServiceTakerPendingJobOffer> serviceProviders;

    int mId;

    int mStatus = 3;
    String token, Id;
    String TAG = "ServiceProviderInServiceTakerRecylerView";
    private Context context;


    //String Tag = "AdPostRecylerViewAdapter";

    public ServiceProviderPendingJobOfferRecylerViewAdapter(Context mContext, List<ShowServiceTakerPendingJobOffer> serviceProviders) {
        this.mContext = mContext;
        this.serviceProviders = serviceProviders;

    }

    @Override
    public ServiceProviderPendingJobOfferRecylerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        final LayoutInflater inflater = LayoutInflater.from( mContext );
        view = inflater.inflate( R.layout.service_taker_jod_offer_panding_recyler_view_item,parent,false);
        final ServiceProviderPendingJobOfferRecylerViewAdapter.MyViewHolder viewHolder = new ServiceProviderPendingJobOfferRecylerViewAdapter.MyViewHolder( view );
        viewHolder.delete.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewHolder.progress_bar.setVisibility( View.VISIBLE );
                viewHolder.delete.setVisibility( View.GONE );


                Id = String.valueOf(mId);

//                SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//                token = sharedPreferences.getString(KEY_TOKEN, "");

                Log.e( TAG, "Data :"+token +"\n"+Id);

                Call <ServiceProviderPendingJobOfferDeletResponce> call = RetrofitClient.getInstance().getApiInterface().serviceProviderPendingJobOfferDeletResponce(Id,mStatus);



                call.enqueue(new Callback <ServiceProviderPendingJobOfferDeletResponce>() {
                    @Override
                    public void onResponse(Call<ServiceProviderPendingJobOfferDeletResponce> call, Response <ServiceProviderPendingJobOfferDeletResponce> response) {
                        if (response.isSuccessful()) {
                            Log.d(TAG, "onResponse: " + response.code());
                            ServiceProviderPendingJobOfferDeletResponce serviceProviderPendingJobOfferDeletResponce = response.body();
//                            Log.d(TAG, "ChangeMyPostStatusResponce: " + changeMyPostStatusResponce.toString());
                            if (serviceProviderPendingJobOfferDeletResponce != null && serviceProviderPendingJobOfferDeletResponce.getChangeStatus() == 1) {

                                viewHolder.progress_bar.setVisibility( View.GONE );
                                viewHolder.delete.setVisibility( View.VISIBLE );

                                Intent intent = new Intent( mContext, MainActivity.class );
                                mContext.startActivity(intent);


                            } else {
                                viewHolder.progress_bar.setVisibility( View.GONE );
                                viewHolder.delete.setVisibility( View.VISIBLE );
                            }



                        } else {
                            Log.d(TAG, "onResponse: " + response.code());
                            viewHolder.progress_bar.setVisibility( View.GONE );
                            viewHolder.delete.setVisibility( View.VISIBLE );
                        }
                    }

                    @Override
                    public void onFailure(Call<ServiceProviderPendingJobOfferDeletResponce> call, Throwable t) {
//                        Toast.makeText(MyAddView.this,"Data Featching Faild", Toast.LENGTH_SHORT).show();
                        viewHolder.progress_bar.setVisibility( View.GONE );
                        viewHolder.delete.setVisibility( View.VISIBLE );
                    }
                });



            }



        } );



        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ServiceProviderPendingJobOfferRecylerViewAdapter.MyViewHolder holder, int position) {
        holder.service_provider_name.setText( serviceProviders.get( position ).getProviderName());
        holder.service_provider_address.setText("Provider Code : #"+serviceProviders.get( position ).getProviderId() );
        holder.post_time.setText( serviceProviders.get( position ).getCreatedAt() );
        holder.service_date.setText("Service day : " +serviceProviders.get( position ).getServiceDay() );
        holder.service_time.setText("Service time :  "+serviceProviders.get( position ).getServiceTime() );
        holder.service_area.setText("Service Address : "+serviceProviders.get( position ).getServiceAddress() );
        mId = serviceProviders.get( position ).getId();
    }

    @Override
    public int getItemCount() {
        return serviceProviders.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView service_provider_name, service_provider_address,post_time,service_date,service_time,service_area;
        LinearLayout pending_job_offer_item;
        ProgressBar progress_bar;

        Button delete;

        public MyViewHolder(View itemView) {
            super( itemView );
            pending_job_offer_item = itemView.findViewById( R.id.pending_job_offer_item );
            service_provider_name = itemView.findViewById( R.id.service_provider_name );
            service_provider_address = itemView.findViewById( R.id.service_provider_address );
            post_time = itemView.findViewById( R.id.post_time );
            service_date = itemView.findViewById( R.id.service_date );
            service_time = itemView.findViewById( R.id.service_time );
            service_area = itemView.findViewById( R.id.service_area );

            delete = itemView.findViewById( R.id.delete );
            progress_bar = itemView.findViewById( R.id.progress_bar );


        }
    }

    public void updateList(List<ShowServiceTakerPendingJobOffer> serviceProviders){
        this.serviceProviders=serviceProviders;
        notifyDataSetChanged();
    }
}
