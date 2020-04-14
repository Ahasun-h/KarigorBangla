package com.mdrayefenam.karigorbangla.ServiceTaker.Adapter;

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
import com.mdrayefenam.karigorbangla.ServiceTaker.Activity.ServiceProviderProfileInServiceTaker;
import com.mdrayefenam.karigorbangla.ServiceTaker.ServiceProviderLocationModel.ServiceProvider;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class ServiceProviderInServiceTakerRecylerView extends RecyclerView.Adapter<ServiceProviderInServiceTakerRecylerView.MyViewHolder> {


    private Context mContext;
    private List <ServiceProvider> serviceProviders;

    String number;
    String TAG = "ServiceProviderInServiceTakerRecylerView";

    //String Tag = "AdPostRecylerViewAdapter";

    public ServiceProviderInServiceTakerRecylerView(Context mContext, List<ServiceProvider> serviceProviders) {
        this.mContext = mContext;
        this.serviceProviders = serviceProviders;
    }

    @Override
    public ServiceProviderInServiceTakerRecylerView.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        final LayoutInflater inflater = LayoutInflater.from( mContext );
        view = inflater.inflate( R.layout.service_provider_recyler_view_item,parent,false);
        final ServiceProviderInServiceTakerRecylerView.MyViewHolder viewHolder = new ServiceProviderInServiceTakerRecylerView.MyViewHolder( view );
        viewHolder.provider_item.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( mContext,ServiceProviderProfileInServiceTaker.class );
                intent.putExtra( "provider_name",serviceProviders.get( viewHolder.getAdapterPosition()).getName());
                intent.putExtra( "provider_id",serviceProviders.get( viewHolder.getAdapterPosition()).getUserId());
                intent.putExtra( "provider_distance",serviceProviders.get( viewHolder.getAdapterPosition()).getDistance());
                intent.putExtra( "provider_address",serviceProviders.get( viewHolder.getAdapterPosition()).getAddress());
                intent.putExtra( "provider_gender",serviceProviders.get( viewHolder.getAdapterPosition()).getGender());
                intent.putExtra( "provider_mobile",serviceProviders.get( viewHolder.getAdapterPosition()).getMobile());
                mContext.startActivity(intent);
            }
        } );

        viewHolder.mobile.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent = new Intent( Intent.ACTION_DIAL );
              intent.setData( Uri.parse( "tel:"+number ) );
                mContext.startActivity(intent);
            }
        } );

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ServiceProviderInServiceTakerRecylerView.MyViewHolder holder, int position) {

        holder.name.setText( serviceProviders.get( position ).getName());
        holder.address.setText( serviceProviders.get( position ).getAddress() );
        holder.gender.setText( serviceProviders.get( position ).getGender() );
        holder.mobile.setText( serviceProviders.get( position ).getMobile() );

        number = serviceProviders.get( position ).getMobile();
        Log.e( TAG, "onBindViewHolder: "+ number);
    }

    @Override
    public int getItemCount() {
        return serviceProviders.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name, address,gender,mobile;
        LinearLayout provider_item;
        ImageView iv;

        public MyViewHolder(View itemView) {
            super( itemView );
            provider_item = itemView.findViewById( R.id.provider_item );
            name = itemView.findViewById( R.id.name );
            address = itemView.findViewById( R.id.address );
            gender = itemView.findViewById( R.id.gender );
            mobile = itemView.findViewById( R.id.mobile );

            iv = itemView.findViewById( R.id.iv );
        }
    }

    public void updateList(List<ServiceProvider> serviceProviders){
        this.serviceProviders=serviceProviders;
        notifyDataSetChanged();
    }

}
