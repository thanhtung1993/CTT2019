package com.example.ctt2019.Adapter.AdapterDichVuHot;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ctt2019.API.RetroClient;
import com.example.ctt2019.Model.DichVuHot.ModelDichVuHot;
import com.example.ctt2019.R;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AdapterDichVuHot  extends RecyclerView.Adapter<AdapterDichVuHot.ViewHolderDVH> {
    Context context;
    List<ModelDichVuHot> dichVuHotList;

    String SUB_CODE;
    String CODE;

    public AdapterDichVuHot(Context context, List<ModelDichVuHot> dichVuHotList) {
        this.context = context;
        this.dichVuHotList = dichVuHotList;
    }

    public class ViewHolderDVH extends RecyclerView.ViewHolder {
        TextView txtnccdvhot,txtdvdvhot,txttengoicuocdvh,txtgiadvh,txtchukicuocdvh;
        Button btnDKDVH;
        public ViewHolderDVH(@NonNull View itemView) {
            super(itemView);
            txtnccdvhot=itemView.findViewById(R.id.txtnccdvhot);
            txtdvdvhot=itemView.findViewById(R.id.txtdvdvhot);
            txttengoicuocdvh=itemView.findViewById(R.id.txttengoicuocdvh);
            txtgiadvh=itemView.findViewById(R.id.txtgiadvh);
            txtchukicuocdvh=itemView.findViewById(R.id.txtchukicuocdvh);
            btnDKDVH=itemView.findViewById(R.id.btndkdvh);


        }
    }

    private void dangKyThanhCong() {
        JsonObject object=new JsonObject();
        object.addProperty("command","REGISTER");
        object.addProperty("tocken","tocken");
        object.addProperty("checksum","checksum");
        object.addProperty("msisdn","0934435389");

        object.addProperty("cpcode","GR");
        object.addProperty("servicecode",CODE);
        object.addProperty("subcode",SUB_CODE);
        object.addProperty("shortcode","9029_GR");

        RetroClient.register_unregister(object, new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Toast.makeText(context,"Đăng ký thành công",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });

    }


    @NonNull
    @Override
    public ViewHolderDVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view=LayoutInflater.from(context).inflate(R.layout.custom_item_dvhot,viewGroup,false);
        return new ViewHolderDVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDVH viewHolderDVH, int i) {

        final ModelDichVuHot dichVuHot=dichVuHotList.get(i);
        viewHolderDVH.txtnccdvhot.setText(dichVuHot.getPARTNERNAME());
        if (dichVuHot.getNAME().equals("Th&#244;ng tin x&#7893; s&#7889;"))
        {
            viewHolderDVH.txtdvdvhot.setText("Thông tin xổ số ");
        }
        else
        {
            viewHolderDVH.txtdvdvhot.setText(dichVuHot.getNAME());
        }

        viewHolderDVH.txttengoicuocdvh.setText(dichVuHot.getSUBCODE());
        viewHolderDVH.txtgiadvh.setText(dichVuHot.getPRICE());
        viewHolderDVH.txtchukicuocdvh.setText(dichVuHot.getDAYCIRCLE()+" ngày ");



        viewHolderDVH.btnDKDVH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SUB_CODE=dichVuHot.getSUBCODE();
                CODE=dichVuHot.getCODE();
                Toast.makeText(context,"Bạn chọn"+CODE,Toast.LENGTH_LONG).show();
                dangKyThanhCong();



            }
        });

    }



    @Override
    public int getItemCount() {
        return dichVuHotList.size();
    }


}
