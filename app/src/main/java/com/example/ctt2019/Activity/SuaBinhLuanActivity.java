package com.example.ctt2019.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ctt2019.API.RetroClient;
import com.example.ctt2019.R;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SuaBinhLuanActivity extends AppCompatActivity implements View.OnClickListener {
    String psMsisdn,psContent,token,thoiGian,psId;
    Button btnTroLaiBinhLuan,btnCapNhatBinhLuan;
    EditText edtSuaBinhLuan;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_diaglog_edit);

        getToken();

        btnCapNhatBinhLuan=findViewById(R.id.btnCapNhatBinhLuan);
        btnTroLaiBinhLuan=findViewById(R.id.btnTroLaiBinhLuan);
        edtSuaBinhLuan=findViewById(R.id.edtSuaBinhLuan);
        btnCapNhatBinhLuan.setOnClickListener(this);
        btnTroLaiBinhLuan.setOnClickListener(this);

        edtSuaBinhLuan.setText(psContent);

    }

    private void getToken() {
        Intent intent=getIntent();
        if (intent!=null)
        {
             psContent=intent.getStringExtra("psContent");
             psId= intent.getStringExtra("psId");
             psMsisdn=intent.getStringExtra("psMsisdn");
             token=intent.getStringExtra("token");
             thoiGian=intent.getStringExtra("time");


        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnCapNhatBinhLuan:
                    JsonObject object=new JsonObject();
                    object.addProperty("constr","update_comment_user");
                    object.addProperty("psMsisdn",psMsisdn);
                    object.addProperty("psId",psId);
                    object.addProperty("psContent",edtSuaBinhLuan.getText().toString());


                    RetroClient.update(object, token, new Callback<JsonObject>() {
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                        }
                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {

                        }
                    });
              Intent intent=new Intent(SuaBinhLuanActivity.this,TraLoiHoTroActivity.class);
              intent.putExtra("token",token);
              intent.putExtra("time",thoiGian);
              startActivity(intent);
                break;
            case R.id.btnTroLaiBinhLuan:
                finish();
                break;
        }

    }
}
