package com.example.ctt2019.Activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ctt2019.R;

public class XacNhanDangKiGoiCuocActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnHuy1, btnDongY1;
    TextView txtTenGoiCuocXN1,txtDichVuGame1,txtSoTienXN1,txtTaiKhoanXN1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_diaglog_dkthanhcong);


        btnHuy1 = findViewById(R.id.btnHuyBoNapTien);
        btnDongY1 = findViewById(R.id.btnNapTienThanhCong);
        txtTenGoiCuocXN1=findViewById(R.id.txtTenGoiCuocXN);
        txtDichVuGame1=findViewById(R.id.txtDichVuGame);
        txtSoTienXN1=findViewById(R.id.txtSoTienXN);
        txtTaiKhoanXN1=findViewById(R.id.txtTaiKhoanXN);



        btnHuy1.setOnClickListener(this);
        btnDongY1.setOnClickListener(this);




        //todo:lẫy dữ liệu
        Intent intent=getIntent();
        txtTenGoiCuocXN1.setText(intent.getStringExtra("1"));
        txtDichVuGame1.setText(intent.getStringExtra("2"));
        txtSoTienXN1.setText(intent.getStringExtra("4"));
        txtTaiKhoanXN1.setText(intent.getStringExtra("5"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnHuyBoNapTien:
                finish();
                break;
            case R.id.btnNapTienThanhCong:
                ThongBaoThanhCong();

                break;
        }
    }

    private void ThongBaoThanhCong() {
        Button btnDong;
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        @SuppressLint("ResourceType")
        View dialogView = inflater.inflate(R.layout.layout_diaglog_dkthanhcong, (ViewGroup) findViewById(R.layout.layout_naptiengame));
        btnDong = dialogView.findViewById(R.id.btndong);
        btnDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iNapTienGame = new Intent(getApplicationContext(), NapTienGameActivity.class);
                startActivity(iNapTienGame);
            }
        });
        dialogBuilder.setView(dialogView);
        AlertDialog b = dialogBuilder.create();
        b.show();

    }
    }

