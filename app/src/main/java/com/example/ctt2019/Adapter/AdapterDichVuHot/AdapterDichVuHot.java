package com.example.ctt2019.Adapter.AdapterDichVuHot;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ctt2019.Diaglog.DiaglogXacNhanDangKyGoiCuoc;
import com.example.ctt2019.Model.DichVuHot.ModelDichVuHot;
import com.example.ctt2019.R;

import java.util.List;


public class AdapterDichVuHot  extends RecyclerView.Adapter<AdapterDichVuHot.ViewHolderDVH> {
    Context context;
    List<ModelDichVuHot> dichVuHotList;

    String SUB_CODE;
    String CODE;
    String token,thoiGian;

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

                Intent intent=new Intent(context, DiaglogXacNhanDangKyGoiCuoc.class);
                intent.putExtra("1",dichVuHot.getNAME());
                intent.putExtra("2",dichVuHot.getSUBCODE());
                intent.putExtra("3",dichVuHot.getPRICE());
                intent.putExtra("4",dichVuHot.getDAYCIRCLE());
                intent.putExtra("5",dichVuHot.getPARTNERNAME());
                intent.putExtra("token",token);
                intent.putExtra("time",thoiGian);
                context.startActivity(intent);




            }
        });

    }



    @Override
    public int getItemCount() {
        return dichVuHotList.size();
    }

    public void setData(String data) {

        token=data;
    }
    public void setTime(String time)

    {
        thoiGian=time;
    }
}
