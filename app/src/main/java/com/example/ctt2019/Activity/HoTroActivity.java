package com.example.ctt2019.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ctt2019.API.RetroClient;
import com.example.ctt2019.Adapter.AdapterHoTro.AdapterHoTro;
import com.example.ctt2019.Model.HoTro.ModelHoTro;
import com.example.ctt2019.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HoTroActivity extends AppCompatActivity implements View.OnClickListener{
TextView mTitle;
    RecyclerView recyclerView;
    List<ModelHoTro> modelHoTroArrayList = new ArrayList<>();
    String token,thoigian,ID;
    Button btnPreviousHoTro,btnNextHoTro;
    int currentPage;
    LinearLayout linearLayout;
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_hotro);

        recyclerView=findViewById(R.id.rccauhoi);
        btnNextHoTro=findViewById(R.id.btnNextHoTro);
        btnPreviousHoTro=findViewById(R.id.btnPreviousHoTro);
        linearLayout=findViewById(R.id.lntieptheo);

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTitle=toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Hỗ Trợ");
        toolbar.setTitle("");
        getToken();
        getData();

        HienThiDanhSachHoTro();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iHome=new Intent(HoTroActivity.this,HomeActivity.class);
                iHome.putExtra("token",token);
                iHome.putExtra("time",thoigian);
                startActivity(iHome);
                HoTroActivity.this.overridePendingTransition(R.anim.anim_enter,R.anim.anim_exit);
            }
        });

        btnPreviousHoTro.setOnClickListener(this);
        btnNextHoTro.setOnClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.itThem:
                Intent intent=new Intent(HoTroActivity.this,ThemHoTroActivity.class);
                intent.putExtra("token",token);
                intent.putExtra("time",thoigian);
                startActivity(intent);
                break;
        }

        return false;
    }

    private void getToken()
    {
        Intent intent=getIntent();
        token= intent.getStringExtra("token");
        ID=intent.getStringExtra("id");
        thoigian=intent.getStringExtra("time");
    }

    private void getData() {
        String constr = "get_customer_support_app";
        String psMsisdn = "0987023195";
        int psPageno=currentPage;
        int psPagerec=3;

        RetroClient.get_customer_support(constr, psMsisdn,psPageno,psPagerec, token, new Callback<List<ModelHoTro>>() {
            @Override
            public void onResponse(Call<List<ModelHoTro>> call, Response<List<ModelHoTro>> response) {
                modelHoTroArrayList=response.body();
                //  Log.i("data",hoTroList.toString());
                HienThiDanhSachHoTro();

                if (modelHoTroArrayList.size()>0) {
                    linearLayout.setVisibility(View.VISIBLE);
                    btnNextHoTro.setVisibility(View.VISIBLE);
                }
                else
                {
                    btnNextHoTro.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<ModelHoTro>> call, Throwable t) {

            }
        });
}

    private void HienThiDanhSachHoTro() {
        RecyclerView recyclerView=findViewById(R.id.rccauhoi);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        AdapterHoTro adapterHoTro=new AdapterHoTro(this,modelHoTroArrayList);
        recyclerView.setAdapter(adapterHoTro);
        adapterHoTro.setData(token);
        adapterHoTro.setTime(thoigian);
        adapterHoTro.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnNextHoTro:
                currentPage=currentPage+1;
                btnPreviousHoTro.setVisibility(View.VISIBLE);
                getData();
                break;
            case R.id.btnPreviousHoTro:
                currentPage=currentPage-1;

                if (currentPage>0) {
                    getData();
                }
                else
                {
                    btnPreviousHoTro.setVisibility(View.GONE);
                }
                break;
        }

    }
}
