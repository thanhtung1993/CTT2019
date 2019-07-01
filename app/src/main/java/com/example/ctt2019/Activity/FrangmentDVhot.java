package com.example.ctt2019.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ctt2019.API.RetroClient;
import com.example.ctt2019.Adapter.AdapterDichVuHot.AdapterDichVuHot;
import com.example.ctt2019.Model.DichVuHot.ModelDichVuHot;
import com.example.ctt2019.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FrangmentDVhot extends Fragment  {
    TextView mTitle;
    Toolbar toolbar;
    private String token = null;
    RecyclerView recyclerView;
    List<ModelDichVuHot> dichVuHotList;
    private LinearLayoutManager layoutManager;
    String thoiGian;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_dvhot,container,false);





        getToken();
        toolbar=view.findViewById(R.id.toolbar);
        toolbar();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ihome=new Intent(getActivity(),HomeActivity.class);
                ihome.putExtra("time",thoiGian);
                ihome.putExtra("token",token);
                startActivity(ihome);
                getActivity().overridePendingTransition(R.anim.anim_enter,R.anim.anim_exit);
            }
        });


        recyclerView=view.findViewById(R.id.rcDichVuHot);


        get_hot_service();


        return view;
    }


    private void getToken()
    {
      HomeActivity homeActivity= (HomeActivity) getActivity();
      token=homeActivity.sendToken();
      thoiGian=homeActivity.sendTime();
    }

    private void get_hot_service() {
        String constr="get_hot_service";
        RetroClient.get_hot_service(constr, token, new Callback<List<ModelDichVuHot>>() {
            @Override
            public void onResponse(Call<List<ModelDichVuHot>> call, Response<List<ModelDichVuHot>> response) {
              dichVuHotList=response.body();
              Log.i("id",dichVuHotList.toString());
              hienThiDVH();

            }
            @Override
            public void onFailure(Call<List<ModelDichVuHot>> call, Throwable t) {

            }
        });
    }

    private void hienThiDVH() {
        layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
         AdapterDichVuHot adapterDichVuHot=new AdapterDichVuHot(getContext(),dichVuHotList);
        recyclerView.setAdapter(adapterDichVuHot);
}


    public void toolbar()
    {
        //gán toolbar cho fragment
        AppCompatActivity activity =    (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mTitle =toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Gói cước hót");
        toolbar.setTitle("");
    }
}
