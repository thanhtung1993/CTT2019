package com.example.ctt2019.Diaglog;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ctt2019.API.RetroClient;
import com.example.ctt2019.R;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiaglogDelete  extends AppCompatActivity implements View.OnClickListener {
    TextView txtTenGoiCuocXN, txtDichVuXN, txtNCCHuy, txtChuKiCuocXN, txtGiaTienXN, txtTaiKhoanHuyXN;
    Button btnHuyYC, btnDongY;
    String token,thoiGian,PARTNER_CODE,CODE,SUB_CODE,MSISDN,PREFIX;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog_xacnhanhuygoicuoc);

        txtTenGoiCuocXN = findViewById(R.id.txtTenGoiCuocXN);
        txtDichVuXN = findViewById(R.id.txtDichVuXN);
        txtNCCHuy = findViewById(R.id.txtNCCHuy);
        txtChuKiCuocXN = findViewById(R.id.txtChuKiCuocXN);
        txtGiaTienXN = findViewById(R.id.txtGiaTienXN);
        txtTaiKhoanHuyXN = findViewById(R.id.txtTaiKhoanHuyXN);
        btnDongY = findViewById(R.id.btnDongY);
        btnHuyYC = findViewById(R.id.btnHuy);
        btnHuyYC.setOnClickListener(this);
        btnDongY.setOnClickListener(this);


        Intent iThongBao = getIntent();
        SUB_CODE=iThongBao.getStringExtra("1");
        txtTenGoiCuocXN.setText(SUB_CODE);
        txtDichVuXN.setText(iThongBao.getStringExtra("2"));
        txtNCCHuy.setText(iThongBao.getStringExtra("3"));
        txtChuKiCuocXN.setText(iThongBao.getStringExtra("4"));
        txtGiaTienXN.setText(iThongBao.getStringExtra("5"));
        txtTaiKhoanHuyXN.setText(iThongBao.getStringExtra("6"));
        token=  iThongBao.getStringExtra("token");
        thoiGian=iThongBao.getStringExtra("time");
        PARTNER_CODE=  iThongBao.getStringExtra("PARTNER_CODE");
        CODE= iThongBao.getStringExtra("CODE");
        MSISDN=iThongBao.getStringExtra("MSISDN");
        PREFIX=iThongBao.getStringExtra("PREFIX");


    }


    private void huyThanhCong() {
        JsonObject object = new JsonObject();
        object.addProperty("command", "UNREGISTER");
        object.addProperty("tocken", "tocken");
        object.addProperty("checksum", "checksum");
        object.addProperty("msisdn", MSISDN);
        object.addProperty("cpcode", PARTNER_CODE);
        object.addProperty("servicecode", CODE);
        object.addProperty("subcode", SUB_CODE);
        object.addProperty("shortcode", PREFIX);

        RetroClient.register_unregister(object, new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
               if (response.isSuccessful())
               {

               }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnHuy:
                finish();
                break;
            case R.id.btnDongY:
               // huyThanhCong();
                Intent iHuy=new Intent(DiaglogDelete.this,DiaglogThongBaoThanhCong.class);
                iHuy.putExtra("token",token);
                iHuy.putExtra("time",thoiGian);
                startActivity(iHuy);


                break;
        }
    }
}
