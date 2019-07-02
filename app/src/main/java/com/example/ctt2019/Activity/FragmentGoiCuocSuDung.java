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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ctt2019.API.RetroClient;
import com.example.ctt2019.Adapter.AdapterGoiCuocSuDung.AdapterGoiCuocSuDung;
import com.example.ctt2019.Model.GoiCuocSuDung.ModelGoiCuocSuDung;
import com.example.ctt2019.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentGoiCuocSuDung extends Fragment {
    TextView mTitle;
    Toolbar toolbar;
    private String token = null;
    String thoiGian;
    List<ModelGoiCuocSuDung> goiCuocSuDungList=null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_goicuocsudung, container, false);


        getToken();
        toolbar = view.findViewById(R.id.toolbar);

        toolbar();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iHome = new Intent(getActivity(), HomeActivity.class);
                iHome.putExtra("token", token);
                iHome.putExtra("time", thoiGian);
                startActivity(iHome);
                getActivity().overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);
            }
        });
        getData();
        return view;


    }

    public void getData() {
        final String constr = "get_home";
        String psMsisdn = "0987023195";
        RetroClient.getHome(constr, psMsisdn, token, new Callback<List<ModelGoiCuocSuDung>>() {
            @Override
            public void onResponse(Call<List<ModelGoiCuocSuDung>> call, Response<List<ModelGoiCuocSuDung>> response) {
                goiCuocSuDungList=response.body();
                 hienThi();
            }

            @Override
            public void onFailure(Call<List<ModelGoiCuocSuDung>> call, Throwable t) {

            }
        });

    }

    private void getToken() {
        HomeActivity homeActivity= (HomeActivity) getActivity();
        token=homeActivity.sendToken();
        thoiGian=homeActivity.sendTime();

    }


  private void hienThi() {
      RecyclerView rcGoiCuocSuDung = getView().findViewById(R.id.rcGoiCuoc);
      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
      rcGoiCuocSuDung.setLayoutManager(layoutManager);
      AdapterGoiCuocSuDung adapterGoiCuocSuDung=new AdapterGoiCuocSuDung(getContext(),goiCuocSuDungList);
      rcGoiCuocSuDung.setAdapter(adapterGoiCuocSuDung);
      adapterGoiCuocSuDung.setData(token);
      adapterGoiCuocSuDung.setTime(thoiGian);
    }

    public void toolbar()
    {
        //gán toolbar cho fragment
        AppCompatActivity activity =    (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mTitle =toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Gói cước đang sử dụng");
        toolbar.setTitle("");
    }
}

