package com.mdrayefenam.karigorbangla.ServiceProvider.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mdrayefenam.karigorbangla.R;
import com.mdrayefenam.karigorbangla.ServiceProvider.Activity.ServiceProviderJobConfirm;
import com.mdrayefenam.karigorbangla.ServiceProvider.Activity.ServiceProviderJobViewActivity;
import com.mdrayefenam.karigorbangla.ServiceProvider.ProviderConfirmJobListModel.ServiceConfirmDatum;
import com.mdrayefenam.karigorbangla.ServiceProvider.ServiceTakerJobOfferModel.MyInterest;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class ProviderJobListAdapter extends RecyclerView.Adapter<ProviderJobListAdapter.MyViewHolder> {

    private Context mContext;
    private List <ServiceConfirmDatum> serviceConfirmData;

    String number;
    String TAG = "ServiceProviderInServiceTakerRecylerView";

    //String Tag = "AdPostRecylerViewAdapter";

    public ProviderJobListAdapter(Context mContext, List<ServiceConfirmDatum> serviceProviders) {
        this.mContext = mContext;
        this.serviceConfirmData = serviceProviders;
    }

    @Override
    public ProviderJobListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        final LayoutInflater inflater = LayoutInflater.from( mContext );
        view = inflater.inflate( R.layout.service_provider_jod_offer_recyler_view_item,parent,false);
        final ProviderJobListAdapter.MyViewHolder viewHolder = new ProviderJobListAdapter.MyViewHolder( view );
        viewHolder.job_offer_item.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent( mContext, ServiceProviderJobViewActivity.class );
                intent.putExtra( "ServiceID",serviceConfirmData.get( viewHolder.getAdapterPosition()).getId());
                intent.putExtra( "ServiceTakerProfilePhoto",serviceConfirmData.get( viewHolder.getAdapterPosition()).getServiceTakerProfileImage());
                intent.putExtra( "ServiceTakerName",serviceConfirmData.get( viewHolder.getAdapterPosition()).getServiceTakerName());
                intent.putExtra( "ServiceTakerEmail",serviceConfirmData.get( viewHolder.getAdapterPosition()).getServiceTakerEmail());
                intent.putExtra( "ServiceTakerAddress",serviceConfirmData.get( viewHolder.getAdapterPosition()).getServiceTakerAddress());
                intent.putExtra( "ServiceTakerLocationLatitude",serviceConfirmData.get( viewHolder.getAdapterPosition()).getServiceTakerLocationLatitude());
                intent.putExtra( "ServiceTakerLocationLongitude",serviceConfirmData.get( viewHolder.getAdapterPosition()).getServiceTakerLocationLongitude());

                intent.putExtra( "ServiceTakerNumbre",serviceConfirmData.get( viewHolder.getAdapterPosition()).getServiceTakerNumbre());

                intent.putExtra( "ServiceDay",serviceConfirmData.get( viewHolder.getAdapterPosition()).getServiceDay());
                intent.putExtra( "ServiceTime",serviceConfirmData.get( viewHolder.getAdapterPosition()).getServiceTime());
                intent.putExtra( "ServiceAddress",serviceConfirmData.get( viewHolder.getAdapterPosition()).getServiceAddress());

                mContext.startActivity(intent);


            }
        } );

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ProviderJobListAdapter.MyViewHolder holder, int position) {
        holder.service_taker_name.setText( serviceConfirmData.get( position ).getServiceTakerName());
        holder.service_taker_address.setText( serviceConfirmData.get( position ).getServiceTakerAddress() );
        holder.post_time.setText( serviceConfirmData.get( position ).getCreatedAt() );
        holder.service_date.setText("Service Date : "+serviceConfirmData.get( position ).getServiceDay());
        holder.service_time.setText("Service Time : "+ serviceConfirmData.get( position ).getServiceTime());
        holder.service_area.setText("Service Time : "+ serviceConfirmData.get( position ).getServiceAddress());
    }

    @Override
    public int getItemCount() {
        return serviceConfirmData.size();
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

    public void updateList(List<ServiceConfirmDatum> serviceConfirmData){
        this.serviceConfirmData=serviceConfirmData;
        notifyDataSetChanged();
    }

}
