package com.example.asus.youthapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.asus.youthapp.Model.GetProgram;
import com.example.asus.youthapp.Model.GetUser;
import com.example.asus.youthapp.Rest.ApiClient;
import com.example.asus.youthapp.Rest.ApiInterface;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class insert_user extends AppCompatActivity {

    Context mContext;
    Button btAddBack, btAddData;
    EditText edtAddIdUser, edtAddNamaUser, edtAddInstitusiUser, edtAddTelpUser, edtAddEmailUser;
    TextView tvAddMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_user);

        mContext = getApplicationContext();
        edtAddIdUser = (EditText) findViewById(R.id.edtAddIdUser);
        edtAddNamaUser = (EditText) findViewById(R.id.edtAddNamaUser);
        edtAddInstitusiUser = (EditText) findViewById(R.id.edtAddInstitusiUser);
        edtAddTelpUser = (EditText) findViewById(R.id.edtAddTelpUser);
        edtAddEmailUser = (EditText) findViewById(R.id.edtAddEmailUser);

        btAddData = (Button) findViewById(R.id.btAddData);
        btAddBack = (Button) findViewById(R.id.btAddBack);
        tvAddMessage = (TextView) findViewById(R.id.tvAddMessage);

        btAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);

                MultipartBody.Part body = null;
                RequestBody reqNama = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (edtAddNamaUser.getText().toString().isEmpty())?"":edtAddNamaUser.getText().toString());
                RequestBody reqInstitusi = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (edtAddInstitusiUser.getText().toString().isEmpty())?"":edtAddInstitusiUser.getText().toString());
                RequestBody reqTelp = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (edtAddTelpUser.getText().toString().isEmpty())?"":edtAddTelpUser.getText().toString());
                RequestBody reqEmail = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (edtAddEmailUser.getText().toString().isEmpty())?"":edtAddEmailUser.getText().toString());
                RequestBody reqAction = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        "insert");
                Call<GetUser> mProgramCall = mApiInterface.postUser(body, reqNama, reqInstitusi,
                        reqTelp, reqEmail, reqAction );
                mProgramCall.enqueue(new Callback<GetUser>() {
                    @Override
                    public void onResponse(Call<GetUser> call, Response<GetUser> response) {
//                      Log.d("Insert Retrofit",response.body().getMessage());
                        if (response.body().getStatus().equals("failed")){
                            tvAddMessage.setText("Retrofit Insert \n Status = "+response.body()
                                    .getStatus()+"\n"+
                                    "Message = "+response.body().getMessage()+"\n");
                        }else{
                            String detail = "\n"+
                                    "id_user = "+response.body().getResult().get(0).getId_user()+"\n"+
                                    "nama_user = "+response.body().getResult().get(0).getNama()+"\n"+
                                    "institusi = "+response.body().getResult().get(0).getInstitusi()+"\n"+
                                    "telp = "+response.body().getResult().get(0).getTelp()+"\n"+
                                    "email = "+response.body().getResult().get(0).getEmail()
                                    +"\n";
                            tvAddMessage.setText("Retrofit Insert \n Status = "+response.body().getStatus()+"\n"+
                                    "Message = "+response.body().getMessage()+detail);
                        }
                    }

                    @Override
                    public void onFailure(Call<GetUser> call, Throwable t) {
//                      Log.d("Insert Retrofit", t.getMessage());
                        tvAddMessage.setText("Retrofit Insert Failure \n Status = "+ t.getMessage
                                ());
                    }
                });
            }
        });
    }
}
