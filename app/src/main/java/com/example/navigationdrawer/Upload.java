package com.example.navigationdrawer;

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

public class Upload extends Activity {
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
    private String description="No description";
    private String location="no link";

    private EditText mEditTextLocation;
    private EditText mEditTextName;

    private EditText mEditTextPhone;

    private EditText mEditTextPrice;



    private String source;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);


        mButtonChooseImage=findViewById(R.id.addImage);
        mButtonUpload=findViewById(R.id.uploadimage);
        mEditTextDescription=findViewById(R.id.description);
        mEditTextLocation=findViewById(R.id.location);
        mEditTextName=findViewById(R.id.name);
        mEditTextPhone=findViewById(R.id.phone);
        mEditTextPrice=findViewById(R.id.price);
        mImageView=(ImageView) findViewById(R.id.imageview);

        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");
        mDatabaseRef= FirebaseDatabase.getInstance().getReference("trade");




        mButtonChooseImage.setOnClickListener(view -> {
            openFileChooser();
        });
        mButtonUpload.setOnClickListener(view -> {
//            if(mEditTextDescription.getText().toString().trim().equalsIgnoreCase("") &&
//                    mEditTextLocation.getText().toString().trim().equalsIgnoreCase("") &&
//                    check==0)
//            {
//                Toast.makeText(this, "Nothing to post", Toast.LENGTH_SHORT).show();
//            }
//            else
//            {
//                if(!mEditTextDescription.getText().toString().trim().equalsIgnoreCase(""))
//                {
//                    description=mEditTextDescription.getText().toString();
//
//                }
//                if(!mEditTextLink.getText().toString().trim().equalsIgnoreCase(""))
//                {
//                    location=mEditTextLink.getText().toString();
//                }
                ProgressBar bar=findViewById(R.id.progressbar);
                bar.setVisibility(View.VISIBLE);
                uploadFile();

            //}

        });



    }

    private String getFileExtension(Uri uri){
        ContentResolver cR=getContentResolver();
        MimeTypeMap mime=MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
    private void uploadFile() {

        if(check==0)
        {
            String url="https://firebasestorage.googleapis.com/v0/b/farming-99d4c.appspot.com/o/download%20(1).jpeg?alt=media&token=d0472f84-27b1-4c21-99ba-296baf11fc46";

            Uploadmodel uploadmodel=new Uploadmodel(mEditTextDescription.getText().toString(),url,mEditTextLocation.getText().toString(),mEditTextName.getText().toString(),mEditTextPhone.getText().toString(),mEditTextPrice.getText().toString());
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
            Toast.makeText(Upload.this, "Upload Successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(),Home.class));
            finish();

        }
        else
        {
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()+"."+getFileExtension(img));
            fileReference.putFile(img)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler=new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                }
                            },500);
                            Toast.makeText(Upload.this, "Upload Successful", Toast.LENGTH_SHORT).show();
                            Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                            while(!uri.isComplete());
                            Uri url = uri.getResult();

                            Uploadmodel uploadmodel=new Uploadmodel(mEditTextDescription.getText().toString(),url.toString(),mEditTextLocation.getText().toString(),mEditTextName.getText().toString(),mEditTextPhone.getText().toString(),mEditTextPrice.getText().toString());
                            String uploadId=mDatabaseRef.push().getKey();
                            mDatabaseRef.child(uploadId).setValue(uploadmodel);
                            startActivity(new Intent(getApplicationContext(),Home.class));
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Upload.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

        }





    }

    private void openFileChooser()
    {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE_REQUEST && resultCode==RESULT_OK
                && data!=null && data.getData()!=null)
        {
            img=data.getData();

            Picasso.get().load(img).into(mImageView);
            check=1;
        }
    }
}