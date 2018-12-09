package com.example.asus.youthapp.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetProgram {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("result")
    @Expose
    private List<Program> result = null;
    @SerializedName("message")
    @Expose
    private Boolean message;
    public GetProgram() {}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Program> getResult() {
        return result;
    }

    public void setResult(List<Program> result) {
        this.result = result;
    }

    public Boolean getMessage() {
        return message;
    }

    public void setMessage(Boolean message) {
        this.message = message;
    }

}
