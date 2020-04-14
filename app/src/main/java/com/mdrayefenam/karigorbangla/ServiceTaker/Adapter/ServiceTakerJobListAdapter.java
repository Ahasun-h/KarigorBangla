package com.mdrayefenam.karigorbangla.ServiceTaker.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mdrayefenam.karigorbangla.R;
import com.mdrayefenam.karigorbangla.ServiceTaker.Activity.ServiceProviderProfileInServiceTaker;
import com.mdrayefenam.karigorbangla.ServiceTaker.ServiceProviderConfirmJobModel.ServiceConfirmDataForUser;
import com.mdrayefenam.karigorbangla.ServiceTaker.ServiceProviderLocationModel.ServiceProvider;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class ServiceTakerJobListAdapter extends RecyclerView.Adapter<ServiceTakerJobListAdapter.MyViewHolder> {

    private Context mContext;
    private List <ServiceConfirmDataForUser> serviceProviders;

    String number;
    String TAG = "ServiceProviderInServiceTakerRecylerView";

    //String Tag = "AdPostRecylerViewAdapter";

    public ServiceTakerJobListAdapter(Context mContext, List<ServiceConfirmDataForUser> serviceProviders) {
        this.mContext = mContext;
        this.serviceProviders = serviceProviders;
    }

    @Override
    public ServiceTakerJobListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        final LayoutInflater inflater = LayoutInflater.from( mContext );
        view = inflater.inflate( R.layout.service_taker_confirm_job_recyler_view_item,parent,false);
        final ServiceTakerJobListAdapter.MyViewHolder viewHolder = new ServiceTakerJobListAdapter.MyViewHolder( view );
        viewHolder.pending_job_offer_item.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        } );



        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ServiceTakerJobListAdapter.MyViewHolder holder, int position) {
        holder.service_provider_name.setText( serviceProviders.get( position ).getProviderName());
        holder.service_provider_address.setText("Provider Code : #"+serviceProviders.get( position ).getProviderId() );
        holder.post_time.setText( serviceProviders.get( position ).getCreatedAt() );
        holder.service_date.setText("Service day : " +serviceProviders.get( position ).getServiceDay() );
        holder.service_time.setText("Service time :  "+serviceProviders.get( position ).getServiceTime() );
        holder.service_area.setText("Service Address : "+serviceProviders.get( position ).getServiceAddress() );
    }

    @Override
    public int getItemCount() {
        return serviceProviders.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView service_provider_name, service_provider_address,post_time,service_date,service_time,service_area;
        LinearLayout pending_job_offer_item;

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
        }
    }

    public void updateList(List<ServiceConfirmDataForUser> serviceProviders){
        this.serviceProviders=serviceProviders;
        notifyDataSetChanged();
    }

}
