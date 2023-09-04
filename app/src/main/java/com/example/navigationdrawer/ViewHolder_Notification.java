package com.example.navigationdrawer;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewHolder_Notification extends RecyclerView.ViewHolder{
    View mview;
    public ViewHolder_Notification(@NonNull View itemView) {
        super(itemView);
        mview=itemView;
        TextView btn=itemView.findViewById(R.id.desc);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onItemClick(view,getAbsoluteAdapterPosition());

            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mClickListener.onItemLongClick(view,getAdapterPosition());
                return true;
            }
        });

    }

    public void setDetails(Context ctx, String date,String desc, String img, String link, String time)
    {
        TextView mdesc=mview.findViewById(R.id.desc);
        TextView mlink=mview.findViewById(R.id.link);
        ImageView mimg=mview.findViewById(R.id.img1);
        TextView mpdate=mview.findViewById(R.id.pdate);
        TextView mptime=mview.findViewById(R.id.ptime);
        mpdate.setText(date);
        mptime.setText(time);
        mdesc.setText(desc);
        if(img!=null)
        {
            Picasso.get().load(img).into(mimg);
        }

        mlink.setText(link);


    }



    private ViewHolder_Notification.ClickListener mClickListener;
    public interface ClickListener{
        void onItemClick(View view,int position);
        void onItemLongClick(View view,int position);
    }
    public void setOnClickListener(ViewHolder_Notification.ClickListener clickListener)
    {
        mClickListener=clickListener;
    }
}
