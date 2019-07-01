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

public class XacNhanNapTienActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnHuy, btnDongY;
    TextView txtTenGoiCuocXN,txtDichVuGame,txtSoTienXN,txtTaiKhoanXN;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog_xacnhannaptien);



        btnHuy = findViewById(R.id.btnHuyBoNapTien);
        btnDongY = findViewById(R.id.btnNapTienThanhCong);
        txtTenGoiCuocXN=findViewById(R.id.txtTenGoiCuocXN);
        txtDichVuGame=findViewById(R.id.txtDichVuGame);
        txtSoTienXN=findViewById(R.id.txtSoTienXN);
        txtTaiKhoanXN=findViewById(R.id.txtTaiKhoanXN);



        btnHuy.setOnClickListener(this);
        btnDongY.setOnClickListener(this);
        //todo:nhận dữ liệu
        Intent intent=getIntent();
        txtTenGoiCuocXN.setText(intent.getStringExtra("1"));
        txtDichVuGame.setText(intent.getStringExtra("2"));
        txtSoTienXN.setText(intent.getStringExtra("4"));
        txtTaiKhoanXN.setText(intent.getStringExtra("5"));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnHuyBoNapTien:
               Intent iNapTien=new Intent(XacNhanNapTienActivity.this,NapTienGameActivity.class);
               startActivity(iNapTien);
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

