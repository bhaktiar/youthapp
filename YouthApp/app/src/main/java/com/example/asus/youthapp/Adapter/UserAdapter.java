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
import com.example.asus.youthapp.Model.GetUser;
import com.example.asus.youthapp.Model.Program;
import com.example.asus.youthapp.Model.User;
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

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    List<User> listUser;

    Context context;

    public UserAdapter(List<User> listUser, Context context) {
        this.listUser = listUser;
        this.context = context;
    }

    @Override
    public UserAdapter.UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_user, parent, false);
        UserViewHolder mHolder = new UserViewHolder((view));
        return mHolder;
    }

    @Override
    public void onBindViewHolder(UserAdapter.UserViewHolder holder, final int position) {
        final ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        holder.tvNamaUser.setText(listUser.get(position).getNama());
        holder.tvInstitusiUser.setText(listUser.get(position).getInstitusi());
        holder.tvTelpUser.setText(listUser.get(position).getTelp());
        holder.tvEmailUser.setText(listUser.get(position).getEmail());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("Hapus User");
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

                        RequestBody reqIdUser =
                                MultipartBody.create(MediaType.parse("multipart/form-data"),
                                        (listUser.get(position).getId_user().isEmpty())?
                                                "" : listUser.get(position).getId_user());
                        RequestBody reqAction =
                                MultipartBody.create(MediaType.parse("multipart/form-data"), "delete");

                        Call<GetUser> callDelete = mApiInterface.deleteUser(reqIdUser,reqAction);
                        callDelete.enqueue(new Callback<GetUser>() {
                            @Override
                            public void onResponse(Call<GetUser> call, Response<GetUser> response) {
                                notifyDataSetChanged();
                            }

                            @Override
                            public void onFailure(Call<GetUser> call, Throwable t) {

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
        return listUser.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView tvNamaUser,tvInstitusiUser, tvTelpUser, tvEmailUser;

        public UserViewHolder(View itemView) {
            super(itemView);
            tvNamaUser = (TextView) itemView.findViewById(R.id.tvNamaUser);
            tvInstitusiUser = (TextView) itemView.findViewById(R.id.tvInstitusiUser);
            tvTelpUser = (TextView) itemView.findViewById(R.id.tvTelpUser);
            tvEmailUser = (TextView) itemView.findViewById(R.id.tvEmailUser);
        }
    }
}
