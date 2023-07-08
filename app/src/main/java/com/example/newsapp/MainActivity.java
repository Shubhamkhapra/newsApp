package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    RecyclerView recview;
    myadapter adapter;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getSupportActionBar().hide();

        recview = (RecyclerView)findViewById(R.id.recview);
        progressBar = findViewById(R.id.progressBar);
        recview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("news"), model.class)
                        .build();

        adapter = new myadapter(options);
        recview.setAdapter(adapter);

        progressBar.setVisibility(View.VISIBLE);

    }


    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}