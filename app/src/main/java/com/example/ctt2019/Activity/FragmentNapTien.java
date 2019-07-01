package com.example.ctt2019.Activity;


import android.app.Dialog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ctt2019.R;


public class FragmentNapTien extends Fragment implements View.OnClickListener {
    Button btnThanhToan,btnDangKy,btnTiepTheo;
    Dialog dialog;
    TextView mTitle;
    Toolbar toolbar;
    private  String token=null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_naptien, container, false);
        getToken();
        btnThanhToan=view.findViewById(R.id.btnThanhToan);
        btnDangKy=view.findViewById(R.id.btnDangKy);
        btnTiepTheo=view.findViewById(R.id.btnTiepTheo);

        toolbar=view.findViewById(R.id.toolbar);
        btnThanhToan.setOnClickListener(this);
        btnTiepTheo.setOnClickListener(this);
        btnDangKy.setOnClickListener(this);




        Toolbar();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iHome=new Intent(getActivity(),HomeActivity.class);
                iHome.putExtra("token",token);
                startActivity(iHome);
                getActivity().overridePendingTransition(R.anim.anim_enter,R.anim.anim_exit);
            }
        });

        return view;






    }

    private void getToken()
    {
        HomeActivity homeActivity= (HomeActivity) getActivity();
        token=homeActivity.sendToken();
    }
    public void Toolbar()
    {
        //gán toolbar cho fragment
        AppCompatActivity activity =    (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mTitle =toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Tra cứu lịch sử nạp tiền/SMS");
        toolbar.setTitle("");
    }


    @Override
    public void onClick(View v) {
         switch (v.getId())
         {
             case R.id.btnThanhToan:
                 Toast.makeText(getActivity(),"Thanh toán thành công",Toast.LENGTH_SHORT).show();
                 break;
             case R.id.btnDangKy:
                 Toast.makeText(getActivity(),"Dăng ký thành công",Toast.LENGTH_SHORT).show();
                 break;
             case R.id.btnTiepTheo:
                 Intent iNapTien=new Intent(getActivity(), NapTienGameActivity.class);
                 startActivity(iNapTien);
                 break;




         }
    }
}

