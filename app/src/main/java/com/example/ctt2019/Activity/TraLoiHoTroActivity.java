package com.example.ctt2019.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ctt2019.API.RetroClient;
import com.example.ctt2019.Adapter.AdapterHoTro.AdapterTraLoiBinhLuan;
import com.example.ctt2019.Model.HoTro.ModelBinhLuan;
import com.example.ctt2019.R;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TraLoiHoTroActivity extends AppCompatActivity {
     String token,thoiGian,ten;
     RecyclerView rcTraLoiHoTro;
     List<ModelBinhLuan> modelBinhLuanList=new ArrayList<>();
     EditText edtThemBinhLuan;
     Button btnSend;
     Toolbar toolbar;
      TextView toolbar_titlebinhluan;

    String psMsisdn,psContent,psGamelist_id,psSub_id,psParentid,psPartner_id;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_traloihotro);

        getToken();
        getData();
        edtThemBinhLuan=findViewById(R.id.edtThemHoTro);

        toolbar=findViewById(R.id.toolbarthembinhluan);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(TraLoiHoTroActivity.this,HoTroActivity.class);
                intent.putExtra("time",thoiGian);
                intent.putExtra("token",token);
                startActivity(intent);
            }
        });


        btnSend=findViewById(R.id.btnSend);
        edtThemBinhLuan.setText("");
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TraLoiHoTroActivity.this,"Đã gửi thành công",Toast.LENGTH_LONG).show();
                traLoiHoTro();
                getData();
                edtThemBinhLuan.setText("");

            }
        });


    }

    private void traLoiHoTro() {
        JsonObject object=new JsonObject();
        object.addProperty("constr","insert_comment_user");
        object.addProperty("psMsisdn","0987023195");
        object.addProperty("psContent",edtThemBinhLuan.getText().toString());
        object.addProperty("psPartner_id",psPartner_id);
        object.addProperty("psGamelist_id",psGamelist_id);
        object.addProperty("psSub_id",psSub_id);
        object.addProperty("psParent_id",psParentid);


        RetroClient.update(object, token, new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                         if (response.isSuccessful())
                         {

                         }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }

    private void getData() {
        String constr="get_customer_support_by_parent";
        String psMsisdn="0987023195";
        String psParent_id=psParentid;

        RetroClient.themBinhLuanHoTro(constr, psMsisdn, psParent_id, token, new Callback<List<ModelBinhLuan>>() {
            @Override
            public void onResponse(Call<List<ModelBinhLuan>> call, Response<List<ModelBinhLuan>> response) {
                    modelBinhLuanList=response.body();
                    hienThiDanhSachBinhLuan();
            }

            @Override
            public void onFailure(Call<List<ModelBinhLuan>> call, Throwable t) {

            }
        });
    }

    private void hienThiDanhSachBinhLuan() {
        rcTraLoiHoTro=findViewById(R.id.rcTraLoi);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        rcTraLoiHoTro.setLayoutManager(layoutManager);
        AdapterTraLoiBinhLuan adapterTraLoiBinhLuan=new AdapterTraLoiBinhLuan(this,modelBinhLuanList);
        rcTraLoiHoTro.setAdapter(adapterTraLoiBinhLuan);
        adapterTraLoiBinhLuan.setData(token);
        adapterTraLoiBinhLuan.setTime(thoiGian);
        adapterTraLoiBinhLuan.setTen(ten);
        adapterTraLoiBinhLuan.notifyDataSetChanged();

    }

    private void getToken() {
        Intent intent=getIntent();
        token=intent.getStringExtra("token");
        psParentid=intent.getStringExtra("psParent_id");
        psSub_id=intent.getStringExtra("psSub_id");
        psGamelist_id=intent.getStringExtra("psGamelist_id");
        psPartner_id=intent.getStringExtra("psPartner_id");
        thoiGian=intent.getStringExtra("time");
        ten=intent.getStringExtra("ten");

    }
}
