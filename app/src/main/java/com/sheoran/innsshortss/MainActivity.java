package com.sheoran.innsshortss;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    int count =0 ;
    EditText title, desc,imagelink, newslink , head;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.submitdatabtn);
        title = findViewById(R.id.titleedit);
        desc = findViewById(R.id.dwscedit);
        imagelink = findViewById(R.id.imagelinkedit);
        newslink = findViewById( R.id.newslinkedit);
        head = findViewById( R.id.headedit);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                auth = FirebaseAuth.getInstance();
                database = FirebaseDatabase.getInstance();
                DatabaseReference mDb = database.getReference();

                String tittle = title.getText().toString();
                String descs = desc.getText().toString();
                String image = imagelink.getText().toString();
                String newss = newslink.getText().toString();
                String headss = head.getText().toString();



                mDb.child("News").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        FirebaseDatabase.getInstance().getReference().child("News").child(String.valueOf(count)).child("title").setValue(tittle);
                        FirebaseDatabase.getInstance().getReference().child("News").child(String.valueOf(count)).child("desc").setValue(descs);
                        FirebaseDatabase.getInstance().getReference().child("News").child(String.valueOf(count)).child("imagelink").setValue(image);
                        FirebaseDatabase.getInstance().getReference().child("News").child(String.valueOf(count)).child("newslink").setValue(newss);
                        FirebaseDatabase.getInstance().getReference().child("News").child(String.valueOf(count)).child("head").setValue(headss);

                        title.setText("");
                        desc.setText("");
                        imagelink.setText("");
                        newslink.setText("");
                        head.setText("");


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

    }
}