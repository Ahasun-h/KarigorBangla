package com.mdrayefenam.karigorbangla.ServiceTaker.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mdrayefenam.karigorbangla.R;
import com.mdrayefenam.karigorbangla.ServiceTaker.Activity.ServiceTime;
import com.mdrayefenam.karigorbangla.ServiceTaker.ShowTakerJobProbiderArrivedModel.ShowTakerJobProbiderArrivedConfirm;
import com.mdrayefenam.karigorbangla.ServiceTaker.showServiceTakerJobHistoryModel.ShowServiceTakerJobHistory;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class showServiceTakerJobHistoryAdapter extends RecyclerView.Adapter<showServiceTakerJobHistoryAdapter.MyViewHolder>{

    private static final String SHARED_PREF_NAME = "simplifiedcodingsharedpref";
    private static final String KEY_TOKEN = "keytoken";

    private Context mContext;
    private List <ShowServiceTakerJobHistory> serviceProviders;

    int mId;

    int mStatus = 3;
    String token, Id;
    String TAG = "ServiceProviderInServiceTakerRecylerView";
    private Context context;


    //String Tag = "AdPostRecylerViewAdapter";

    public showServiceTakerJobHistoryAdapter(Context mContext, List<ShowServiceTakerJobHistory> serviceProviders) {
        this.mContext = mContext;
        this.serviceProviders = serviceProviders;

    }

    @Override
    public showServiceTakerJobHistoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        final LayoutInflater inflater = LayoutInflater.from( mContext );
        view = inflater.inflate( R.layout.service_provider_job_history_recyler_view_item,parent,false);
        final showServiceTakerJobHistoryAdapter.MyViewHolder viewHolder = new showServiceTakerJobHistoryAdapter.MyViewHolder( view );
        viewHolder.job_history.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {








            }



        } );



        return viewHolder;
    }

    @Override
    public void onBindViewHolder(showServiceTakerJobHistoryAdapter.MyViewHolder holder, int position) {
        holder.service_provider_name.setText( serviceProviders.get( position ).getProviderName());
        holder.service_provider_address.setText("Provider Code : #"+String.valueOf(serviceProviders.get( position ).getProviderId()) );
        holder.post_time.setText( serviceProviders.get( position ).getCreatedAt() );
        holder.service_date.setText("Service day : " +serviceProviders.get( position ).getServiceDay() );
        holder.service_time.setText("Service time :  "+serviceProviders.get( position ).getServiceTime() );
        holder.service_area.setText("Service Address : "+serviceProviders.get( position ).getServiceAddress() );

        String Url = "https://karigor.againwish.com/";

        ShowServiceTakerJobHistory product = serviceProviders.get(position);
        Glide.with(mContext)
                .load(Url+product.getProviderProfileImage())
                .into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return serviceProviders.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView service_provider_name, service_provider_address,post_time,service_date,service_time,service_area;
        LinearLayout job_history;
        ImageView iv;


        Button delete;

        public MyViewHolder(View itemView) {
            super( itemView );
            job_history = itemView.findViewById( R.id.job_history );

            iv = itemView.findViewById( R.id.iv );

            service_provider_name = itemView.findViewById( R.id.service_provider_name );
            service_provider_address = itemView.findViewById( R.id.service_provider_address );
            post_time = itemView.findViewById( R.id.post_time );
            service_date = itemView.findViewById( R.id.service_date );
            service_time = itemView.findViewById( R.id.service_time );
            service_area = itemView.findViewById( R.id.service_area );

        }
    }

    public void updateList(List<ShowServiceTakerJobHistory> serviceProviders){
        this.serviceProviders=serviceProviders;
        notifyDataSetChanged();
    }
}
