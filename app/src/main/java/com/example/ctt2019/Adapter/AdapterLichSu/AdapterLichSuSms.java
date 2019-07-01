package com.example.ctt2019.Adapter.AdapterLichSu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ctt2019.Model.Lichsusms.ModelSms;
import com.example.ctt2019.R;

import java.util.List;


public class AdapterLichSuSms extends RecyclerView.Adapter<AdapterLichSuSms.ViewHolder>{
  private List<ModelSms> smslist;
  private Context context;


    public AdapterLichSuSms(List<ModelSms> smslist, Context context) {
        this.smslist = smslist;
        this.context = context;
    }




    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView txtSTT;
        protected TextView txtNgay;
        protected TextView txtNoiDung;
        protected TextView txtLoai;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSTT=itemView.findViewById(R.id.txtSTT);
            txtNgay=itemView.findViewById(R.id.txtNgay);
            txtNoiDung=itemView.findViewById(R.id.txtNoiDung);
            txtLoai=itemView.findViewById(R.id.txtLoai);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.table_list_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ViewHolder holder=viewHolder;
        int rowPos=holder.getAdapterPosition();

        if (rowPos==0)
        {
            holder.txtSTT.setBackgroundResource(R.drawable.table_header_cell_bg);
            holder.txtNgay.setBackgroundResource(R.drawable.table_header_cell_bg);
            holder.txtNoiDung.setBackgroundResource(R.drawable.table_header_cell_bg);
            holder.txtLoai.setBackgroundResource(R.drawable.table_header_cell_bg);

            holder.txtSTT.setText("STT");
            holder.txtNgay.setText("Ngày");
            holder.txtNoiDung.setText("Nội dung");
            holder.txtLoai.setText("Loại");
        }
        else
        {
            ModelSms modelSms=smslist.get(rowPos-1);
            holder.txtSTT.setBackgroundResource(R.drawable.table_content_cell_bg);
            holder.txtNgay.setBackgroundResource(R.drawable.table_content_cell_bg);
            holder.txtNoiDung.setBackgroundResource(R.drawable.table_content_cell_bg);
            holder.txtLoai.setBackgroundResource(R.drawable.table_content_cell_bg);

            holder.txtSTT.setText(modelSms.getSTT());
            holder.txtNgay.setText(modelSms.getDATE_LOG());
            holder.txtNoiDung.setText(modelSms.getCONTENT());
            holder.txtLoai.setText(modelSms.getTYPE_());
        }

    }

    @Override
    public int getItemCount() {
        return smslist.size() + 1;

    }


}
