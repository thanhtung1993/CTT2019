package com.example.ctt2019.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ctt2019.API.RetroClient;
import com.example.ctt2019.Model.Home.ModelHome;
import com.example.ctt2019.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentGoiCuocSuDung extends Fragment implements View.OnClickListener{
    TextView mTitle;
    Toolbar toolbar;
    Button btnHuyGoiCuoc;
    LinearLayout lnHuy;
    TextView txtTenGoiCuoc,txtGiaTien,txtChuKiCuoc,txtNCC,txtDKLandau,txtThoiGianGiaHanGanNhat,txtTongSoTienDaNop;
    private RecyclerView.LayoutManager layoutManager;
    List<ModelHome> modelGoiCuocSuDungList = null;
    private String token=null;
    String thoiGian;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_goicuocsudung,container,false);



        getToken();
        toolbar=view.findViewById(R.id.toolbar);
        btnHuyGoiCuoc=view.findViewById(R.id.btnHuyGoiCuoc);
        txtChuKiCuoc=view.findViewById(R.id.txtChuKiCuoc);
        txtDKLandau=view.findViewById(R.id.txtDKLandau);
        txtGiaTien=view.findViewById(R.id.txtGiaTien);
        txtTenGoiCuoc=view.findViewById(R.id.txtTenGoiCuoc);
        txtNCC=view.findViewById(R.id.txtNCC);
        txtThoiGianGiaHanGanNhat=view.findViewById(R.id.txtThoiGianGiaHanGanNhat);
        txtTongSoTienDaNop=view.findViewById(R.id.txtTongSoTienDaNop);


        btnHuyGoiCuoc.setOnClickListener(this);
        Toolbar();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iHome=new Intent(getActivity(),HomeActivity.class);
                iHome.putExtra("token",token);
                iHome.putExtra("time",thoiGian);
                startActivity(iHome);
                getActivity().overridePendingTransition(R.anim.anim_enter,R.anim.anim_exit);
            }
        });
       getDataJson();
        return view;


    }

    private void getDataJson() {
        String constr="get_home";
        String psMsisdn="0987023195";
        RetroClient.getHome(constr,psMsisdn,token, new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response!=null && response.code() == HttpURLConnection.HTTP_OK)
                {
                    try {
                        String body=response.body().string();
                        JSONArray jsonArray=new JSONArray(body);
                        if (jsonArray.length()>0)
                        {
                            JSONObject object=jsonArray.getJSONObject(0);
                            if (object!=null)
                            {
                                String tengoicuoc=object.optString("VASGATEFTPACCOUNT");
                                String giagoicuoc=object.optString("PRICE");
                                String chuki=object.optString("FREE_CIRCLE");
                                String dichvu=object.optString("PARTNER_NAME");
                                // String ncc=object.optString("")
                                String dklandau=object.optString("TIME_START");
                                String giahan=object.optString("LASTDATE_REMIND");
                                //   String tongtiendanap=object.optString("")


                                txtTenGoiCuoc.setText(tengoicuoc);
                                txtGiaTien.setText(giagoicuoc);
                                txtChuKiCuoc.setText(chuki);
                                txtNCC.setText("VMG Media");
                                txtDKLandau.setText(dklandau);
                                txtThoiGianGiaHanGanNhat.setText(giahan);
                                txtTongSoTienDaNop.setText("10.000.000 VNĐ");
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

    private void getToken() {
        HomeActivity homeActivity= (HomeActivity) getActivity();
        token=homeActivity.sendToken();
        thoiGian=homeActivity.sendTime();

    }


   /* private void HienThi() {
     //   RecyclerView rcGoiCuocSuDung=getView().findViewById(R.id.rcGoiCuoc);
        layoutManager=new LinearLayoutManager(getContext());
        rcGoiCuocSuDung.setLayoutManager(layoutManager);
        AdapterGoiCuocSuDung adapterGoiCuocSuDung=new AdapterGoiCuocSuDung(modelGoiCuocSuDungList,getContext());
        rcGoiCuocSuDung.setAdapter(adapterGoiCuocSuDung);
    }*/

    public void Toolbar()
    {
        //gán toolbar cho fragment
        AppCompatActivity activity =    (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mTitle =toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Gói cước đang sử dụng");
        toolbar.setTitle("");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnHuyGoiCuoc:




                 Button btnHuy,btnDongY;
                 AlertDialog.Builder dialogBuilder=new AlertDialog.Builder(getActivity());
                 final LayoutInflater inflater=this.getLayoutInflater();
                 @SuppressLint("ResourceType") final
                 View dialogView=inflater.inflate(R.layout.layout_dialog_xacnhanhuygoicuoc, (ViewGroup) getActivity().findViewById(R.layout.fragment_goicuocsudung));

                 btnDongY=dialogView.findViewById(R.id.btnDongY);
                 btnHuy=dialogView.findViewById(R.id.btnHuy);
                 btnHuy.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {


                     }
                 });

                 btnDongY.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         Toast.makeText(getActivity(),"Đồng ý",Toast.LENGTH_SHORT).show();
                         Button btnHuyGoiThanhCong;
                         AlertDialog.Builder dialogBuilder=new AlertDialog.Builder(getActivity());
                        @SuppressLint("ResourceType") View dialogView1=inflater.inflate(R.layout.layout_dialog_huythanhcong, (ViewGroup) getActivity().findViewById(R.layout.fragment_goicuocsudung));
                        btnHuyGoiThanhCong=dialogView1.findViewById(R.id.btnDong);
                        btnHuyGoiThanhCong.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


                               
                            }
                        });
                         dialogBuilder.setView(dialogView1);
                         AlertDialog b = dialogBuilder.create();
                         b.show();

                     }
                 });
                 btnHuy.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                       Toast.makeText(getActivity(),"Hủy",Toast.LENGTH_SHORT).show();

                     }
                 });
                dialogBuilder.setView(dialogView);
                AlertDialog b = dialogBuilder.create();
                b.show();

                break;
        }
    }
}

