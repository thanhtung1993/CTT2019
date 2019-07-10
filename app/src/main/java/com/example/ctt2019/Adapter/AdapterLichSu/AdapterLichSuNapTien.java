package com.example.ctt2019.Adapter.AdapterLichSu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ctt2019.Model.Lichsunaptien.ModelLichSuNapTien;
import com.example.ctt2019.R;

import java.util.List;


public class AdapterLichSuNapTien extends RecyclerView.Adapter {
    private List<ModelLichSuNapTien> LichSuNapTienList;
    private Context context;

    public AdapterLichSuNapTien(List<ModelLichSuNapTien> lichSuNapTienList, Context context) {
        this.LichSuNapTienList = lichSuNapTienList;
        this.context = context;
    }

    public class RowViewHolder extends RecyclerView.ViewHolder

    {
        protected TextView txtSTT;
        protected TextView txtNgay;
        protected TextView txtSoTien;
        protected TextView txtTrangThai;

        public RowViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSTT=itemView.findViewById(R.id.txtSTTLichSuNapTien);
            txtNgay=itemView.findViewById(R.id.txtNgay);
            txtSoTien=itemView.findViewById(R.id.txtSoTien);
            txtTrangThai=itemView.findViewById(R.id.txtTrangThai);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.table_list_itemnaptien,viewGroup,false);
        return new RowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        RowViewHolder rowViewHolderNapTien= (RowViewHolder) viewHolder;

        int row=rowViewHolderNapTien.getAdapterPosition();

        if (row ==0)
        {
            rowViewHolderNapTien.txtSTT.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolderNapTien.txtNgay.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolderNapTien.txtSoTien.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolderNapTien.txtTrangThai.setBackgroundResource(R.drawable.table_header_cell_bg);

            rowViewHolderNapTien.txtSTT.setText("STT");
            rowViewHolderNapTien.txtNgay.setText("Ngày");
            rowViewHolderNapTien.txtSoTien.setText("Số tiền");
            rowViewHolderNapTien.txtTrangThai.setText("Trạng thái");

        }
        else
        {
            ModelLichSuNapTien model=LichSuNapTienList.get(row-1);
            rowViewHolderNapTien.txtSTT.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolderNapTien.txtNgay.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolderNapTien.txtSoTien.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolderNapTien.txtTrangThai.setBackgroundResource(R.drawable.table_content_cell_bg);
                try {
                    rowViewHolderNapTien.txtSTT.setText(model.getSTT());
                    rowViewHolderNapTien.txtNgay.setText(model.getDATE_LOG());
                    rowViewHolderNapTien.txtSoTien.setText(model.getTOTAL_AMOUNT());
                    if (model.getRESULT().equals("Th&#224;nh c&#244;ng"))
                    {
                        rowViewHolderNapTien.txtTrangThai.setText("Thành công");
                    }
                    else
                    {rowViewHolderNapTien.txtTrangThai.setText("Thất bại");}

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                        }
    }


    @Override
    public int getItemCount() {
        return LichSuNapTienList.size()+1;
    }
}
