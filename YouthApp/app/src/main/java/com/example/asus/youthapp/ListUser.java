package com.example.asus.youthapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.asus.youthapp.Adapter.ProgramAdapter;
import com.example.asus.youthapp.Adapter.UserAdapter;
import com.example.asus.youthapp.Model.GetProgram;
import com.example.asus.youthapp.Model.GetUser;
import com.example.asus.youthapp.Model.Program;
import com.example.asus.youthapp.Model.User;
import com.example.asus.youthapp.Rest.ApiClient;
import com.example.asus.youthapp.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListUser extends AppCompatActivity {

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
        mContext = this.getApplicationContext();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        btGet = (Button) findViewById(R.id.btGet);
        btAddData = (Button) findViewById(R.id.btAddData);
        btGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mApiInterface = ApiClient.getClient().create(ApiInterface.class);
                Call<GetUser> mPembeliCall = mApiInterface.getUser();
                mPembeliCall.enqueue(new Callback<GetUser>() {
                    @Override
                    public void onResponse(Call<GetUser> call, Response<GetUser> response) {
                        Log.d("Get Kategori",response.body().getStatus());
                        List<User> listUser = response.body().getResult();
                        mAdapter = new UserAdapter(listUser,ListUser.this);
                        mRecyclerView.setAdapter(mAdapter);
                    }

                    @Override
                    public void onFailure(Call<GetUser> call, Throwable t) {

                    }
                });
            }
        });
        btAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, InsertProgram.class);
                startActivity(intent);
            }
        });

    }
}

