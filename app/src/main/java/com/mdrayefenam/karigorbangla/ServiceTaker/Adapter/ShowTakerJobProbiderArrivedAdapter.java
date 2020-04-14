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
import com.mdrayefenam.karigorbangla.ServiceTaker.Activity.ServiceTime;
import com.mdrayefenam.karigorbangla.ServiceTaker.ServiceProviderPendingJobOfferModel.ShowServiceTakerPendingJobOffer;
import com.mdrayefenam.karigorbangla.ServiceTaker.ShowTakerJobProbiderArrivedModel.ShowTakerJobProbiderArrivedConfirm;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowTakerJobProbiderArrivedAdapter extends RecyclerView.Adapter<ShowTakerJobProbiderArrivedAdapter.MyViewHolder> {

    private static final String SHARED_PREF_NAME = "simplifiedcodingsharedpref";
    private static final String KEY_TOKEN = "keytoken";

    private Context mContext;
    private List <ShowTakerJobProbiderArrivedConfirm> serviceProviders;

    int mId;

    int mStatus = 3;
    String token, Id;
    String TAG = "ServiceProviderInServiceTakerRecylerView";
    private Context context;


    //String Tag = "AdPostRecylerViewAdapter";

    public ShowTakerJobProbiderArrivedAdapter(Context mContext, List<ShowTakerJobProbiderArrivedConfirm> serviceProviders) {
        this.mContext = mContext;
        this.serviceProviders = serviceProviders;

    }

    @Override
    public ShowTakerJobProbiderArrivedAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        final LayoutInflater inflater = LayoutInflater.from( mContext );
        view = inflater.inflate( R.layout.show_taker_job_probider_arrived_recyler_view_item,parent,false);
        final ShowTakerJobProbiderArrivedAdapter.MyViewHolder viewHolder = new ShowTakerJobProbiderArrivedAdapter.MyViewHolder( view );
        viewHolder.pending_job_offer_item.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                  Intent intent = new Intent( mContext, ServiceTime.class );
                  intent.putExtra( "JoBId",serviceProviders.get( viewHolder.getAdapterPosition()).getId());
                  intent.putExtra( "ProviderId",serviceProviders.get( viewHolder.getAdapterPosition()).getProviderId());
                Log.e( TAG, "onClick: "+ serviceProviders.get( viewHolder.getAdapterPosition()).getProviderId());
                  intent.putExtra( "ProviderName",serviceProviders.get( viewHolder.getAdapterPosition()).getProviderName());
                  intent.putExtra( "ProviderMobile",serviceProviders.get( viewHolder.getAdapterPosition()).getProviderMobile());
                  intent.putExtra( "ProviderProfileImage",serviceProviders.get( viewHolder.getAdapterPosition()).getProviderProfileImage());
                  intent.putExtra( "ServiceDay",serviceProviders.get( viewHolder.getAdapterPosition()).getServiceDay());
                  intent.putExtra( "ServiceTime",serviceProviders.get( viewHolder.getAdapterPosition()).getServiceTime());
                  intent.putExtra( "ServiceAddress",serviceProviders.get( viewHolder.getAdapterPosition()).getServiceAddress());
                  mContext.startActivity(intent);






            }



        } );



        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ShowTakerJobProbiderArrivedAdapter.MyViewHolder holder, int position) {
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

        }
    }

    public void updateList(List<ShowTakerJobProbiderArrivedConfirm> serviceProviders){
        this.serviceProviders=serviceProviders;
        notifyDataSetChanged();
    }
}
