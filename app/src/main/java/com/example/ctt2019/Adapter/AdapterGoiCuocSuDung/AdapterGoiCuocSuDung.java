package com.example.ctt2019.Adapter.AdapterGoiCuocSuDung;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ctt2019.Diaglog.DiaglogDelete;
import com.example.ctt2019.Model.GoiCuocSuDung.ModelGoiCuocSuDung;
import com.example.ctt2019.R;

import java.util.List;

public class AdapterGoiCuocSuDung extends RecyclerView.Adapter<AdapterGoiCuocSuDung.ViewHolder> {
    private List<ModelGoiCuocSuDung> modelGoiCuocSuDungList;
    private Context context;

    String token,thoiGian;

    public AdapterGoiCuocSuDung(Context context, List<ModelGoiCuocSuDung> modelGoiCuocSuDungList) {
        this.context=context;
        this.modelGoiCuocSuDungList=modelGoiCuocSuDungList;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView txtTenGoiCuoc;
        protected TextView txtGiaTien;
        protected TextView txtChuKiCuoc;
        protected TextView txtNCC;
        protected TextView txtDKLandau;
        protected TextView txtThoiGianGiaHanGanNhat;
        protected TextView txtTongSoTienDaNop;
        protected TextView txtHuyGoiDVH;
        protected Button btnHuyGoiCuoc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenGoiCuoc=itemView.findViewById(R.id.txtTenGoiCuoc);
            txtGiaTien=itemView.findViewById(R.id.txtGiaTien);
            txtChuKiCuoc=itemView.findViewById(R.id.txtChuKiCuoc);
            txtNCC=itemView.findViewById(R.id.txtNCC);
            txtDKLandau=itemView.findViewById(R.id.txtDKLandau);
            txtThoiGianGiaHanGanNhat=itemView.findViewById(R.id.txtThoiGianGiaHanGanNhat);
            txtTongSoTienDaNop=itemView.findViewById(R.id.txtTongSoTienDaNop);
            btnHuyGoiCuoc=itemView.findViewById(R.id.btnHuyGoiCuoc);
            txtHuyGoiDVH=itemView.findViewById(R.id.txtHuyGoiDVH);
        }
    }
    @NonNull
    @Override
    public AdapterGoiCuocSuDung.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.custom_goicuocsudung,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterGoiCuocSuDung.ViewHolder viewHolder, int i) {
        final ModelGoiCuocSuDung modelGoiCuocSuDung = modelGoiCuocSuDungList.get(i);
        viewHolder.txtTenGoiCuoc.setText(modelGoiCuocSuDung.getSUB_CODE());
        viewHolder.txtGiaTien.setText(modelGoiCuocSuDung.getPRICE());
        viewHolder.txtChuKiCuoc.setText(modelGoiCuocSuDung.getDAY_CIRCLE() + " ngày ");
        viewHolder.txtNCC.setText(modelGoiCuocSuDung.getPARTNER_NAME());
        viewHolder.txtDKLandau.setText(modelGoiCuocSuDung.getTIME_START());
        viewHolder.txtThoiGianGiaHanGanNhat.setText(modelGoiCuocSuDung.getLASTDATE_REMIND());
        viewHolder.txtTongSoTienDaNop.setText("10.000.000 VNĐ");
        viewHolder.txtHuyGoiDVH.setText(modelGoiCuocSuDung.getSYNTAX_ID());
      //todo:hủy gói cước
        viewHolder.btnHuyGoiCuoc.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent iThongBao=new Intent(context, DiaglogDelete.class);
                iThongBao.putExtra("1",modelGoiCuocSuDung.getSUB_CODE());
                iThongBao.putExtra("2",modelGoiCuocSuDung.getCODE());
                iThongBao.putExtra("3",modelGoiCuocSuDung.getPARTNER_NAME());
                iThongBao.putExtra("4",modelGoiCuocSuDung.getDAY_CIRCLE());
                iThongBao.putExtra("5",modelGoiCuocSuDung.getPRICE());
                iThongBao.putExtra("6",modelGoiCuocSuDung.getLASTDATE_REMIND());
                iThongBao.putExtra("7","10.000.000 VNĐ");
                iThongBao.putExtra("token",token);
                iThongBao.putExtra("time",thoiGian);
                iThongBao.putExtra("PARTNER_CODE",modelGoiCuocSuDung.getPARTNER_CODE());
                iThongBao.putExtra("CODE",modelGoiCuocSuDung.getCODE());
                iThongBao.putExtra("MSISDN",modelGoiCuocSuDung.getMSISDN());
                iThongBao.putExtra("PREFIX",modelGoiCuocSuDung.getPREFIX());
                context.startActivity(iThongBao);


            }
        });
    }



    @Override
    public int getItemCount() {
        return modelGoiCuocSuDungList.size();
    }
    public void setData(String data) {

        token=data;
    }
    public void setTime(String time)

    {
        thoiGian=time;
    }

}
