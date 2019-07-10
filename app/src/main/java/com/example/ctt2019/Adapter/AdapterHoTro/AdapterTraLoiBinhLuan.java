package com.example.ctt2019.Adapter.AdapterHoTro;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.ctt2019.Activity.SuaBinhLuanActivity;
import com.example.ctt2019.Activity.XacNhanXoaActivity;
import com.example.ctt2019.Model.HoTro.ModelBinhLuan;
import com.example.ctt2019.R;

import java.util.List;

public class AdapterTraLoiBinhLuan extends RecyclerView.Adapter<AdapterTraLoiBinhLuan.ViewHolder> {

   private Context context;
   private List<ModelBinhLuan> modelBinhLuanList;
    String token,thoiGian,ten;

    public AdapterTraLoiBinhLuan(Context context, List<ModelBinhLuan> modelBinhLuanList) {
        this.context = context;
        this.modelBinhLuanList = modelBinhLuanList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtMessage;
        public TextView txtAdmin;
        public TextView txtTraLoiKH;
        CardView cv;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtAdmin = itemView.findViewById(R.id.txtadmintraloi);
            txtMessage = itemView.findViewById(R.id.txtMessage);
            txtTraLoiKH=itemView.findViewById(R.id.txtTenKHHT);
            cv=itemView.findViewById(R.id.cv);

        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(i,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
            final ModelBinhLuan modelBinhLuan=modelBinhLuanList.get(i);
            viewHolder.txtMessage.setText(modelBinhLuan.getDESCRIBE());

     if (modelBinhLuan.getRESPONDENT().equals("User")) {
         viewHolder.txtTraLoiKH.setText(ten);
         viewHolder.cv.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 PopupMenu popupMenu = new PopupMenu(context, viewHolder.cv);
                 popupMenu.inflate(R.menu.option_menu);
                 popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                     @Override
                     public boolean onMenuItemClick(MenuItem item) {
                         switch (item.getItemId()) {
                             case R.id.mnu_item_edit:
                                 Intent intent = new Intent(context, SuaBinhLuanActivity.class);
                                 intent.putExtra("psMsisdn", modelBinhLuan.getMSISDN());
                                 intent.putExtra("psId", modelBinhLuan.getID());
                                 intent.putExtra("psContent", modelBinhLuan.getDESCRIBE());
                                 intent.putExtra("token", token);
                                 context.startActivity(intent);
                                 break;
                             case R.id.mnu_item_delete:
                                 Intent intent1 = new Intent(context, XacNhanXoaActivity.class);
                                 intent1.putExtra("token", token);
                                 intent1.putExtra("id", modelBinhLuan.getID());
                                 context.startActivity(intent1);

                                 break;
                         }
                         return false;
                     }
                 });
                 popupMenu.show();
             }
         });
     }
  }

    @Override
    public int getItemCount() {
        return modelBinhLuanList.size();
    }

    @Override
    public int getItemViewType(int position) {

           if (modelBinhLuanList.get(position).getRESPONDENT().equals("Admin")) {
                return R.layout.layout_item_hotro_admin;
           }
                 return R.layout.layout_item_hotro_client;
    }




    public void setData(String data) {

        token=data;
    }
    public void setTime(String data) {

        thoiGian=data;
    }
    public void setTen(String data) {

        ten=data;
    }


        }



