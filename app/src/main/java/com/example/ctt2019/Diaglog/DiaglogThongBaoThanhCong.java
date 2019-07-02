package com.example.ctt2019.Diaglog;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.ctt2019.Activity.HomeActivity;
import com.example.ctt2019.R;

public class DiaglogThongBaoThanhCong  extends AppCompatActivity {
    String token,thoiGian;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog_huythanhcong);

        Button btnHuy=findViewById(R.id.btnDong);
        getToken();
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DiaglogThongBaoThanhCong.this, HomeActivity.class);
                intent.putExtra("token",token);
                intent.putExtra("time",thoiGian);
                startActivity(intent);
            }
        });
    }

    private void getToken() {
        Intent intent=getIntent();
        if (intent !=null)
        {
            token=intent.getStringExtra("token");
            thoiGian=intent.getStringExtra("time");
        }
    }
}
