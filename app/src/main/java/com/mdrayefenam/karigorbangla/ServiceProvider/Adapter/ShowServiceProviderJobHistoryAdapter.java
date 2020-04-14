package com.mdrayefenam.karigorbangla.ServiceProvider.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mdrayefenam.karigorbangla.R;
import com.mdrayefenam.karigorbangla.ServiceProvider.ShowServiceProviderJobHistoryModel.ShowServiceProviderJobHistory;
import com.mdrayefenam.karigorbangla.ServiceTaker.Adapter.showServiceTakerJobHistoryAdapter;
import com.mdrayefenam.karigorbangla.ServiceTaker.showServiceTakerJobHistoryModel.ShowServiceTakerJobHistory;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class ShowServiceProviderJobHistoryAdapter  extends RecyclerView.Adapter<ShowServiceProviderJobHistoryAdapter.MyViewHolder> {

    private static final String SHARED_PREF_NAME = "simplifiedcodingsharedpref";
    private static final String KEY_TOKEN = "keytoken";

    private Context mContext;
    private List <ShowServiceProviderJobHistory> serviceProviders;

    int mId;

    String TAG = "ServiceProviderInServiceTakerRecylerView";
    private Context context;


    //String Tag = "AdPostRecylerViewAdapter";

    public ShowServiceProviderJobHistoryAdapter(Context mContext, List<ShowServiceProviderJobHistory> serviceProviders) {
        this.mContext = mContext;
        this.serviceProviders = serviceProviders;

    }

    @Override
    public ShowServiceProviderJobHistoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        final LayoutInflater inflater = LayoutInflater.from( mContext );
        view = inflater.inflate( R.layout.service_provider_job_history_recyler_view_item,parent,false);
        final ShowServiceProviderJobHistoryAdapter.MyViewHolder viewHolder = new ShowServiceProviderJobHistoryAdapter.MyViewHolder( view );
        viewHolder.job_history.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }



        } );



        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ShowServiceProviderJobHistoryAdapter.MyViewHolder holder, int position) {
        holder.service_provider_name.setText( serviceProviders.get( position ).getServiceTakerName());
        holder.service_provider_address.setText("Service Taker Address : "+serviceProviders.get( position ).getServiceTakerAddress() );
        holder.post_time.setText( serviceProviders.get( position ).getCreatedAt() );
        holder.service_date.setText("Service day : " +serviceProviders.get( position ).getServiceDay() );
        holder.service_time.setText("Service time :  "+serviceProviders.get( position ).getServiceTime() );
        holder.service_area.setText("Service Address : "+serviceProviders.get( position ).getServiceAddress() );

        String Url = "https://karigor.againwish.com/";

        ShowServiceProviderJobHistory product = serviceProviders.get(position);
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

    public void updateList(List<ShowServiceProviderJobHistory> serviceProviders){
        this.serviceProviders=serviceProviders;
        notifyDataSetChanged();
    }
}
