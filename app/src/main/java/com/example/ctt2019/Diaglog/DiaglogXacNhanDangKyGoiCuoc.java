package com.example.ctt2019.Diaglog;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ctt2019.API.RetroClient;
import com.example.ctt2019.R;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiaglogXacNhanDangKyGoiCuoc  extends AppCompatActivity implements View.OnClickListener {
    TextView txtTenGoiCuocXNDVH,txtDichVuXNDVH,txtNCCDVH,txtChuKiCuocXNDVH,txtSoTienXNDVH;
    EditText edtTaiKhoanXNDVH;
    Button btnHuyYeuCau,btnDongYDKDVhot;
    String token,thoiGian;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_diaglog_dkdichvuhot);

        txtTenGoiCuocXNDVH=findViewById(R.id.txtTenGoiCuocXNDVH);
        txtDichVuXNDVH=findViewById(R.id.txtDichVuXNDVH);
        txtNCCDVH=findViewById(R.id.txtNCCDVH);
        txtChuKiCuocXNDVH=findViewById(R.id.txtChuKiCuocXNDVH);
        txtSoTienXNDVH=findViewById(R.id.txtSoTienXNDVH);
        edtTaiKhoanXNDVH=findViewById(R.id.edtTaiKhoanXNDVH);
        btnHuyYeuCau=findViewById(R.id.btnHuyYeuCau);
        btnDongYDKDVhot=findViewById(R.id.btnDongYDKDVhot);
        btnHuyYeuCau.setOnClickListener(this);
        btnDongYDKDVhot.setOnClickListener(this);


        Intent intent=getIntent();
        txtTenGoiCuocXNDVH.setText(intent.getStringExtra("2"));
        txtDichVuXNDVH.setText(intent.getStringExtra("1"));
        txtNCCDVH.setText(intent.getStringExtra("5"));
        txtChuKiCuocXNDVH.setText(intent.getStringExtra("4"));
        txtSoTienXNDVH.setText(intent.getStringExtra("3"));
        token= intent.getStringExtra("token");
        thoiGian= intent.getStringExtra("time");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnHuyYeuCau:
                finish();
                break;
            case R.id.btnDongYDKDVhot:
              //  dangKyThanhCong();
                Intent iThanhCong=new Intent(DiaglogXacNhanDangKyGoiCuoc.this, DiaglogXacNhanThanhCong.class);
                iThanhCong.putExtra("token",token);
                iThanhCong.putExtra("time",thoiGian);
                startActivity(iThanhCong);

                break;
        }

    }

    private void dangKyThanhCong() {
        JsonObject object = new JsonObject();
        object.addProperty("command", "REGISTER");
        object.addProperty("tocken", "tocken");
        object.addProperty("checksum", "checksum");
        object.addProperty("msisdn", "0934435389");
        object.addProperty("cpcode", "GR");
        object.addProperty("servicecode", "LQ");
        object.addProperty("subcode", "LQ1");
        object.addProperty("shortcode", "9029_GR");

        RetroClient.register_unregister(object, new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }
}
