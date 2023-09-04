package com.example.navigationdrawer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;








public class Learn_Fragment extends Fragment {

    RecyclerView recyclerView;

    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;
    FirebaseRecyclerAdapter<Main_Model_Learn,ViewHolder_Learn> firebaseRecyclerAdapter;
    FirebaseRecyclerOptions<Main_Model_Learn> options;
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


        View v= inflater.inflate(R.layout.fragment_learn_, container, false);
        recyclerView=v.findViewById(R.id.rv1);
        mLinearLayoutManager=new LinearLayoutManager(getContext());
        mLinearLayoutManager.setReverseLayout(true);
        mLinearLayoutManager.setStackFromEnd(true);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference =mFirebaseDatabase.getReference("learn");
        shownotification();
        return v;
    }

    private void shownotification() {

        options=new FirebaseRecyclerOptions.Builder<Main_Model_Learn>().setQuery(mDatabaseReference,Main_Model_Learn.class).build();
        firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Main_Model_Learn, ViewHolder_Learn>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder_Learn holder, int position, @NonNull Main_Model_Learn model) {
                String desc=model.getDesc();
                String link= model.getLink();
                if(desc.equalsIgnoreCase("no description"))
                    desc=" ";
                if(link.equalsIgnoreCase("no link"))
                    link=" ";
                else {
                    link = model.getLink();
                    if(link.length()>80)
                    {
                        link=link.substring(0,79)+"...";
                    }
                }

                holder.setDetails(getActivity().getApplicationContext(),desc,link,model.getTitle());
                holder.itemView.findViewById(R.id.link).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String accesslink=model.getLink();
                        if(!accesslink.equalsIgnoreCase("no link"))
                        {
                            gotoUrl(accesslink);
                        }

                    }
                });
//                holder.itemView.findViewById(R.id.img1).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent intent=new Intent(Home_Fragment.this,postview.class);
//                        intent.putExtra("imgurl",model.getImg());
//                        startActivity(intent);
//                    }
//                });


            }

            @NonNull
            @Override
            public ViewHolder_Learn onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.learn_card_view,parent,false);
                ViewHolder_Learn viewHolder=new ViewHolder_Learn(itemView);
                viewHolder.setOnClickListener(new ViewHolder_Learn.ClickListener() {

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
    private void gotoUrl(String s) {
        Uri uri= Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}