package com.example.navigationdrawer;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;








public class Trade_Fragement extends Fragment {

    RecyclerView recyclerView;

    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;
    FirebaseRecyclerAdapter<Main_Model_Trade,ViewHolder_Trade> firebaseRecyclerAdapter;
    FirebaseRecyclerOptions<Main_Model_Trade> options;
    LinearLayoutManager mLinearLayoutManager;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    private String Name;
    private String url;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View v= inflater.inflate(R.layout.fragment_trade__fragement, container, false);
        FloatingActionButton uploadbutton=v.findViewById(R.id.floatingActionButton);
        recyclerView=v.findViewById(R.id.rv2);
        mLinearLayoutManager=new LinearLayoutManager(getContext());
        mLinearLayoutManager.setReverseLayout(true);
        mLinearLayoutManager.setStackFromEnd(true);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference =mFirebaseDatabase.getReference("trade");

        uploadbutton.setOnClickListener(view -> {
            Intent intent=new Intent(getContext(),Upload.class);
            startActivity(intent);
        });

        shownotification();
        return v;
    }

    private void shownotification() {

        options=new FirebaseRecyclerOptions.Builder<Main_Model_Trade>().setQuery(mDatabaseReference,Main_Model_Trade.class).build();
        firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Main_Model_Trade, ViewHolder_Trade>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder_Trade holder, int position, @NonNull Main_Model_Trade model) {
                String desc=model.getDesc();
                String img=model.getImg();
                if(desc.equalsIgnoreCase("no description"))
                    desc=" ";

                holder.setDetails(getActivity().getApplicationContext(),desc,img,model.getLocation(),model.getName(),model.getPhone(), model.getPrice());
                holder.itemView.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder= new AlertDialog.Builder(holder.itemView.getContext());
                        builder.setTitle("Are you sure you want to delete?");
                        builder.setMessage("Deleted data can't be undo");
                        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                FirebaseDatabase.getInstance().getReference().child("trade")
                                        .child(getRef(holder.getAdapterPosition()).getKey()).removeValue();
                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(holder.itemView.getContext(), "Cancelled",Toast.LENGTH_SHORT).show();
                            }
                        });
                        builder.show();
                    }
                });
                holder.itemView.findViewById(R.id.phone).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent callIntent = new Intent(Intent.ACTION_DIAL);
                        callIntent.setData(Uri.parse("tel:"+model.getPhone()
                        ));
                        startActivity(callIntent);
                    }
                });


            }

            @NonNull
            @Override
            public ViewHolder_Trade onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.trade_card_view,parent,false);
                ViewHolder_Trade viewHolder=new ViewHolder_Trade(itemView);
                viewHolder.setOnClickListener(new ViewHolder_Trade.ClickListener() {

                    @Override
                    public void onItemClick(View view, int position) {



//                        Toast.makeText(Home_Fragment.this,"Hello",Toast.LENGTH_SHORT);
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {
//                        Toast.makeText(Home.this,"Long Click",Toast.LENGTH_SHORT);
                    }

                });
                return viewHolder;
            }
        };

        recyclerView.setLayoutManager(mLinearLayoutManager);
        firebaseRecyclerAdapter.startListening();
        recyclerView.setAdapter(firebaseRecyclerAdapter);

    }

}