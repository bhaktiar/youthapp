package com.example.asus.youthapp.Model;

import com.google.gson.annotations.SerializedName;

public class Program {
    @SerializedName("id_program")
    private String id_program;
    @SerializedName("nama_program")
    private String nama_program;
    @SerializedName("type")
    private String type;
    @SerializedName("kategori")
    private String kategori;
    @SerializedName("tempat")
    private String tempat;
    @SerializedName("tanggal")
    private String tanggal;
    @SerializedName("link")
    private String link;
    @SerializedName("biaya_daftar")
    private String biaya_daftar;
    private String action;

    public Program(String id_program, String nama_program, String type, String kategori, String tempat, String tanggal, String link, String biaya_daftar, String action) {
        this.id_program = id_program;
        this.nama_program = nama_program;
        this.type = type;
        this.kategori = kategori;
        this.tempat = tempat;
        this.tanggal = tanggal;
        this.link = link;
        this.biaya_daftar = biaya_daftar;
        this.action = action;
    }

    public String getId_program() {
        return id_program;
    }

    public void setId_program(String id_program) {
        this.id_program = id_program;
    }

    public String getNama_program() {
        return nama_program;
    }

    public void setNama_program(String nama_program) {
        this.nama_program = nama_program;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getBiaya_daftar() { return biaya_daftar; }

    public void setBiaya_daftar(String biaya_daftar) {
        this.biaya_daftar = biaya_daftar;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}

