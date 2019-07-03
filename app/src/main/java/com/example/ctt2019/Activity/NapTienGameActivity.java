package com.example.ctt2019.Activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

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


public class NapTienGameActivity extends AppCompatActivity implements View.OnClickListener{
    Toolbar toolbar;
    Button btnNapTien,btnDangKyGoi;
    Spinner spNCCGame,spDichVuGame,spGoiCuocGame;
    EditText edtSoTien,edtTaiKhoan;
    String spncc,spDV,spGoiCuoc;
    private String token;
    String thoiGian,ncc;


    String gamelist_id;
    String sub_id;
    String idncc="";


    private JSONArray jsonArray,jsonDichVu,jsonGoiCuoc;
    private ArrayList<ModelNCC> arrayList;
    private ArrayList<ModelDV> arrayDichVu;
    private ArrayList<ModelGoiCuoc> arrayListGoiCuoc;

    private ArrayList<String> playerNames= new ArrayList<String>();
    private ArrayList<String> dichVu= new ArrayList<String>();
    private ArrayList<String> goiCuoc= new ArrayList<String>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_naptiengame);

        getToken();
        getDataSpinner();
        toolbar =findViewById(R.id.toolbar);
        TextView mTitle=toolbar.findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        mTitle.setText("Nạp tiền game");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtSoTien=findViewById(R.id.edtSoTien);
        edtTaiKhoan=findViewById(R.id.edtTaiKhoan);
        spNCCGame=findViewById(R.id.spNCCGame);
        spDichVuGame=findViewById(R.id.spDichVuGame);
        spGoiCuocGame=findViewById(R.id.spGoiCuocGame);
        btnNapTien=findViewById(R.id.btnNapTien);
        btnDangKyGoi=findViewById(R.id.btnDangKyGoi);
        btnNapTien.setOnClickListener(this);
        btnDangKyGoi.setOnClickListener(this);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iHome=new Intent(NapTienGameActivity.this,HomeActivity.class);
                iHome.putExtra("token",token);
                iHome.putExtra("time",thoiGian);
                startActivity(iHome);
                overridePendingTransition(R.anim.anim_enter,R.anim.anim_exit);
            }
        });

        //todo: sự kienj click của spinner
      spNCCGame.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              getDataDV(position);
               ncc=parent.getItemAtPosition(position).toString();
           //  Toast.makeText(NapTienGameActivity.this,"Chọn"+item,Toast.LENGTH_LONG).show();

          }

          @Override
          public void onNothingSelected(AdapterView<?> parent) {

          }
      });
   spDichVuGame.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
       @Override
       public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
           getDataCircle(position);
           spDV=parent.getItemAtPosition(position).toString();
       }

       @Override
       public void onNothingSelected(AdapterView<?> parent) {

       }
   });

   //
        spGoiCuocGame.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spGoiCuoc=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void getDataCircle(int position) {
        String constr="get_gamelist_circle";
        String psGamelist_id=getIDDV(position);
        RetroClient.get_gamelist_circle(constr, psGamelist_id, token, new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful())
                {
                    if (response.body()!=null)
                    {
                        String jsonCircle=response.body().toString();
                        spJsonCircle(jsonCircle);
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    private void spJsonCircle(String jsonCircle) {

        try {
            arrayListGoiCuoc=new ArrayList<>();
            jsonGoiCuoc = new JSONArray(jsonCircle);
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

            ArrayAdapter<String> spAdapter=new ArrayAdapter<String>(NapTienGameActivity.this,android.R.layout.simple_spinner_item,goiCuoc);
            spAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spGoiCuocGame.setAdapter(spAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void getDataDV(int position) {
        String constr="get_gamelist_by_partner";
        String  psPartner_id=getSpNCC(position);
        Log.i("idpspartner_id",psPartner_id);

    RetroClient.get_gamelist_by_partner(constr, psPartner_id, token, new Callback<String>() {
        @Override
        public void onResponse(Call<String> call, Response<String> response) {
            if (response.isSuccessful())
            {
                Log.i("datadichvu123", response.body().toString());
                if (response.body()!=null)
                {
                    String jsonDV=response.body().toString();
                    spJsonDV(jsonDV);
                    Log.i("datadichvu",jsonDV);
                }
            }

        }

        @Override
        public void onFailure(Call<String> call, Throwable t) {

        }
    });

    }

    private void spJsonDV(String jsonDV) {


        try {
            arrayDichVu=new ArrayList<>();
            jsonDichVu=new JSONArray(jsonDV);
            for (int i=0;i<jsonDichVu.length();i++)
            {
                ModelDV modelDV=new ModelDV();
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

            ArrayAdapter<String> spAdapter=new ArrayAdapter<String>(NapTienGameActivity.this,android.R.layout.simple_spinner_item,dichVu);
            spAdapter.notifyDataSetChanged();
            spDichVuGame.setAdapter(spAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //todo: lấy id dich vu , ncc , game
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

    //id dich vụ
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

    //todo:Lấy dữ liệu json về cho spinner
    private void getDataSpinner() {
        String constr="get_all_partners";
        RetroClient.get_all_partners(constr, token, new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("naptien",response.body().toString());
                if (response.isSuccessful())
                {
                    if (response.body()!=null)
                    {
                        String json=response.body().toString();
                        spJson(json);
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }

    private void spJson(String json) {

        try {
            arrayList=new ArrayList<>();
            jsonArray=new JSONArray(json);
            for (int i=0;i<jsonArray.length();i++)
            {
                ModelNCC modelNCC=new ModelNCC();
                JSONObject object=jsonArray.getJSONObject(i);
                modelNCC.setPARTNER_ID(object.optString("PARTNER_ID"));
                modelNCC.setPARTNER_NAME(object.optString("PARTNER_NAME"));
                arrayList.add(modelNCC);


            }
            playerNames.clear();
            for (int i=0;i<arrayList.size();i++)
            {
                playerNames.add(arrayList.get(i).getPARTNER_NAME().toString());
            }

            ArrayAdapter<String> spAdapter=new ArrayAdapter<String>(NapTienGameActivity.this,android.R.layout.simple_spinner_item,playerNames);
            spAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spNCCGame.setAdapter(spAdapter);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public  void bundle()
    {
        Intent intent=new Intent(NapTienGameActivity.this,XacNhanNapTienActivity.class);
        intent.putExtra("1",ncc);
        intent.putExtra("2",spDV);
        intent.putExtra("3",spGoiCuoc);
        intent.putExtra("4",edtSoTien.getText().toString());
        intent.putExtra("5",edtTaiKhoan.getText().toString());
        intent.putExtra("token",token);
        intent.putExtra("time",thoiGian);

        startActivity(intent);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnNapTien:
                napTienThanhCong();
                Intent inaptienthanhcong=new Intent(NapTienGameActivity.this,XacNhanNapTienActivity.class);
                startActivity(inaptienthanhcong);
                bundle();

                break;

                case R.id.btnDangKyGoi:
                Intent iDKgoicuoc=new Intent(NapTienGameActivity.this,XacNhanNapTienActivity.class);
               // iDKgoicuoc.putExtra("token",token);

                startActivity(iDKgoicuoc);
                    bundle();

                break;

        }

    }




    private void getToken()
    {
        Intent intent=getIntent();
        if (intent!=null)
        {
         token=intent.getStringExtra("token");
        }
        thoiGian=intent.getStringExtra("time");


    }

    private void napTienThanhCong()
    {

        JsonObject object=new JsonObject();
        object.addProperty("command","TRAN_9029");
        object.addProperty("tocken","tocken");
        object.addProperty("checksum","checksum");
        //phone
        object.addProperty("msisdn","0934435389");
        //id ncc : idncc
        object.addProperty("cpid","000044");
        // số tiền
        object.addProperty("totalamount",edtSoTien.getText().toString());
        //dịch vụ
        object.addProperty("contenid","0000440005");
        //game code của mã game
        object.addProperty("gamecode","TSKHM");
        //tài khoản
        object.addProperty("account",edtTaiKhoan.getText().toString());

        RetroClient.register_unregister(object, new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                   thongBaoThanhCong();
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }
    private void thongBaoThanhCong() {
        Button btnDong;
        AlertDialog.Builder dialogBuilder=new AlertDialog.Builder(this);
        LayoutInflater inflater=this.getLayoutInflater();
        @SuppressLint("ResourceType") View dialogView=inflater.inflate(R.layout.layout_diaglog_dkthanhcong, (ViewGroup) findViewById(R.layout.layout_naptiengame));
        btnDong=dialogView.findViewById(R.id.btndong);
        btnDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iNapTienGame=new Intent(getApplicationContext(),NapTienGameActivity.class);
                iNapTienGame.putExtra("token",token);
                startActivity(iNapTienGame);
            }
        });
        dialogBuilder.setView(dialogView);
        AlertDialog b = dialogBuilder.create();
        b.show();
    }
}
