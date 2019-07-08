package com.example.ctt2019.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.ctt2019.API.RetroClient;
import com.example.ctt2019.R;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class XacNhanXoaActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnXacNhanXoa,btnTroLai;
    String token,ID;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_diaglog_xacnhanxoa);
        btnXacNhanXoa=findViewById(R.id.btnDongYXoa);
        btnTroLai=findViewById(R.id.btnTroLai);
        btnXacNhanXoa.setOnClickListener(this);
        btnTroLai.setOnClickListener(this);
        getToken();

    }

    private void getToken() {
        Intent intent=getIntent();
        if (intent!=null)
        {
            token=intent.getStringExtra("token");
            ID=intent.getStringExtra("id");
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnDongYXoa:
                JsonObject object=new JsonObject();
                object.addProperty("constr","delete_customer_support");
                object.addProperty("psId",ID);

                RetroClient.update(object, token, new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });
                Intent intent=new Intent(XacNhanXoaActivity.this,HoTroActivity.class);
                intent.putExtra("token",token);
                startActivity(intent);

                break;
            case R.id.btnTroLai:
                Intent itrolai=new Intent(XacNhanXoaActivity.this,HoTroActivity.class);
                itrolai.putExtra("token",token);
                startActivity(itrolai);
                break;
        }
    }
}
