package com.example.ctt2019.Adapter.AdapterHoTro;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ctt2019.Model.HoTro.ModelBinhLuan;
import com.example.ctt2019.R;

import java.util.List;

public class AdapterTraLoiBinhLuan extends RecyclerView.Adapter<AdapterTraLoiBinhLuan.ViewHolder> {

   private Context context;
   private List<ModelBinhLuan> modelBinhLuanList;

    public AdapterTraLoiBinhLuan(Context context, List<ModelBinhLuan> modelBinhLuanList) {
        this.context = context;
        this.modelBinhLuanList = modelBinhLuanList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtMessage;
        public TextView txtAdmin;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtAdmin = itemView.findViewById(R.id.txtadmintraloi);
            txtMessage = itemView.findViewById(R.id.txtMessage);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(i,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            ModelBinhLuan modelBinhLuan=modelBinhLuanList.get(i);
            viewHolder.txtMessage.setText(modelBinhLuan.getDESCRIBE());
    }

    @Override
    public int getItemCount() {
        return modelBinhLuanList.size();
    }

    @Override
    public int getItemViewType(int position) {

              if (modelBinhLuanList.get(position).getRESPONDENT().equals("Admin")) {
                  return R.layout.layout_item_hotro_admin;
              } else {
                  return R.layout.layout_item_hotro_client;
              }
          }

        }



