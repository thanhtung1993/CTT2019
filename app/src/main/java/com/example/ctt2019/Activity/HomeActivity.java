package com.example.ctt2019.Activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ctt2019.API.RetroClient;
import com.example.ctt2019.R;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeActivity extends AppCompatActivity {
    Toolbar toolbar;
    Drawable drawable;
    TextView mTitle, txtGioDNHome, txtDTHome, txtTenHome, txtTenGoiCuocHome, txtGiaTienHome, txtChuKiCuoc, txtDichVuHome, txtNCCHome, txtDangKyLanDauHome, txtNgayGiaHanHome, txtTongSoTienDaNap;
    String thoiGianHienTai;
    private String token=null;
    BottomNavigationViewEx navigationViewEx;
    String ten;
    Menu menu;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        Fragment fragment;

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            switch (menuItem.getItemId()) {
                case R.id.itTrangChu:
                    startActivity(getIntent());
                    return true;
                case R.id.itLichSu:
                    fragment = new FragmentLichSu();
                    loadFragment(fragment);
                    return true;
                case R.id.itNapTien:
                    Intent iNapTien = new Intent(HomeActivity.this, NapTienGameActivity.class);
                    iNapTien.putExtra("time",thoiGianHienTai);
                    iNapTien.putExtra("token",token);
                    startActivity(iNapTien);
                    return true;
                case R.id.itGoiCuoc:
                    fragment = new FragmentGoiCuocSuDung();
                    loadFragment(fragment);
                    return true;
                case R.id.itDVhot:
                    fragment = new FrangmentDVhot();
                    loadFragment(fragment);
                    return true;

            }

            return false;
        }
    };

     //todo:load fragment
    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.layouthome, fragment);
        transaction.addToBackStack(null).commit();

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_home);


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTitle = toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Hệ thống cổng thanh toán");

        navigationViewEx=findViewById(R.id.navigation);
        txtGioDNHome=findViewById(R.id.txtGioDNHome);
        txtDTHome = findViewById(R.id.txtDTHome);
        txtTenHome = findViewById(R.id.txtTenHome);
        txtTenGoiCuocHome =findViewById(R.id.txtTenGoiCuocHome);
        txtGiaTienHome =findViewById(R.id.txtGiaTienHome);
        txtChuKiCuoc = findViewById(R.id.txtChuKiCuoc);
        txtDichVuHome =findViewById(R.id.txtDichVuHome);
        txtNCCHome =findViewById(R.id.txtNCCHome);
        txtDangKyLanDauHome =findViewById(R.id.txtDangKyLanDauHome);
        txtNgayGiaHanHome =findViewById(R.id.txtNgayGiaHanHome);
        txtTongSoTienDaNap =findViewById(R.id.txtTongSoTienDaNap);

        hienThiNutMenu();

        //todo:nut nhỏ
        FloatingActionButton fab=findViewById(R.id.fabTaiKhoan);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iHoTro=new Intent(HomeActivity.this, HoTroActivity.class);

                iHoTro.putExtra("token",token);
                iHoTro.putExtra("time",thoiGianHienTai);
                startActivity(iHoTro);
            }
        });




        BottomNavigationView navigationView = findViewById(R.id.navigation);

        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //tạo click cho toolbar
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent icapnhat = new Intent(HomeActivity.this, CapNhatActivity.class);
               icapnhat.putExtra("time",thoiGianHienTai);
                icapnhat.putExtra("token",token);
               startActivity(icapnhat);

            }
       });
        getToken();
        getHome();
        thongTinNguoiDung();

        setupBottomNavigationView(navigationViewEx);
        txtGioDNHome.setText(thoiGianHienTai);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_exit,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {

            case R.id.itexit:
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void thongTinNguoiDung() {
        String constr="get_customer_infor";
        String psMsisdn="0987023195";

        RetroClient.get_customer_infor(constr, psMsisdn, token, new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response!=null && response.code()==HttpURLConnection.HTTP_OK)
                {
                    try {
                        String body=response.body().string();
                        JSONArray jsonArray=new JSONArray(body);
                        if (jsonArray.length()>0)
                        {
                            JSONObject object=jsonArray.getJSONObject(0);
                            if (object!=null)
                            {
                                String sdt=object.optString("MSISDN");
                                ten=object.optString("NAME");

                                txtTenHome.setText(ten);
                                txtDTHome.setText(sdt);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

    //todo: bỏ hiệu ứng
    public static void setupBottomNavigationView(BottomNavigationViewEx bnve){

        bnve.enableAnimation(false);
        bnve.enableShiftingMode(false);
        bnve.enableItemShiftingMode(false);

    }


    private void getHome() {

        String constr="get_home";
        String psMsisdn="0987023195";
        RetroClient.getHome(constr,psMsisdn,token, new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response != null && response.code() == HttpURLConnection.HTTP_OK) {
                    try {

                        // Toast.makeText(HomeActivity.this,"ok"+body,Toast.LENGTH_SHORT).show();
                        String body=response.body().string();
                        JSONArray jsonArray=new JSONArray(body);
                        if (jsonArray.length()>0)
                        {
                            JSONObject object=jsonArray.getJSONObject(0);
                            if (object!=null)
                            {
                               // String sodienthoai=object.optString("MSISDN");
                              //  String tennguoidung=object.optString("NAME");
                                String tengoicuoc=object.optString("VASGATEFTPACCOUNT");
                                String giagoicuoc=object.optString("PRICE");
                                String chuki=object.optString("FREE_CIRCLE");
                                String dichvu=object.optString("NAME");
                                 String ncc=object.optString("PARTNER_NAME");
                                String dklandau=object.optString("TIME_START");
                                String giahan=object.optString("LASTDATE_REMIND");
                                //   String tongtiendanap=object.optString("")


                             //  txtDTHome.setText(sodienthoai);
                               // txtTenHome.setText(tennguoidung);
                                txtTenGoiCuocHome.setText(tengoicuoc);
                                txtGiaTienHome.setText(giagoicuoc + " VNĐ");
                                txtChuKiCuoc.setText(chuki + " ngày");
                                txtDichVuHome.setText(dichvu);
                                txtNCCHome.setText(ncc);
                                txtDangKyLanDauHome.setText(dklandau);
                                txtNgayGiaHanHome.setText(giahan);
                                txtTongSoTienDaNap.setText("10.000.000 VNĐ");
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(HomeActivity.this,"Vui lòng kết nối Internet .",Toast.LENGTH_SHORT).show();

            }
        });

    }

    protected void getToken() {
        Intent intent=getIntent();
        if (intent!=null) {
            token = intent.getStringExtra("token");
        }
        thoiGianHienTai=intent.getStringExtra("time");

    }



    public String sendToken()
    {
      return token;
    }
    public String sendTime()
    {
        return thoiGianHienTai;
    }

    private void hienThiNutMenu() {
        drawable = getResources().getDrawable(R.drawable.menuu);
        getSupportActionBar().setHomeButtonEnabled(false);//gán nút menu
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
    }





}
