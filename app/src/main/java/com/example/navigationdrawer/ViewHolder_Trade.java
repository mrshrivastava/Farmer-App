package com.example.navigationdrawer;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class ViewHolder_Trade extends RecyclerView.ViewHolder{
    View mview;
    public ViewHolder_Trade(@NonNull View itemView) {
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

    public void setDetails(Context ctx, String desc, String img, String location, String name,String phone, String price)
    {
        TextView mdesc=mview.findViewById(R.id.desc);
        TextView mlocation=mview.findViewById(R.id.location);
        ImageView mimg=mview.findViewById(R.id.img1);
        TextView mname=mview.findViewById(R.id.source);
        TextView mphone=mview.findViewById(R.id.phone);
        TextView mprice=mview.findViewById(R.id.price);
        mlocation.setText(location);
        mname.setText(name);
        mdesc.setText(desc);
        mphone.setText(phone);
        mprice.setText(price);
        if(img!=null)
        {
            Picasso.get().load(img).into(mimg);
        }



    }



    private ViewHolder_Trade.ClickListener mClickListener;
    public interface ClickListener{
        void onItemClick(View view,int position);
        void onItemLongClick(View view,int position);
    }
    public void setOnClickListener(ViewHolder_Trade.ClickListener clickListener)
    {
        mClickListener=clickListener;
    }
}
