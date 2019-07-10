package com.example.ctt2019.Activity;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ctt2019.API.RetroClient;
import com.example.ctt2019.Adapter.AdapterLichSu.AdapterLichSuNapTien;
import com.example.ctt2019.Adapter.AdapterLichSu.AdapterLichSuSms;
import com.example.ctt2019.Model.Lichsunaptien.ModelLichSuNapTien;
import com.example.ctt2019.Model.Lichsusms.ModelSms;
import com.example.ctt2019.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentLichSu  extends Fragment implements View.OnClickListener{
    TextView mTitle;
    Button btnLichSuNapTien,btnLichSuSMS;
    ImageButton imgTuNgay,imgDenNgay;
    FrameLayout frLichSuNapTien,frLichSuSms;
    List<ModelSms> smsList=null;
    List<ModelLichSuNapTien> lichSuNapTienList=null;
    EditText edtStartDate,edtEnddate;
    private LinearLayoutManager layoutManager;
    String date1,date2;
    private String token=null;
    LinearLayout lntieptheo;
    String thoiGian;
    Toolbar toolbar;
    SimpleDateFormat sdf;

    //******************************
    public int currentPage = 1;
    public RecyclerView recyclerView;
    Button btnPre,btnNext;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lichsu, container, false);
        toolbar=view.findViewById(R.id.toolbar);
        btnLichSuNapTien=view.findViewById(R.id.btnLichSuNapTien);
        btnLichSuSMS=view.findViewById(R.id.btnLichSuSMS);
        imgTuNgay=view.findViewById(R.id.imgDate);
        imgDenNgay=view.findViewById(R.id.imgDate2);
        edtStartDate=view.findViewById(R.id.edtStartDate);
        edtEnddate=view.findViewById(R.id.edtEndDate);
        frLichSuNapTien=view.findViewById(R.id.frLichSuNapTien);
        frLichSuSms=view.findViewById(R.id.frlichSuSms);
        lntieptheo=view.findViewById(R.id.lntieptheo);
        btnPre=view.findViewById(R.id.Previous);
        btnNext=view.findViewById(R.id.Next);
        recyclerView=view.findViewById(R.id.rcLichSuSMS);


        btnPre.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnLichSuNapTien.setOnClickListener(this);
        btnLichSuSMS.setOnClickListener(this);
        imgTuNgay.setOnClickListener(this);
        imgDenNgay.setOnClickListener(this);


        sdf=new SimpleDateFormat("dd-MM-YYYY");

         //todo:lấy token
        getToken();
        //todo:gán toolbar cho fragment
        toolbar();
        //tạo sự kiện cho toolbar
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
        return view;
         }

        private void toolbar() {
            AppCompatActivity activity =    (AppCompatActivity) getActivity();
            activity.setSupportActionBar(toolbar);
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mTitle =toolbar.findViewById(R.id.toolbar_title);
            mTitle.setText("LỊCH SỬ");
            toolbar.setTitle("");
        }


       @Override
    public void onClick(View v) {
           switch (v.getId()) {
               case R.id.btnLichSuNapTien:

                   frLichSuSms.setVisibility(View.GONE);
                   frLichSuNapTien.setVisibility(View.VISIBLE);

                   if (edtStartDate.getText().toString().compareTo(edtEnddate.getText().toString()) > 0) {
                       Toast.makeText(getActivity(), "Sai định dạng ngày !!", Toast.LENGTH_SHORT).show();
                   } else if (edtStartDate.length() == 0 || edtEnddate.length() == 0) {
                       Toast.makeText(getActivity(), "Bạn cần phải nhập vào ngày !!", Toast.LENGTH_SHORT).show();
                   } else {
                       //Put data
                       lichSuNapTien();
                       lntieptheo.setVisibility(View.VISIBLE);
                   }

                break;

            case R.id.btnLichSuSMS:
                frLichSuNapTien.setVisibility(View.GONE);
                frLichSuSms.setVisibility(View.VISIBLE);

                if (edtStartDate.getText().toString().compareTo(edtEnddate.getText().toString())>0) {
                    Toast.makeText(getActivity(), "Sai định dạng ngày !!", Toast.LENGTH_SHORT).show();
                }


                else if (edtStartDate.length() == 0 || edtEnddate.length() == 0)
                {
                    Toast.makeText(getActivity(), "Bạn cần phải nhập vào ngày !!", Toast.LENGTH_SHORT).show();
                } else {
                    //Put data
                    getHistory();
                    lntieptheo.setVisibility(View.VISIBLE);

                }
                break;

            case R.id.imgDate:
                TuNgay();
                break;

            case R.id.imgDate2:
                DenNgay();
                break;

            case R.id.Next:

                currentPage=currentPage+1;

                lichSuNapTien();
                getHistory();



                break;
            case R.id.Previous:
                currentPage=currentPage-1;

                    getHistory();
                    lichSuNapTien();

                break;
        }

    }

    private void lichSuNapTien() {
        String constr = "get_history_charging";
        String psMsisdn = "0987023195";
        String psStartdate = edtStartDate.getText().toString();
        String psEnddate = edtEnddate.getText().toString();
        int psPageno=currentPage;
        int psPagerec=2;
        //String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjcmVhdGVkQXQiOjE1NTk3MjY4NTIsInVzZXJJZCI6InRoYW5ncGgifQ.i1Rheu3ahWZ15i5B1VNTOrXHigj_dggD-idbdo2lXHc";
        RetroClient.lichSuNapTien(constr, psMsisdn, psStartdate, psEnddate,psPageno,psPagerec, token, new Callback<List<ModelLichSuNapTien>>() {
            @Override
            public void onResponse(Call<List<ModelLichSuNapTien>> call, Response<List<ModelLichSuNapTien>> response) {
                lichSuNapTienList=response.body();
                hienThiLichSuNapTien();

            }

            @Override
            public void onFailure(Call<List<ModelLichSuNapTien>> call, Throwable t) {
                Toast.makeText(getActivity(),"Vui lòng kết nối Internet .",Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void getToken()
    {
        HomeActivity homeActivity= (HomeActivity) getActivity();
        token=homeActivity.sendToken();
        thoiGian=homeActivity.sendTime();
    }
    private void getHistory() {
        String constr = "get_history_sms";
        String psMsisdn = "0987023195";
        String psStartdate = edtStartDate.getText().toString();
        String psEnddate = edtEnddate.getText().toString();
        int psPageno=currentPage;
        int psPagerec=3;
        //  String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjcmVhdGVkQXQiOjE1NTk3MjY4NTIsInVzZXJJZCI6InRoYW5ncGgifQ.i1Rheu3ahWZ15i5B1VNTOrXHigj_dggD-idbdo2lXHc";

        RetroClient.lichSuSms(constr, psMsisdn, psStartdate, psEnddate,psPageno,psPagerec, token, new Callback<List<ModelSms>>() {
            @Override
            public void onResponse(Call<List<ModelSms>> call, Response<List<ModelSms>> response) {
                smsList=response.body();
                hienThiLichSuSms();

            }

            @Override
            public void onFailure(Call<List<ModelSms>> call, Throwable t) {

            }
        });
    }




    private void hienThiLichSuNapTien() {
        RecyclerView rcLichSuNapTien=getView().findViewById(R.id.rcLichSuNapTien);
        layoutManager=new LinearLayoutManager(getContext());
        rcLichSuNapTien.setLayoutManager(layoutManager);
        AdapterLichSuNapTien adapterLichSuNapTien=new AdapterLichSuNapTien(lichSuNapTienList,getContext());
        rcLichSuNapTien.setAdapter(adapterLichSuNapTien);
    }

    private void hienThiLichSuSms() {
        RecyclerView rcLichSuSms=getView().findViewById(R.id.rcLichSuSMS);
        layoutManager=new LinearLayoutManager(getContext());
        rcLichSuSms.setLayoutManager(layoutManager);
        AdapterLichSuSms adapterLichSuSms=new AdapterLichSuSms(smsList,getContext());
        rcLichSuSms.setAdapter(adapterLichSuSms);

    }


    private void DenNgay() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), AlertDialog.THEME_DEVICE_DEFAULT_LIGHT, new OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(year,monthOfYear,dayOfMonth);
                edtEnddate.setText(sdf.format(calendar.getTime()));
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    private void TuNgay() {

        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), AlertDialog.THEME_DEVICE_DEFAULT_LIGHT, new OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(year,monthOfYear,dayOfMonth);

                edtStartDate.setText(sdf.format(calendar.getTime()));
            }
        }, year, month, day);
        datePickerDialog.show();
    }


}
