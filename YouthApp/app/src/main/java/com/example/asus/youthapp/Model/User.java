package com.example.asus.youthapp.Model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id_user")
    private String id_user;
    @SerializedName("nama")
    private String nama;
    @SerializedName("institusi")
    private String institusi;
    @SerializedName("telp")
    private String telp;
    @SerializedName("email")
    private String email;
    private String action;

    public User(String id_user, String nama, String institusi, String telp, String email, String action) {
        this.id_user = id_user;
        this.nama = nama;
        this.institusi = institusi;
        this.telp = telp;
        this.email = email;
        this.action = action;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getInstitusi() {
        return institusi;
    }

    public void setInstitusi(String institusi) {
        this.institusi = institusi;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
