package com.example.ctt2019.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.ctt2019.R;

public class ManHinhChaoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_manhinhchao);


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                }
                catch (Exception e)
                {

                }
                finally {
                    Intent itrangchu=new Intent(ManHinhChaoActivity.this, LoginActivity.class);
                    startActivity(itrangchu);
                }
            }
        });
        thread.start();
    }

    }

