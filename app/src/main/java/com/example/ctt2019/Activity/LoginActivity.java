package com.example.ctt2019.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ctt2019.API.RetroClient;
import com.example.ctt2019.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnLogin, btnGuiOTP;
    EditText edtDangNhap, edtMatKhau;
    String currentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        //   toolbar =findViewById(R.id.toolbar);
        //TextView mTitle=toolbar.findViewById(R.id.toolbar_title);
        //  setSupportActionBar(toolbar);
        // mTitle.setText(toolbar.getTitle());
        // getSupportActionBar().setDisplayShowTitleEnabled(false);


        btnLogin = findViewById(R.id.btnLogin);
        btnGuiOTP = findViewById(R.id.btnGuiOTP);
        edtDangNhap = findViewById(R.id.edtdangnhap);
        edtMatKhau = findViewById(R.id.edtmatkhau);

        //txtOTP = findViewById(R.id.txtOTP);
        btnGuiOTP.setOnClickListener(this);
        btnLogin.setOnClickListener(this);


    }


    /* public void time()
     {
         new CountDownTimer(60000,1000){
             @Override
             public void onTick(long millisUntilFinished) {
                 txtDTHome.setText("Con lai: "+ millisUntilFinished/1000+" s");

             }

             @Override
             public void onFinish() {
                 txtDTHome.setText("oop Time Out!");

             }
         }.start();
     }*/
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
              //  Intent idangnhap=new Intent(LoginActivity.this,HomeActivity.class);
              //  startActivity(idangnhap);
                dangNhap();
                layThoiGianHienTai();
                break;
            case R.id.btnGuiOTP:
               // txtOTP.setVisibility(View.VISIBLE);
                Toast.makeText(LoginActivity.this,"Mã OTP đã được gửi tới điện thoại của quý khách .Vui lòng nhập mã OTP để tiếp tục đăng nhập !",Toast.LENGTH_LONG).show();
                break;
        }
    }

    private void dangNhap() {
        String user = "thangph";
        String pass = "e10adc3949ba59abbe56e057f20f883e";

      RetroClient.login(user, pass, new Callback<ResponseBody>() {
          @Override
          public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
              /*{"userId":"thangph","password":"e10adc3949ba59abbe56e057f20f883e","token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjcmVhdGVkQXQiOjE1NjAyMjU0NjcsInVzZXJJZCI6InRoYW5ncGgifQ.0Vw1UeqJ4X_GxrF_c5WprcmjuB1HSyYBmAhzOGI0EbY"}*/

              try {
                  JSONObject object=new JSONObject(response.body().string());
                  String token=object.optString("token","");
                  gotomain(token);
                 // Toast.makeText(LoginActivity.this,"ok"+object,Toast.LENGTH_SHORT).show();
              } catch (JSONException e) {
                  e.printStackTrace();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }

          @Override
          public void onFailure(Call<ResponseBody> call, Throwable t) {

          }
      });

    }

    private void gotomain(String token) {
        Intent ihome=new Intent(LoginActivity.this,HomeActivity.class);
        ihome.putExtra("time",currentDate);
        ihome.putExtra("token",token);
        startActivity(ihome);
    }

    private  void layThoiGianHienTai()
    {
        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat sdf=new SimpleDateFormat("hh:mm a");
        currentDate = sdf.format(calendar.getTime());
        Toast.makeText(LoginActivity.this,"Giờ :"+currentDate,Toast.LENGTH_LONG).show();

    }

}
