package com.example.ctt2019.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ctt2019.API.RetroClient;
import com.example.ctt2019.R;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CapNhatThongTinActivity  extends AppCompatActivity implements View.OnClickListener{
    Toolbar toolbar;
    TextView mTitle,txtSDT;
    Button btnCapNhatThanhCong;
    EditText edtTenNguoiDung;
    String token=null;
    String dienthoai;
    String thoiGian;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_capnhatthongtin);

        toolbar=findViewById(R.id.toolbar);
        txtSDT=findViewById(R.id.txtSDT);
        mTitle=findViewById(R.id.toolbar_title);
        btnCapNhatThanhCong=findViewById(R.id.btnCapNhatThanhCong);
        edtTenNguoiDung=findViewById(R.id.edtTenNguoiDung);
        btnCapNhatThanhCong.setOnClickListener(this);

        getToken();
        mTitle.setText("Cập nhật thông tin cá nhân");
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iHome=new Intent(CapNhatThongTinActivity.this,HomeActivity.class);
                iHome.putExtra("time",thoiGian);
                iHome.putExtra("token",token);
                startActivity(iHome);
                overridePendingTransition(R.anim.anim_enter,R.anim.anim_exit);
            }
        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

      //
        txtSDT.setText(dienthoai);
    }


    private void getToken()
    {
        Intent intent=getIntent();
        if (intent!=null)
        {
            token=intent.getStringExtra("token");
            dienthoai=intent.getStringExtra("dienthoai");
            thoiGian=intent.getStringExtra("time");
        }


    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnCapNhatThanhCong:
                capNhatThanhCong();
                Button btnDong;
                final AlertDialog.Builder dialogBuilder=new AlertDialog.Builder(this);
                LayoutInflater inflater=this.getLayoutInflater();
                @SuppressLint("ResourceType") final View dialogView=inflater.inflate(R.layout.layout_dialog_capnhatthongtinthanhcong, (ViewGroup) findViewById(R.layout.layout_capnhatthongtin));
                btnDong=dialogView.findViewById(R.id.btndong);
                btnDong.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //chuyển từ màn hình dialog xang activity
                           Intent ihome=new Intent(getApplicationContext(),HomeActivity.class);
                           ihome.putExtra("token",token);
                           startActivity(ihome);

                    }
                });
                dialogBuilder.setView(dialogView);
                AlertDialog b = dialogBuilder.create();
                b.show();

                break;
        }

    }

    private void capNhatThanhCong() {
            JsonObject object = new JsonObject();
            object.addProperty("constr", "update_customer_infor");
            object.addProperty("psMsisdn", "0987023195");
            object.addProperty("psName", edtTenNguoiDung.getText().toString());

            RetroClient.update(object, token, new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {

                }
            });


    }

    }

