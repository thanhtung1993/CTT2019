package com.example.ctt2019.API;


import com.example.ctt2019.Model.DichVuHot.ModelDichVuHot;
import com.example.ctt2019.Model.GoiCuocSuDung.ModelGoiCuocSuDung;
import com.example.ctt2019.Model.HoTro.ModelHoTro;
import com.example.ctt2019.Model.Lichsunaptien.ModelLichSuNapTien;
import com.example.ctt2019.Model.Lichsusms.ModelSms;
import com.example.ctt2019.util.Config;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetroClient {
    public static final String SERVER = Config.BASE_URL;
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

        retrofit = new Retrofit.Builder()
                .baseUrl(SERVER)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static void login(String username, String pass, Callback<ResponseBody> callback) {
        ApiService apiService = getClient().create(ApiService.class);
        Call<ResponseBody> call = apiService.login(username, pass);
        call.enqueue(callback);

    }

    public static void lichSuSms(String constr, String psMsisdn, String psStartdate, String psEnddate,int psPageno,int psPagerec, String token, Callback<List<ModelSms>> callback) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", token);

        ApiService apiService = getClient().create(ApiService.class);
        Call<List<ModelSms>> call = apiService.lichsusms(constr, psMsisdn, psStartdate, psEnddate,psPageno,psPagerec, headers);
        call.enqueue(callback);
    }

    public static void getHome(String constr,String psMsisdn,String token, Callback<List<ModelGoiCuocSuDung>> callback) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", token);

        ApiService apiService = getClient().create(ApiService.class);
        Call<List<ModelGoiCuocSuDung>> call = apiService.listHome(constr, psMsisdn, headers);
        call.enqueue(callback);


    }
    public static void home(String constr,String psMsisdn,String token, Callback<ResponseBody> callback)
    {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", token);

        ApiService apiService = getClient().create(ApiService.class);
        Call<ResponseBody> call=apiService.getHome(constr,psMsisdn,headers);
        call.enqueue(callback);
    }

    public static void lichSuNapTien(String constr, String pStartdate, String pEnddate, String pMsisdn,int psPageno,int psPagerec, String token, Callback<List<ModelLichSuNapTien>> callback) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", token);

        ApiService apiService = getClient().create(ApiService.class);
        Call<List<ModelLichSuNapTien>> call = apiService.lichsunaptien(constr, pStartdate, pEnddate, pMsisdn,psPageno,psPagerec, headers);
        call.enqueue(callback);
    }

    public static void get_hot_service(String constr, String token, Callback<List<ModelDichVuHot>> callback) {
        Map<String, String> dvh = new HashMap<>();
        dvh.put("Content-Type", "application/json");
        dvh.put("Authorization", token);

        ApiService apiService = getClient().create(ApiService.class);
        Call<List<ModelDichVuHot>> call = apiService.get_hot_service(constr, dvh);
        call.enqueue(callback);

    }

    public static void get_customer_support(String constr, String psMsisdn, String token, Callback<List<ModelHoTro>> callback) {
        Map<String, String> hoTro = new HashMap<>();
        hoTro.put("Content-Type", "application/json");
        hoTro.put("Authorization", token);

        ApiService apiService = getClient().create(ApiService.class);
        Call<List<ModelHoTro>> call = apiService.get_customer_support(constr, psMsisdn,hoTro);
        call.enqueue(callback);
    }


    public static void get_all_partners(String constr, String token, Callback<String> callback) {
        Map<String, String> ncc = new HashMap<>();
        ncc.put("Content-Type", "application/json");
        ncc.put("Authorization", token);

        ApiService apiService = getClient().create(ApiService.class);
        Call<String> call = apiService.get_all_partners(constr, ncc);
        call.enqueue(callback);

    }

    public static void get_gamelist_by_partner(String constr, String psPartner_id, String token, Callback<String> callback) {
        Map<String, String> dichvu = new HashMap<>();
        dichvu.put("Content-Type", "application/json");
        dichvu.put("Authorization", token);

        ApiService apiService = getClient().create(ApiService.class);
        Call<String> call = apiService.get_gamelist_by_partner(constr, psPartner_id, dichvu);
        call.enqueue(callback);
    }

    public static void get_gamelist_circle(String constr, String psGamelist_id, String token, Callback<String> callback) {
        Map<String, String> goiCuoc = new HashMap<>();
        goiCuoc.put("Content-Type", "application/json");
        goiCuoc.put("Authorization", token);

        ApiService apiService = getClient().create(ApiService.class);
        Call<String> call = apiService.get_gamelist_circle(constr, psGamelist_id, goiCuoc);
        call.enqueue(callback);
    }

    public static void get_customer_infor(String constr,String psMsisdn,String token,Callback<ResponseBody> callback)
    {
        Map<String, String> info = new HashMap<>();
        info.put("Content-Type", "application/json");
        info.put("Authorization", token);

        ApiService apiService=getClient().create(ApiService.class);
        Call<ResponseBody> call=apiService.get_customer_infor(constr,psMsisdn,info);
        call.enqueue(callback);
    }



    public static void update(JsonObject body,String token,Callback<JsonObject> callback)
    {
        Map<String, String> info = new HashMap<>();
        info.put("Content-Type", "application/json");
        info.put("Authorization", token);

        ApiService apiService=getClient().create(ApiService.class);
        Call<JsonObject> call=apiService.update_infor(body,info);
        call.enqueue(callback);

    }

    //todo:Nạp tiền / dịnh vụ hót
    public static void register_unregister(JsonObject object,Callback<JsonObject> callback)
    {
        ApiService apiService=getClient().create(ApiService.class);
        Call<JsonObject> call=apiService.register_unregister(object);
        call.enqueue(callback);
    }

}

















