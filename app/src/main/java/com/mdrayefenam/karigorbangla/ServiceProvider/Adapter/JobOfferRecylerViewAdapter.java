package com.mdrayefenam.karigorbangla.ServiceProvider.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mdrayefenam.karigorbangla.R;
import com.mdrayefenam.karigorbangla.ServiceProvider.Activity.ServiceProviderJobConfirm;
import com.mdrayefenam.karigorbangla.ServiceProvider.ServiceTakerJobOfferModel.MyInterest;
import com.mdrayefenam.karigorbangla.ServiceTaker.Activity.ServiceProviderProfileInServiceTaker;
import com.mdrayefenam.karigorbangla.ServiceTaker.Adapter.ServiceProviderInServiceTakerRecylerView;
import com.mdrayefenam.karigorbangla.ServiceTaker.ServiceProviderLocationModel.ServiceProvider;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class JobOfferRecylerViewAdapter extends RecyclerView.Adapter<JobOfferRecylerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List <MyInterest> serviceProviders;

    String number;
    String TAG = "ServiceProviderInServiceTakerRecylerView";

    //String Tag = "AdPostRecylerViewAdapter";

    public JobOfferRecylerViewAdapter(Context mContext, List<MyInterest> serviceProviders) {
        this.mContext = mContext;
        this.serviceProviders = serviceProviders;
    }

    @Override
    public JobOfferRecylerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        final LayoutInflater inflater = LayoutInflater.from( mContext );
        view = inflater.inflate( R.layout.service_provider_jod_offer_recyler_view_item,parent,false);
        final JobOfferRecylerViewAdapter.MyViewHolder viewHolder = new JobOfferRecylerViewAdapter.MyViewHolder( view );
        viewHolder.job_offer_item.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( mContext, ServiceProviderJobConfirm.class );
                intent.putExtra( "ServiceID",serviceProviders.get( viewHolder.getAdapterPosition()).getId());
                intent.putExtra( "ServiceTakerProfilePhoto",serviceProviders.get( viewHolder.getAdapterPosition()).getServiceTakerProfileImage());
                intent.putExtra( "ServiceTakerName",serviceProviders.get( viewHolder.getAdapterPosition()).getServiceTakerName());
                intent.putExtra( "ServiceTakerEmail",serviceProviders.get( viewHolder.getAdapterPosition()).getServiceTakerEmail());
                intent.putExtra( "ServiceTakerAddress",serviceProviders.get( viewHolder.getAdapterPosition()).getServiceTakerAddress());
                intent.putExtra( "ServiceTakerLocationLatitude",serviceProviders.get( viewHolder.getAdapterPosition()).getServiceTakerLocationLatitude());
                intent.putExtra( "ServiceTakerLocationLongitude",serviceProviders.get( viewHolder.getAdapterPosition()).getServiceTakerLocationLongitude());

                intent.putExtra( "ServiceTakerId",serviceProviders.get( viewHolder.getAdapterPosition()).getServiceTakerId());
                intent.putExtra( "ServiceTakerNumbre",serviceProviders.get( viewHolder.getAdapterPosition()).getServiceTakerNumbre());

                intent.putExtra( "ServiceDay",serviceProviders.get( viewHolder.getAdapterPosition()).getServiceDay());
                intent.putExtra( "ServiceTime",serviceProviders.get( viewHolder.getAdapterPosition()).getServiceTime());
                intent.putExtra( "ServiceAddress",serviceProviders.get( viewHolder.getAdapterPosition()).getServiceAddress());

                mContext.startActivity(intent);


            }
        } );

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(JobOfferRecylerViewAdapter.MyViewHolder holder, int position) {
        holder.service_taker_name.setText( serviceProviders.get( position ).getServiceTakerName());
        holder.service_taker_address.setText( serviceProviders.get( position ).getServiceTakerAddress() );
        holder.post_time.setText( serviceProviders.get( position ).getCreatedAt() );
        holder.service_date.setText("Service Date : "+serviceProviders.get( position ).getServiceDay());
        holder.service_time.setText("Service Time : "+ serviceProviders.get( position ).getServiceTime());
        holder.service_area.setText("Service Time : "+ serviceProviders.get( position ).getServiceAddress());
    }

    @Override
    public int getItemCount() {
        return serviceProviders.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView service_taker_name, service_taker_address,post_time,service_date,service_time,service_area;
        LinearLayout job_offer_item;

        public MyViewHolder(View itemView) {
            super( itemView );
            job_offer_item = itemView.findViewById( R.id.job_offer_item );
            service_taker_name = itemView.findViewById( R.id.service_taker_name );
            service_taker_address = itemView.findViewById( R.id.service_taker_address );
            post_time = itemView.findViewById( R.id.post_time );

            service_date = itemView.findViewById( R.id.service_date );
            service_time = itemView.findViewById( R.id.service_time );
            service_area = itemView.findViewById( R.id.service_area );

        }
    }

    public void updateList(List<MyInterest> serviceProviders){
        this.serviceProviders=serviceProviders;
        notifyDataSetChanged();
    }


}
