package com.example.asus.youthapp.Rest;

import com.example.asus.youthapp.Model.GetProgram;
import com.example.asus.youthapp.Model.GetUser;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {
    @GET("program/all")
    Call<GetProgram> getProgram();

    @Multipart
    @POST("program/all")
    Call<GetProgram> postProgram(
            @Part MultipartBody.Part file,
            @Part("nama_program") RequestBody nama_program,
            @Part("type") RequestBody type,
            @Part("kategori") RequestBody kategori,
            @Part("tempat") RequestBody tempat,
            @Part("tanggal") RequestBody tanggal,
            @Part("link") RequestBody link,
            @Part("biaya_daftar") RequestBody biaya,
            @Part("action") RequestBody action
    );

    @Multipart
    @POST("program/all")
    Call<GetProgram> putProgram(
            @Part("id_program") RequestBody id_program,
            @Part("nama_program") RequestBody nama_program,
            @Part("type") RequestBody type,
            @Part("kategori") RequestBody kategori,
            @Part("tempat") RequestBody tempat,
            @Part("tanggal") RequestBody tanggal,
            @Part("link") RequestBody link,
            @Part("biaya_daftar") RequestBody biaya,
            @Part("action") RequestBody action
    );

    @Multipart
    @POST("program/all")
    Call<GetProgram> deleteProgram(
            @Part("id_program") RequestBody id_program,
            @Part("action") RequestBody action);

    @GET("user/all")
    Call<GetUser> getUser();

    @Multipart
    @POST("user/all")
    Call<GetUser> postUser(
            @Part("nama") RequestBody nama,
            @Part("institusi") RequestBody institusi,
            @Part("telp") RequestBody telp,
            @Part("email") RequestBody email,
            @Part("action") RequestBody action
    );

    @Multipart
    @POST("user/all")
    Call<GetUser> putUser(
            @Part("nama") RequestBody nama,
            @Part("institusi") RequestBody institusi,
            @Part("telp") RequestBody telp,
            @Part("email") RequestBody email,
            @Part("action") RequestBody action
    );

    @Multipart
    @POST("user/all")
    Call<GetUser> deleteUser(
            @Part("id_user") RequestBody id_user,
            @Part("action") RequestBody action);
}

