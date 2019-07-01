package com.example.ctt2019.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ctt2019.API.RetroClient;
import com.example.ctt2019.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CapNhatActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnCapNhat;
    TextView txtDTCN,txtTenCN;
    String token=null;
    String sdt;
    String thoiGian;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_capnhat);

        txtDTCN=findViewById(R.id.txtDTCN);
        txtTenCN=findViewById(R.id.txtTenCN);
        btnCapNhat=findViewById(R.id.btnCapNhat);
        btnCapNhat.setOnClickListener(this);

        getToken();
        get_customer_infor();
    }

    private void get_customer_infor() {
        String constr="get_customer_infor";
        String psMsisdn="0987023195";
        RetroClient.get_customer_infor(constr, psMsisdn, token, new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response !=null && response.code() == HttpURLConnection.HTTP_OK) {
                        String body = response.body().string();
                        JSONArray jsonArray=new JSONArray(body);
                        if (jsonArray.length()>0)
                        {
                            JSONObject object=jsonArray.getJSONObject(0);
                            if (object!=null)
                            {
                                sdt=object.optString("MSISDN");
                                String ten=object.optString("NAME");

                                txtDTCN.setText(sdt);
                                txtTenCN.setText(ten);
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


    private void getToken()
    {
        Intent intent=getIntent();
        if (intent!=null)
        {
            token=intent.getStringExtra("token");
        }
        thoiGian=intent.getStringExtra("time");

    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnCapNhat:
                Intent iCapNhatThongTin=new Intent(CapNhatActivity.this,CapNhatThongTinActivity.class);
                iCapNhatThongTin.putExtra("token",token);
                iCapNhatThongTin.putExtra("dienthoai",sdt);
                iCapNhatThongTin.putExtra("time",thoiGian);
                startActivity(iCapNhatThongTin);
                break;
        }
    }
}
