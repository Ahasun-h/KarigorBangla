package com.mdrayefenam.karigorbangla.ServiceTaker.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mdrayefenam.karigorbangla.R;
import com.mdrayefenam.karigorbangla.Responce.NotificationModel.Notification;
import com.mdrayefenam.karigorbangla.RootActivity.NotificationView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {


    private Context mContext;
    private List<Notification> postList;

    //String Tag = "AdPostRecylerViewAdapter";

    public NotificationAdapter(Context mContext, List<Notification> postList) {
        this.mContext = mContext;
        this.postList = postList;
    }

    @Override
    public NotificationAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        final LayoutInflater inflater = LayoutInflater.from( mContext );
        view = inflater.inflate( R.layout.notification_recyler_view_item,parent,false);
        final NotificationAdapter.MyViewHolder viewHolder = new NotificationAdapter.MyViewHolder( view );
        viewHolder.notification_item.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent( mContext, NotificationView.class );
                intent.putExtra( "notification_title",postList.get( viewHolder.getAdapterPosition()).getNotificationTitle());
                intent.putExtra( "notification_date",postList.get( viewHolder.getAdapterPosition()).getCreatedAt());
                intent.putExtra( "notification_body",postList.get( viewHolder.getAdapterPosition()).getNotificationBody());
                intent.putExtra( "notification_image",postList.get( viewHolder.getAdapterPosition()).getImage());
                mContext.startActivity(intent);
            }
        } );
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NotificationAdapter.MyViewHolder holder, int position) {
        holder.title.setText( postList.get( position ).getNotificationTitle());
        holder.date.setText( postList.get( position ).getCreatedAt() );
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title, date;
        LinearLayout notification_item;
        ImageView iv;

        public MyViewHolder(View itemView) {
            super( itemView );
            notification_item = itemView.findViewById( R.id.notification_item );
            title = itemView.findViewById( R.id.title );
            date = itemView.findViewById( R.id.date );
        }
    }
    
    public void updateList(List<Notification> postList){
        this.postList=postList;
        notifyDataSetChanged();
    }

}

