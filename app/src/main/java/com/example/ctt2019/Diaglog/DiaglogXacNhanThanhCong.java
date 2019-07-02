package com.example.ctt2019.Diaglog;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.ctt2019.API.RetroClient;
import com.example.ctt2019.Activity.HomeActivity;
import com.example.ctt2019.R;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiaglogXacNhanThanhCong extends AppCompatActivity {
    Button btndong;
    String token,thoiGian;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_dialog_dkgoicuocthanhcong);
            getToken();
            btndong=findViewById(R.id.btnDongDVH);

            btndong.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // xacNhanDangKyGoiCuocHot();
                    Intent iHome=new Intent(DiaglogXacNhanThanhCong.this, HomeActivity.class);
                    iHome.putExtra("token",token);
                    iHome.putExtra("time",thoiGian);
                    startActivity(iHome);
                }
            });
    }

    private void xacNhanDangKyGoiCuocHot() {
        JsonObject object=new JsonObject();
        object.addProperty("command","REGISTER");
        object.addProperty("tocken","tocken");
        object.addProperty("checksum","checksum");
        object.addProperty("msisdn","0934435389");
        object.addProperty("cpcode","GR");
        object.addProperty("servicecode","LQ");
        object.addProperty("subcode","LQ1");
        object.addProperty("shortcode","9029_GR");

        RetroClient.register_unregister(object, new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }

    protected void getToken() {
        Intent intent=getIntent();
        if (intent!=null) {
            token = intent.getStringExtra("token");
            thoiGian=intent.getStringExtra("time");
        }


    }
}
