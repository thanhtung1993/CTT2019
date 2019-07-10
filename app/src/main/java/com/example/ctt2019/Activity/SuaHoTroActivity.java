package com.example.ctt2019.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ctt2019.API.RetroClient;
import com.example.ctt2019.R;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SuaHoTroActivity extends AppCompatActivity implements View.OnClickListener{
    String ID,token,psPartner_id,psGamelist_id,psSub_id;
    Button btnSuaHoTro,btnTroLaiHoTro;
    EditText edtSuaHoTro;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_edit_hotro);

        btnSuaHoTro=findViewById(R.id.btnSuaHoTro);
        btnTroLaiHoTro=findViewById(R.id.btnTroLaiHoTro);
        edtSuaHoTro=findViewById(R.id.edtSuaHoTro);

        btnTroLaiHoTro.setOnClickListener(this);
        btnSuaHoTro.setOnClickListener(this);

        getData();
        layToanBoHoTroTheoId();

    }

    private void layToanBoHoTroTheoId() {
        String constr = "get_customer_support_by_id";
        String id = ID;

        RetroClient.suaHoTro(constr, id, token, new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String body = response.body().string();
                    JSONArray jsonArray = new JSONArray(body);
                    if (jsonArray.length() > 0) {
                        JSONObject object = jsonArray.getJSONObject(0);
                        if (object != null) {
                            edtSuaHoTro.setText(object.optString("DESCRIBE"));
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void getData() {
        Intent intent=getIntent();
        if (intent!=null) {
            ID = intent.getStringExtra("psId");
            token=intent.getStringExtra("token");
            psPartner_id=intent.getStringExtra("psPartner_id");
            psGamelist_id=intent.getStringExtra("psGamelist_id");
            psSub_id=intent.getStringExtra("psSub_id");
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btnSuaHoTro:
                JsonObject object=new JsonObject();
                object.addProperty("constr","update_customer_support");
                object.addProperty("psMsisdn","0987023195");
                object.addProperty("psId",ID);
                object.addProperty("psContent",edtSuaHoTro.getText().toString());
                object.addProperty("psPartner_id",psPartner_id);
                object.addProperty("psGamelist_id",psGamelist_id);
                object.addProperty("psSub_id",psSub_id);

                RetroClient.update(object, token, new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                    }
                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });
                Toast.makeText(SuaHoTroActivity.this,"Cập nhật thành công",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(SuaHoTroActivity.this,HoTroActivity.class);
                intent.putExtra("id",ID);
                intent.putExtra("token",token);
                startActivity(intent);
                this.finish();

                break;
            case R.id.btnTroLaiHoTro:
                finish();
                break;

        }

    }
}
