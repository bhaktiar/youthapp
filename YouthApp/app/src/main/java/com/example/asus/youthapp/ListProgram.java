package com.example.asus.youthapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.asus.youthapp.Adapter.ProgramAdapter;
import com.example.asus.youthapp.Model.GetProgram;
import com.example.asus.youthapp.Model.Program;
import com.example.asus.youthapp.Rest.ApiClient;
import com.example.asus.youthapp.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListProgram extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    Context mContext;
    ApiInterface mApiInterface;
    Button btGet,btAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_program);
        mContext = getApplicationContext();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        btGet = (Button) findViewById(R.id.btGet);
        btAddData = (Button) findViewById(R.id.btAddData);

        btGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mApiInterface = ApiClient.getClient().create(ApiInterface.class);
                Call<GetProgram> mPembeliCall = mApiInterface.getProgram();
                mPembeliCall.enqueue(new Callback<GetProgram>() {
                    @Override
                    public void onResponse(Call<GetProgram> call, Response<GetProgram> response) {
                        Log.d("Get Kategori",response.body().getStatus());
                        List<Program> listProgram = response.body().getResult();
                        mAdapter = new ProgramAdapter(listProgram,ListProgram.this);
                        mRecyclerView.setAdapter(mAdapter);
                    }

                    @Override
                    public void onFailure(Call<GetProgram> call, Throwable t) {

                    }
                });
            }
        });

        btAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), insert_program2.class);
                startActivity(i);
            }
        });
    }
}
