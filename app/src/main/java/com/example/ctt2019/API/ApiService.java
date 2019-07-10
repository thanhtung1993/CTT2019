package com.example.ctt2019.API;


import com.example.ctt2019.Model.DichVuHot.ModelDichVuHot;
import com.example.ctt2019.Model.GoiCuocSuDung.ModelGoiCuocSuDung;
import com.example.ctt2019.Model.HoTro.ModelBinhLuan;
import com.example.ctt2019.Model.HoTro.ModelHoTro;
import com.example.ctt2019.Model.Lichsunaptien.ModelLichSuNapTien;
import com.example.ctt2019.Model.Lichsusms.ModelSms;
import com.example.ctt2019.util.Config;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface ApiService {

    //todo:login
    @FormUrlEncoded
    @POST(Config.login)
    Call<ResponseBody> login(@Field("username") String username,
                             @Field("password") String password);


    //todo:goicuoc su dung

    @GET(Config.SERVER)
    Call<ResponseBody> listHome(@Query("constr") String constr, @Query("psMsisdn") String psMsisdn, @HeaderMap Map<String,String> header);
  //
    @GET(Config.SERVER)
    Call<List<ModelGoiCuocSuDung>> goiCuocHot(@Query("constr") String constr, @Query("psMsisdn") String psMsisdn, @HeaderMap Map<String,String> header);
    //
    //todo:lay lich su sms
    @GET(Config.SERVER)
    Call<List<ModelSms>> lichsusms(@Query("constr") String constr,
                                   @Query("psMsisdn") String psMsisdn,
                                   @Query("psStartdate") String psStartdate,
                                   @Query("psEnddate") String psEnddate,
                                   @Query("psPageno") int psPageno,
                                   @Query("psPagerec") int psPagerec,
                                   @HeaderMap Map<String, String> headers);

    //todo: lấy lịch sử nạp tiền
    @GET(Config.SERVER)
    Call<List<ModelLichSuNapTien>> lichsunaptien(@Query("constr") String constr,
                                                 @Query("psMsisdn") String psMsisdn,
                                                 @Query("psStartdate") String psStartdate,
                                                 @Query("psEnddate") String psEnddate,
                                                 @Query("psPageno") int psPageno,
                                                 @Query("psPagerec") int psPagerec,
                                                 @HeaderMap Map<String, String> headers);


    //todo:Lấy dịch vụ hót
    @GET(Config.SERVER)
    Call<List<ModelDichVuHot>> get_hot_service(@Query("constr") String constr,
                                               @HeaderMap Map<String, String> headers);



    //todo:Lấy danh sách hỗ trợ
    @GET(Config.SERVER)
    Call<List<ModelHoTro>> get_customer_support(@Query("constr") String constr,
                                                @Query("psMsisdn") String psMsisdn,
                                                @Query("psPageno") int psPageno,
                                                @Query("psPagerec") int psPagerec,
                                                @HeaderMap Map<String, String> headers);

    @GET(Config.SERVER)
    Call<ResponseBody> getAllSupport(@Query("constr") String constr,
                                     @Query("psMsisdn") String psMsisdn,
                                     @HeaderMap Map<String, String> headers);


    //todo:NCC hỗ trợ
     @GET(Config.NCCHOTRO)

     Call<String> get_all_partners(@Query("constr") String constr,
                                           @HeaderMap Map<String, String> headers);
     //todo:Thêm hỗ trợ phần dịch vụ
    @GET(Config.SERVER)
    Call<String> get_gamelist_by_partner(@Query("constr") String constr,
                                                @Query("psPartner_id") String psPartner_id,
                                                @HeaderMap Map<String, String> headers);

    //todo: Thêm hỗ trợ phần gói cước
    @GET(Config.SERVER)
    Call<String> get_gamelist_circle (@Query("constr") String constr,
                                      @Query("psGamelist_id") String psGamelist_id,
                                      @HeaderMap Map<String, String> headers);

    //todo:Hiển thị thông tin
    @GET(Config.SERVER)
    Call<ResponseBody> get_customer_infor(@Query("constr") String constr,
                                          @Query("psMsisdn") String psMsisdn,
                                          @HeaderMap Map<String, String> headers);
    //todo:Cập nhật thông tin/Thêm hỗ trợ/thêm bình luận
    @POST(Config.UPDATE_INSERT)
    Call<JsonObject> update_infor(@Body JsonObject body,
                                  @HeaderMap Map<String, String> headers);

   //todo: đăng kí gói cước / hủy gói cước
    @Headers({"Content-Type:application/json"})
    @POST(Config.REGISTER_UNREGISTER)
    Call<JsonObject> register_unregister(@Body JsonObject object);

    //todo:thêm bình luận hỗ trợ
    @GET(Config.SERVER)
    Call<List<ModelBinhLuan>> layToanBoBinhLuan(@Query("constr") String constr,
                                                @Query("psMsisdn") String psMsisdn,
                                                @Query("psParent_id") String psParent_id,
                                                @HeaderMap Map<String, String> headers);

    //todo: đếm bình luận
     @GET(Config.SERVER)
    Call<ResponseBody> demBinhLuan(@Query("constr") String constr,
                                   @Query("psParent_id") String psParent_id,
                                   @HeaderMap Map<String, String> headers);

     //todo: edit hỗ trợ lấy toàn bộ thoogn tin
    @GET(Config.SERVER)
    Call<ResponseBody> layToanBoHoTro(
            @Query("constr") String constr,
            @Query("psId") String psId,
            @HeaderMap Map<String, String> headers);












}


