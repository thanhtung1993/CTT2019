package com.example.ctt2019.Adapter.AdapterHoTro;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ctt2019.API.RetroClient;
import com.example.ctt2019.Activity.TraLoiHoTroActivity;
import com.example.ctt2019.Model.HoTro.ModelHoTro;
import com.example.ctt2019.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterHoTro extends RecyclerView.Adapter<AdapterHoTro.ViewHolder> {

    Context context;
    List<ModelHoTro> modelHoTroList;
    String token;
    String ten;


    public AdapterHoTro(Context context, List<ModelHoTro> modelHoTroList) {
        this.context = context;
        this.modelHoTroList = modelHoTroList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtStatus;
        public TextView txtTenHoTro;
        public TextView txtThoiGianHoTro;
        RelativeLayout relativeLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtStatus=itemView.findViewById(R.id.txtStatus);
            txtTenHoTro =itemView.findViewById(R.id.txtTenHoTro);
            txtThoiGianHoTro=itemView.findViewById(R.id.txtThoiGianHoTro);
            relativeLayout=itemView.findViewById(R.id.relativelayout);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_item_hotro,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
       final ModelHoTro modelHoTro=modelHoTroList.get(i);
      if (modelHoTro.getSTATUS().equals("Câu hỏi"))
       {
          viewHolder.txtStatus.setText(modelHoTro.getDESCRIBE());
          viewHolder.txtThoiGianHoTro.setText("Đã gửi lúc "+modelHoTro.getDESCRIBE_DATE());
       }
      viewHolder.txtTenHoTro.setText(token);
              viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent iThemBinhLuan=new Intent(context, TraLoiHoTroActivity.class);
              context.startActivity(iThemBinhLuan);


          }
      });
      //todo: set tên người gửi
        String constr="get_customer_infor";
        String psMsisdn="0987023195";

        RetroClient.get_customer_infor(constr, psMsisdn, token, new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response!=null && response.code()== HttpURLConnection.HTTP_OK)
                {
                    try {
                        String body=response.body().string();
                        JSONArray jsonArray=new JSONArray(body);
                        if (jsonArray.length()>0)
                        {
                            JSONObject object=jsonArray.getJSONObject(0);
                            if (object!=null)
                            {
                                String sdt=object.optString("MSISDN");
                               String  ten=object.optString("NAME");

                                 viewHolder.txtTenHoTro.setText(ten);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

      viewHolder.txtTenHoTro.setText(ten);




        // viewHolder.txtStatus.setText("Nội dung được viết ở đây");
    }

    @Override
    public int getItemCount() {
        return modelHoTroList.size();
    }


    public void setData(String data) {

       token=data;
    }

    private void thongTinNguoiDung() {


    }

}
