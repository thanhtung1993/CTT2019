package com.example.ctt2019.Adapter.AdapterGoiCuocSuDung;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ctt2019.Model.GoiCuocSuDung.ModelGoiCuocSuDung;
import com.example.ctt2019.Model.Home.ModelHome;
import com.example.ctt2019.R;

import java.util.List;

public class AdapterGoiCuocSuDung extends RecyclerView.Adapter<AdapterGoiCuocSuDung.ViewHolder> {
    private List<ModelHome> modelGoiCuocSuDungList;
    private Context context;

    public AdapterGoiCuocSuDung(List<ModelHome> modelGoiCuocSuDungList, Context context) {
        this.modelGoiCuocSuDungList = modelGoiCuocSuDungList;
        this.context = context;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView txtTenGoiCuoc;
        protected TextView txtGiaTien;
        protected TextView txtChuKiCuoc;
        protected TextView txtNCC;
        protected TextView txtDKLandau;
        protected TextView txtThoiGianGiaHanGanNhat;
        protected TextView txtTongSoTienDaNop;
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
        }
    }
    @NonNull
    @Override
    public AdapterGoiCuocSuDung.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.custom_goicuocsudung,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterGoiCuocSuDung.ViewHolder viewHolder, int i) {
        ModelGoiCuocSuDung modelGoiCuocSuDung=new ModelGoiCuocSuDung();
        viewHolder.txtTenGoiCuoc.setText(modelGoiCuocSuDung.getPartnerId());

    }

    @Override
    public int getItemCount() {
        return modelGoiCuocSuDungList.size();
    }


}
