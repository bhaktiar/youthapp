package com.example.asus.youthapp.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asus.youthapp.Model.GetProgram;
import com.example.asus.youthapp.Model.Program;
import com.example.asus.youthapp.R;
import com.example.asus.youthapp.Rest.ApiClient;
import com.example.asus.youthapp.Rest.ApiInterface;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProgramAdapter extends RecyclerView.Adapter<ProgramAdapter.ProgramViewHolder> {

    List<Program> listProgram;
    Context context;

    public ProgramAdapter(List<Program> listProgram, Context context) {
        this.listProgram = listProgram;
        this.context = context;
    }

    @Override
    public ProgramAdapter.ProgramViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_program, parent, false);
        ProgramViewHolder mHolder = new ProgramViewHolder((view));
        return mHolder;
    }

    @Override
    public void onBindViewHolder(ProgramAdapter.ProgramViewHolder holder, final int position) {
        final ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        holder.tvNamaProgram.setText(listProgram.get(position).getNama_program());
        holder.tvTypeProgram.setText(listProgram.get(position).getType());
        holder.tvKategoriProgram.setText(listProgram.get(position).getKategori());
        holder.tvTempatProgram.setText(listProgram.get(position).getTempat());
        holder.tvTanggalProgram.setText(listProgram.get(position).getTanggal());
        holder.tvLinkProgram.setText(listProgram.get(position).getLink());
        holder.tvBiaya_daftarProgram.setText(listProgram.get(position).getBiaya_daftar());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("Hapus Program");
                alertDialog.setMessage("Anda Yakin Ingin Menghapus ?");
                alertDialog.setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertDialog.setNegativeButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        RequestBody reqIdProgram =
                                MultipartBody.create(MediaType.parse("multipart/form-data"),
                                        (listProgram.get(position).getId_program().isEmpty())?
                                                "" : listProgram.get(position).getId_program());
                        RequestBody reqAction =
                                MultipartBody.create(MediaType.parse("multipart/form-data"), "delete");

                        Call<GetProgram> callDelete = mApiInterface.deleteProgram(reqIdProgram,reqAction);
                        callDelete.enqueue(new Callback<GetProgram>() {
                            @Override
                            public void onResponse(Call<GetProgram> call, Response<GetProgram> response) {
                                notifyDataSetChanged();
                            }

                            @Override
                            public void onFailure(Call<GetProgram> call, Throwable t) {

                            }

                        });

                    }
                });

                AlertDialog dialog = alertDialog.create();
                dialog.show();


            }
        });
    }

    @Override
    public int getItemCount() {
        return listProgram.size();
    }

    public class ProgramViewHolder extends RecyclerView.ViewHolder {
        TextView  tvNamaProgram,tvTypeProgram, tvKategoriProgram, tvTempatProgram, tvTanggalProgram, tvLinkProgram, tvBiaya_daftarProgram;

        public ProgramViewHolder(View itemView) {
            super(itemView);
            tvNamaProgram = (TextView) itemView.findViewById(R.id.tvNamaProgram);
            tvTypeProgram = (TextView) itemView.findViewById(R.id.tvTypeProgram);
            tvKategoriProgram = (TextView) itemView.findViewById(R.id.tvKategoriProgram);
            tvTempatProgram = (TextView) itemView.findViewById(R.id.tvTempatProgram);
            tvTanggalProgram = (TextView) itemView.findViewById(R.id.tvTanggalProgram);
            tvLinkProgram = (TextView) itemView.findViewById(R.id.tvLinkProgram);
            tvBiaya_daftarProgram = (TextView) itemView.findViewById(R.id.tvBiaya_daftarProgram);
        }
    }
}
