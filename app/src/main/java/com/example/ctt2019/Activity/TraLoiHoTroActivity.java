package com.example.ctt2019.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ctt2019.R;

public class TraLoiHoTroActivity extends AppCompatActivity {

    ImageView imgTraLoiHoTro;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_traloihotro);



        imgTraLoiHoTro=findViewById(R.id.imgSend);
        imgTraLoiHoTro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TraLoiHoTroActivity.this,"Đã gửi",Toast.LENGTH_LONG).show();
            }
        });
    }
}
