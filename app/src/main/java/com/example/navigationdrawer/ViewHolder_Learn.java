package com.example.navigationdrawer;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class ViewHolder_Learn extends RecyclerView.ViewHolder{
    View mview;
    public ViewHolder_Learn(@NonNull View itemView) {
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

    public void setDetails(Context ctx,String desc,String link,String title)
    {
        TextView mdesc=mview.findViewById(R.id.desc);
        TextView mlink=mview.findViewById(R.id.link);
        TextView mtitle=mview.findViewById(R.id.title);

        mdesc.setText(desc);
        mlink.setText(link);
        mtitle.setText(title);

    }



    private ViewHolder_Learn.ClickListener mClickListener;
    public interface ClickListener{
        void onItemClick(View view,int position);
        void onItemLongClick(View view,int position);
    }
    public void setOnClickListener(ViewHolder_Learn.ClickListener clickListener)
    {
        mClickListener=clickListener;
    }
}
