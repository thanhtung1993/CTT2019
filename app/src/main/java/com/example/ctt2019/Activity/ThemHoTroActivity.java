package com.example.ctt2019.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ThemHoTroActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnGuiHoTro;
    private String token=null;
    EditText edtThemHoTro;
    ModelNCC modelNCC;
    ModelDV modelDV;
    Spinner spNCCThemHoTro,spDVThemHoTro,spGoiCuocThemHoTro;

    Toolbar toolbar;
    String psPartner_id,gamelist_id,sub_id,idncc,thoigian;

    private JSONArray jsonArray,jsonDichVu,jsonGoiCuoc;
    private ArrayList<ModelNCC> arrayList;
    private ArrayList<ModelDV> arrayDichVu;
    private ArrayList<ModelGoiCuoc> arrayListGoiCuoc;

    private ArrayList<String> nhaCC = new ArrayList<String>();
    private ArrayList<String> dichVu= new ArrayList<String>();
    private ArrayList<String> goiCuoc= new ArrayList<String>();



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_themhotro);
        btnGuiHoTro=findViewById(R.id.btnGuiYeuCauThemHoTro);
        edtThemHoTro=findViewById(R.id.edtThemHoTro);
        spDVThemHoTro=findViewById(R.id.spDVThemHoTro);
        spGoiCuocThemHoTro=findViewById(R.id.spGoiCuocThemHoTro);

        toolbar=findViewById(R.id.toolbarthemthotro);
        TextView mTitle=toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Hỗ Trợ");
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        btnGuiHoTro.setOnClickListener(this);

        getToken();
        getData();


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent icapnhat = new Intent(ThemHoTroActivity.this, HoTroActivity.class);
                icapnhat.putExtra("token",token);
                icapnhat.putExtra("time",thoigian);
                startActivity(icapnhat);

            }
        });
        //todo:spin thêm hỗ trợ
        spNCCThemHoTro=findViewById(R.id.spNCCThemHoTro);
        spNCCThemHoTro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getDataDV(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //todo:spin gói cước
        spDVThemHoTro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              getDataCircle(position);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //todo: lấy id gói cước
        spGoiCuocThemHoTro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getIDGoiCuoc(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//--------------------------------



    }

    private String getIDGoiCuoc(int position) {

        try {
            JSONObject object=jsonGoiCuoc.getJSONObject(position);
            sub_id=object.getString("ID");
            Log.i("IDGOICUOC",sub_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sub_id;
    }
    // lấy id gói cước



    //todo" lấy dữ liệu cho phần gói cước
    private void getDataCircle(int position) {
        String constr="get_gamelist_circle";
        String psGamelist_id=getIDDV(position);
        Log.i("ID",psGamelist_id);

        RetroClient.get_gamelist_circle(constr, psGamelist_id, token, new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
            //    Log.i("Res", response.body().toString());
                if (response.isSuccessful())
                {
                    if (response.body()!=null)
                    {
                        Log.i("sucsit", response.body().toString());
                        String jsonCircle = response.body().toString();
                        spJSONCC(jsonCircle);
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }
    //todo dữ liệu spinner cho nhà cung cấp
    private void spJSONCC(String jsonCircle) {

        try {
            arrayListGoiCuoc = new ArrayList<>();
            jsonGoiCuoc=new JSONArray(jsonCircle);
            for (int i=0;i<jsonGoiCuoc.length();i++)
            {
                ModelGoiCuoc modelGoiCuoc=new ModelGoiCuoc();
                JSONObject object=jsonGoiCuoc.getJSONObject(i);
                modelGoiCuoc.setID(object.optString("ID"));
                modelGoiCuoc.setSUB_CODE(object.optString("SUB_CODE"));
                arrayListGoiCuoc.add(modelGoiCuoc);

            }
            goiCuoc.clear();
            for (int i=0;i<arrayListGoiCuoc.size();i++)
            {
                goiCuoc.add(arrayListGoiCuoc.get(i).getSUB_CODE().toString());
            }
            ArrayAdapter<String> spAdapter=new ArrayAdapter<String>(ThemHoTroActivity.this,android.R.layout.simple_spinner_item,goiCuoc);
            spAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spGoiCuocThemHoTro.setAdapter(spAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    //todo: lấy dữ liệu cho phần dịch vụ game
    private void getDataDV(int position) {
       String constr="get_gamelist_by_partner";
        psPartner_id=getSpNCC(position);
        Log.i("idname",psPartner_id);

        RetroClient.get_gamelist_by_partner(constr, psPartner_id, token, new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("Responaaaaaaaasestring", response.body().toString());
                if (response.isSuccessful())
                {
                    if (response.body()!=null)
                    {
                        Log.i("onSuccess", response.body().toString());
                        String jsonDV = response.body().toString();
                        spJSONDV(jsonDV);
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }



    //todo: chuyển dữ liệu về spinner phần dịch vụ
    private void spJSONDV(String jsonDV) {
        try {
            arrayDichVu= new ArrayList<>();
            jsonDichVu=new JSONArray(jsonDV);
            for (int i=0;i<jsonDichVu.length();i++)
            {
                modelDV =new ModelDV();
                JSONObject object=jsonDichVu.getJSONObject(i);

                modelDV.setID(object.optString("ID"));
                modelDV.setCODE(object.optString("CODE"));
                modelDV.setNAME(object.optString("NAME"));

                arrayDichVu.add(modelDV);

            }
            dichVu.clear();
            for (int i=0;i<arrayDichVu.size();i++)
            {
                dichVu.add(arrayDichVu.get(i).getNAME().toString());
            }

            ArrayAdapter<String> spAdapter=new ArrayAdapter<String>(ThemHoTroActivity.this,android.R.layout.simple_spinner_item,dichVu);
            spDVThemHoTro.setAdapter(spAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
     //todo: lấy id của dịch vụ
    private String getIDDV(int position)
    {

        try {
            JSONObject object=jsonDichVu.getJSONObject(position);
            gamelist_id=object.getString("ID");
            Log.i("ID",gamelist_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return gamelist_id;
    }
    //todo: lấy id của nhà cung cấp
    private String getSpNCC(int position)
    {

        try {
            JSONObject object=jsonArray.getJSONObject(position);
            idncc=object.getString("PARTNER_ID");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return idncc;
    }

    private void getData() {
        String constr = "get_all_partners";
        RetroClient.get_all_partners(constr, token, new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("Responsestring", response.body().toString());
                if (response.isSuccessful())
                {
                    if (response.body()!=null)
                    {
                        Log.i("onSuccess", response.body().toString());
                        String jsonresponse = response.body().toString();
                        spJSON(jsonresponse);
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });


    }
    //todo: hiển thị lên spinner
    private void spJSON(String jsonresponse) {
        try {
        arrayList=new ArrayList<>();
        jsonArray=new JSONArray(jsonresponse);
        for (int i=0;i<jsonArray.length();i++)
        {
                modelNCC  =new ModelNCC();
                JSONObject object=jsonArray.getJSONObject(i);
                modelNCC.setPARTNER_ID(object.optString("PARTNER_ID"));
                modelNCC.setPARTNER_NAME(object.optString("PARTNER_NAME"));
                arrayList.add(modelNCC);

            }
        nhaCC.clear();
        for (int i=0;i<arrayList.size();i++)
        {
            nhaCC.add(arrayList.get(i).getPARTNER_NAME().toString());
        }

        ArrayAdapter<String> spAdapter=new ArrayAdapter<String>(ThemHoTroActivity.this,android.R.layout.simple_spinner_item, nhaCC);
        spAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spNCCThemHoTro.setAdapter(spAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

//todo:lấy token
    protected void getToken() {
        Intent intent = getIntent();
        if (intent != null) {
            token = intent.getStringExtra("token");
            thoigian=intent.getStringExtra("time");
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnGuiYeuCauThemHoTro:

                themHoTro();
                Intent intent=new Intent(ThemHoTroActivity.this,HoTroActivity.class);
                intent.putExtra("token",token);
                intent.putExtra("time",thoigian);
                startActivity(intent);
                Toast.makeText(ThemHoTroActivity.this,"Gửi hỗ trợ thành công !",Toast.LENGTH_SHORT).show();
                break;
        }

    }

    private void themHoTro() {
        JsonObject object=new JsonObject();
        object.addProperty("constr","insert_customer_support");
        object.addProperty("psMsisdn","0987023195");
        object.addProperty("psContent",edtThemHoTro.getText().toString());
        object.addProperty("psPartner_id",idncc);
        object.addProperty("psGamelist_id",gamelist_id);
        object.addProperty("psSub_id","3370");

        RetroClient.update(object, token, new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });




    }


}
