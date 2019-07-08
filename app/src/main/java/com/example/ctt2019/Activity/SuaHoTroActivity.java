package com.example.ctt2019.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ctt2019.API.RetroClient;
import com.example.ctt2019.Model.DichVu.ModelDV;
import com.example.ctt2019.Model.GoiCuoc.ModelGoiCuoc;
import com.example.ctt2019.Model.NCC.ModelNCC;
import com.example.ctt2019.R;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SuaHoTroActivity extends AppCompatActivity implements View.OnClickListener{
    String ID,token,psPartner_id,gamelist_id,sub_id,idNCC;
    Button btnSuaHoTro;
    EditText edtSuaHoTro;
    Spinner spNCCSuaHoTro,spDVSuaHoTro,spGoiCuocSuaHoTro;

    private JSONArray jsonNCC,jsonDV,jsonGC;
    private ArrayList<ModelNCC> modelNCCArrayList;
    private ArrayList<ModelDV> modelDVArrayList;
    private ArrayList<ModelGoiCuoc> modelGoiCuocArrayList;

    private ArrayList<String> ncc=new ArrayList<String>();
    private ArrayList<String> dichVu=new ArrayList<String>();
    private ArrayList<String> goiCuoc=new ArrayList<String>();
    

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_edit_hotro);

        btnSuaHoTro=findViewById(R.id.btnSuaHoTro);
        edtSuaHoTro=findViewById(R.id.edtSuaHoTro);
        spNCCSuaHoTro=findViewById(R.id.spNCCSuaHoTro);
        spDVSuaHoTro=findViewById(R.id.spDVSuaHoTro);
        spGoiCuocSuaHoTro=findViewById(R.id.spGoiCuocSuaHoTro);

        btnSuaHoTro.setOnClickListener(this);

        getData();
        getJsonNCC();
        layToanBoHoTroTheoId();
        //spinner ncc
        spNCCSuaHoTro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getDataToSpinnerDV(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //spinner dv
        spDVSuaHoTro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getDataToSpinnerGC(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void layToanBoHoTroTheoId() {
        String constr="get_customer_support_by_id";
        String id=ID;

    RetroClient.suaHoTro(constr, id, token, new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            try {
                String body=response.body().string();
                JSONArray jsonArray=new JSONArray(body);
                if (jsonArray.length()>0)
                {
                    JSONObject object=jsonArray.getJSONObject(0);
                    if (object!=null)
                    {
                        edtSuaHoTro.setText(object.optString("DESCRIBE"));
                    }
                }
               // Toast.makeText(SuaHoTroActivity.this,"ok"+body,Toast.LENGTH_LONG).show();

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

    private void getDataToSpinnerGC(int position) {
        String constr="get_gamelist_circle";
        String psGameList_id=getIDDV(position);

        RetroClient.get_gamelist_circle(constr, psGameList_id, token, new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful())
                {
                    if (response.body()!=null)
                    {
                        String body=response.body().toString();
                        modelGoiCuocArrayList=new ArrayList<>();
                        try {
                            jsonGC=new JSONArray(body);
                            for (int i=0;i<jsonGC.length();i++)
                            {
                                ModelGoiCuoc modelGoiCuoc=new ModelGoiCuoc();
                                JSONObject object=jsonGC.getJSONObject(i);
                                modelGoiCuoc.setID(object.optString("ID"));
                                modelGoiCuoc.setSUB_CODE(object.optString("SUB_CODE"));
                                modelGoiCuocArrayList.add(modelGoiCuoc);
                            }
                            goiCuoc.clear();
                            for (int i=0;i<modelGoiCuocArrayList.size();i++)
                            {
                                goiCuoc.add(modelGoiCuocArrayList.get(i).getSUB_CODE().toString());
                            }

                            ArrayAdapter<String> adapter=new ArrayAdapter<String>(SuaHoTroActivity.this,android.R.layout.simple_spinner_item,goiCuoc);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spGoiCuocSuaHoTro.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }

    private String getIDDV(int position) {
        try {
            JSONObject object=jsonDV.getJSONObject(position);
            sub_id=object.getString("ID");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sub_id;
    }


    private void getDataToSpinnerDV(final int position) {
        String constr="get_gamelist_by_partner";
        psPartner_id=getIdNCC(position);
        Log.i("idname",psPartner_id);
        RetroClient.get_gamelist_by_partner(constr, psPartner_id, token, new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful())
                {
                    if (response.body()!=null)
                    {
                        String body=response.body().toString();
                        modelDVArrayList=new ArrayList<>();
                        try {
                            jsonDV=new JSONArray(body);
                            for (int i=0;i<jsonDV.length();i++)
                            {
                                ModelDV modelDV=new ModelDV();
                                JSONObject object=jsonDV.getJSONObject(i);
                                modelDV.setNAME(object.optString("NAME"));
                                modelDV.setCODE(object.optString("CODE"));
                                modelDV.setID(object.optString("ID"));
                                modelDVArrayList.add(modelDV);


                            }
                            dichVu.clear();
                            for (int i=0;i<modelDVArrayList.size();i++)
                            {
                                dichVu.add(modelDVArrayList.get(i).getNAME().toString());

                            }
                            ArrayAdapter<String> adapter=new ArrayAdapter<String>(SuaHoTroActivity.this,android.R.layout.simple_spinner_item,dichVu);
                           adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                           spDVSuaHoTro.setAdapter(adapter);



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });


    }

    private String getIdNCC(int position) {
        try {
            JSONObject object=jsonNCC.getJSONObject(position);
            idNCC=object.getString("PARTNER_ID");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return idNCC;
    }


    private void getJsonNCC() {
        String constr="get_all_partners";
        RetroClient.get_all_partners(constr, token, new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("NCC",response.body().toString());
                if (response.isSuccessful())
                {
                    if (response.body()!=null)
                    {
                        String body=response.body().toString();
                        getDataToSpinnerNCC(body);
                    }
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    private void getDataToSpinnerNCC(String body) {

        try {
            modelNCCArrayList=new ArrayList<>();
            jsonNCC=new JSONArray(body);
            for (int i=0;i<jsonNCC.length();i++)
            {
                ModelNCC modelNCC=new ModelNCC();
                JSONObject object=jsonNCC.getJSONObject(i);
                modelNCC.setPARTNER_ID(object.optString("PARTNER_ID"));
                modelNCC.setPARTNER_NAME(object.optString("PARTNER_NAME"));
                modelNCCArrayList.add(modelNCC);
            }
            ncc.clear();
            for (int i=0;i<modelNCCArrayList.size();i++)
            {
                ncc.add(modelNCCArrayList.get(i).getPARTNER_NAME().toString());
            }

            ArrayAdapter<String> adapter=new ArrayAdapter<String>(SuaHoTroActivity.this,android.R.layout.simple_spinner_item,ncc);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spNCCSuaHoTro.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    private void getData() {
        Intent intent=getIntent();
        if (intent!=null) {
            ID = intent.getStringExtra("psId");
            token=intent.getStringExtra("token");
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
                object.addProperty("psPartner_id",1026);
                object.addProperty("psGamelist_id",7872);
                object.addProperty("psSub_id",3370);

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
                intent.putExtra("token",token);
                startActivity(intent);

                break;

        }

    }
}
