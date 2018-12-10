package com.example.asus.youthapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.asus.youthapp.Model.GetProgram;
import com.example.asus.youthapp.Rest.ApiClient;
import com.example.asus.youthapp.Rest.ApiInterface;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class insert_program2 extends AppCompatActivity {

    Context mContext;
    Button btAddBack, btAddData;
    EditText edtAddIdProgram, edtAddNamaProgram, edtAddTypeProgram, edtAddKategoriProgram, edtAddTempatProgram;
    EditText edtAddTanggalProgram, edtAddLinkProgram, edtAddBiayaProgram;
    TextView tvAddMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_program2);

        mContext = getApplicationContext();
        edtAddIdProgram = (EditText) findViewById(R.id.edtAddIdProgram);
        edtAddNamaProgram = (EditText) findViewById(R.id.edtAddNamaProgram);
        edtAddTypeProgram = (EditText) findViewById(R.id.edtAddTypeProgram);
        edtAddKategoriProgram = (EditText) findViewById(R.id.edtAddKategoriProgram);
        edtAddTempatProgram = (EditText) findViewById(R.id.edtAddTempatProgram);
        edtAddTanggalProgram = (EditText) findViewById(R.id.edtAddTanggalProgram);
        edtAddLinkProgram = (EditText) findViewById(R.id.edtAddLinkProgram);
        edtAddBiayaProgram = (EditText) findViewById(R.id.edtAddBiayaProgram);

        btAddData = (Button) findViewById(R.id.btAddData);
        btAddBack = (Button) findViewById(R.id.btAddBack);
        tvAddMessage = (TextView) findViewById(R.id.tvAddMessage);

        btAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);

                MultipartBody.Part body = null;
                RequestBody reqNama = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (edtAddNamaProgram.getText().toString().isEmpty())?"":edtAddNamaProgram.getText().toString());
                RequestBody reqType = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (edtAddTypeProgram.getText().toString().isEmpty())?"":edtAddTypeProgram.getText().toString());
                RequestBody reqKategori = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (edtAddKategoriProgram.getText().toString().isEmpty())?"":edtAddKategoriProgram.getText().toString());
                RequestBody reqTempat = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (edtAddTempatProgram.getText().toString().isEmpty())?"":edtAddTempatProgram.getText().toString());
                RequestBody reqTanggal = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (edtAddTanggalProgram.getText().toString().isEmpty())?"":edtAddTanggalProgram.getText().toString());
                RequestBody reqLink = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (edtAddLinkProgram.getText().toString().isEmpty())?"":edtAddLinkProgram.getText().toString());
                RequestBody reqBiaya = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (edtAddBiayaProgram.getText().toString().isEmpty())?"":edtAddBiayaProgram.getText().toString());
                RequestBody reqAction = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        "insert");
                Call<GetProgram> mProgramCall = mApiInterface.postProgram(body, reqNama, reqType,
                        reqKategori, reqTempat, reqTanggal, reqLink, reqBiaya, reqAction );
                mProgramCall.enqueue(new Callback<GetProgram>() {
                    @Override
                    public void onResponse(Call<GetProgram> call, Response<GetProgram> response) {
//                      Log.d("Insert Retrofit",response.body().getMessage());
                        if (response.body().getStatus().equals("failed")){
                            tvAddMessage.setText("Retrofit Insert \n Status = "+response.body()
                                    .getStatus()+"\n"+
                                    "Message = "+response.body().getMessage()+"\n");
                        }else{
                            String detail = "\n"+
                                    "id_program = "+response.body().getResult().get(0).getId_program()+"\n"+
                                    "nama_program = "+response.body().getResult().get(0).getNama_program()+"\n"+
                                    "type = "+response.body().getResult().get(0).getType()+"\n"+
                                    "kategori = "+response.body().getResult().get(0).getKategori()+"\n"+
                                    "tempat = "+response.body().getResult().get(0).getTempat()+"\n"+
                                    "tanggal = "+response.body().getResult().get(0).getTanggal()+"\n"+
                                    "link = "+response.body().getResult().get(0).getLink()+"\n"+
                                    "biaya_daftar = "+response.body().getResult().get(0).getBiaya_daftar()
                                    +"\n";
                            tvAddMessage.setText("Retrofit Insert \n Status = "+response.body().getStatus()+"\n"+
                                    "Message = "+response.body().getMessage()+detail);
                        }
                    }

                    @Override
                    public void onFailure(Call<GetProgram> call, Throwable t) {
//                      Log.d("Insert Retrofit", t.getMessage());
                        tvAddMessage.setText("Retrofit Insert Failure \n Status = "+ t.getMessage
                                ());
                    }
                });
            }
        });

        btAddBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ListProgram.class);
                startActivity(intent);
            }
        });
    }
}
