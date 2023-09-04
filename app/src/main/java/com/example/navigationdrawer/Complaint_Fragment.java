package com.example.navigationdrawer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
public class Complaint_Fragment extends Fragment {

    private static final int PICK_IMAGE_REQUEST=1;

    private Button mButtonChooseImage;
    private Button mButtonUpload;
    private AppCompatButton mAddLink;
    private EditText mEditTextDescription;
    private EditText mEditTextLink;
    private ImageView mImageView;
    private ProgressBar mProgressBar;

    private Uri img;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;


    private int check=0;
    private int s=0;
    private String mdpimg;
    // private String description,link;


    private EditText mEditTextAadhar;

    private ProgressBar bar;
    private EditText mEditTextName;

    private EditText mEditTextPhone;

    private EditText mEditTextDate;

    View v;



    private String source;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_complaint_, container, false);
        mButtonUpload=v.findViewById(R.id.uploadimage);
        mEditTextDescription=v.findViewById(R.id.complaint);
        mEditTextAadhar=v.findViewById(R.id.aadhar);
        mEditTextName=v.findViewById(R.id.name);
        mEditTextPhone=v.findViewById(R.id.phone);
        mEditTextDate=v.findViewById(R.id.date);


        //mStorageRef = FirebaseStorage.getInstance().getReference("uploads");
        mDatabaseRef= FirebaseDatabase.getInstance().getReference("complaint");


        mButtonUpload.setOnClickListener(view -> {
            bar=v.findViewById(R.id.progressbar);
            bar.setVisibility(View.VISIBLE);
            uploadFile();


        });
        return v;
    }

    private void uploadFile() {




                            ComplaintModel uploadmodel=new ComplaintModel(mEditTextAadhar.getText().toString(),mEditTextDate.getText().toString(),mEditTextDescription.getText().toString(),mEditTextPhone.getText().toString(),mEditTextName.getText().toString());
                            String uploadId=mDatabaseRef.push().getKey();
                            mDatabaseRef.child(uploadId).setValue(uploadmodel);
                            Thread thread= new Thread(){
                                public void run(){
                                    try{
                                        sleep(3000);
                                    }
                                    catch(Exception e){
                                        e.printStackTrace();
                                    }

                                }
                            };thread.start();
                            Toast.makeText(getContext(), "Complaint Registered. We'll reach you out within 24hrs", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(getContext(),Home.class);
                            startActivity(intent);
                            bar.setVisibility(View.INVISIBLE);









    }

}