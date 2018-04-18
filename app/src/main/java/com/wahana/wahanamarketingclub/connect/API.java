package com.wahana.wahanamarketingclub.connect;

/**
 * Created by lely on 12/28/17.
 */

import android.graphics.Bitmap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wahana.wahanamarketingclub.activities.CustomerProspectAddActivity;
import com.wahana.wahanamarketingclub.fragments.ProfileCustomerProspectFragment;
import com.wahana.wahanamarketingclub.model.ActivitySalesmenAdd;
import com.wahana.wahanamarketingclub.model.ActivitySalesmenIndex;
import com.wahana.wahanamarketingclub.model.CustomerProspectAdd;
import com.wahana.wahanamarketingclub.model.CustomerProspectIndex;
import com.wahana.wahanamarketingclub.model.LoginUser;
import com.wahana.wahanamarketingclub.model.MasterAboutUs;
import com.wahana.wahanamarketingclub.model.MasterJenisCustomer;
import com.wahana.wahanamarketingclub.model.MasterLovCustomers;
import com.wahana.wahanamarketingclub.model.MasterOccupation;
import com.wahana.wahanamarketingclub.model.MasterPosCode;
import com.wahana.wahanamarketingclub.model.MasterReligion;
import com.wahana.wahanamarketingclub.model.MasterTypeCustomer;
import com.wahana.wahanamarketingclub.model.MyProfile;
import com.wahana.wahanamarketingclub.model.NotifActivitySalesman;
import com.wahana.wahanamarketingclub.model.StatusActivity;
import com.wahana.wahanamarketingclub.model.TypeActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class API {

    static Retrofit retrofit;
    public static String baseURL ="http://192.168.18.226:8080/";
    public static String baseURLPicasso ="http://192.168.18.226:8080";
//    public static String baseURL ="http://192.168.0.101:8080/";
//    public static String baseURLPicasso ="http://192.168.0.101:8080";

    public static Retrofit getInstance() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .build();

            Gson gson = new GsonBuilder().setLenient().create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }


    // This method  converts Bitmap to RequestBody
    private static MultipartBody.Part toImageRequestBody(Bitmap bitmap, String name, String fileName, String type) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
        byte[] bitmapdata = bos.toByteArray();
        RequestBody photo = RequestBody.create(MediaType.parse("multipart/form-data"), bitmapdata);
        return MultipartBody.Part.create(okhttp3.Headers.of("Content-Disposition",
                "form-data; name=\"" + name + "\"; filename=\"" + fileName + "\"\r\nContent-Type: " + type + "\r\n\r\n\r\n"), photo);

    }
    // This method  converts Bitmap to RequestBody
    public static MultipartBody.Part toInputStreamRequestBody(InputStream stream, String name, String fileName, String type) {
        byte[] buf;
        try {
            buf = new byte[stream.available()];
            while (stream.read(buf) != -1);

            RequestBody photo = RequestBody.create(MediaType.parse("multipart/form-data"), buf);
            return MultipartBody.Part.create(okhttp3.Headers.of("Content-Disposition",
                    "form-data; name=\"" + name + "\"; filename=\"" + fileName + "\"\r\nContent-Type: " + type + "\r\n\r\n\r\n"), photo);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static MultipartBody.Part toTextRequestBody(String value, String name) {
        return MultipartBody.Part.create(okhttp3.Headers.of("Content-Disposition", "form-data; name=\"" + name + "\""),
                RequestBody.create(MediaType.parse("text/plain"), value));
    }


    public static Call<LoginUser> login(LoginUser loginUser) {
        WmcService service = getInstance().create(WmcService.class);
        return service.login(loginUser);
    }

    public static Call<ArrayList<CustomerProspectIndex>> getCustomerProspectIndex(String id) {
        WmcService service = getInstance().create(WmcService.class);
        return service.getCustomerProspectIndex(id);
    }

    public static Call<ArrayList<MasterReligion>> getreligion() {
        WmcService service = getInstance().create(WmcService.class);
        return service.getReligion();
    }

    public static Call<ArrayList<MasterPosCode>> getMasterPosCode() {
        WmcService service = getInstance().create(WmcService.class);
        return service.getMasterPosCode();

}
    public static Call<ArrayList<MasterJenisCustomer>> getMasterJenisCustomer() {
        WmcService service = getInstance().create(WmcService.class);
        return service.getMasterJenisCustomer();
    }

    public static Call<ArrayList<MasterTypeCustomer>> getMasterTypeCustomer() {
        WmcService service = getInstance().create(WmcService.class);
        return service.getMasterTypeCustomer();
    }

    public static Call<ArrayList<MasterOccupation>> getMasterOccupation() {
        WmcService service = getInstance().create(WmcService.class);
        return service.getMasterOccupation();
    }

    public static Call<ArrayList<MasterLovCustomers>> getLovCustomerProspect() {
        WmcService service = getInstance().create(WmcService.class);
        return service.getLovCustomerProspect();
    }

    public static Call<CustomerProspectAdd> insertCustomerProspect(CustomerProspectAdd customerProspectIndex) {
        WmcService service = getInstance().create(WmcService.class);
        return service.insertCustomerProspect(customerProspectIndex);
    }

    public static Call<CustomerProspectAdd> insertCustomerProspectMultipart(CustomerProspectAdd customerProspectIndex, ArrayList<CustomerProspectAddActivity.FileUpload> fileUploads) {
        WmcService service = getInstance().create(WmcService.class);

        List<MultipartBody.Part> requestBody = new ArrayList<>();

        requestBody.add(toTextRequestBody(new Gson().toJson(customerProspectIndex), "data"));
        for (CustomerProspectAddActivity.FileUpload fileUpload : fileUploads) {
            requestBody.add(toImageRequestBody(fileUpload.getBitmap(), fileUpload.getFileName(), fileUpload.getFileName(), fileUpload.getType()));
        }
        return service.insertCustomerProspect(requestBody);
    }

    public static Call<CustomerProspectAdd> updateCustomerProspectMultipart(String id, CustomerProspectAdd customers, ArrayList<ProfileCustomerProspectFragment.FileUpload> fileUploads) {
        WmcService service = getInstance().create(WmcService.class);


        List<MultipartBody.Part> requestBody = new ArrayList<>();

        requestBody.add(toTextRequestBody(new Gson().toJson(customers), "data"));
        for (ProfileCustomerProspectFragment.FileUpload fileUpload : fileUploads) {
            requestBody.add(toImageRequestBody(fileUpload.getBitmap(), fileUpload.getFieldName(), fileUpload.getFileName(), fileUpload.getType()));
        }
        return service.updateCustomerProspect(id, requestBody);
    }

    public static Call<MyProfile> editProfile(MyProfile user){
        WmcService service = getInstance().create(WmcService.class);
        return service.editProfile(user);
    }

    public static Call<MyProfile> editPassword(MyProfile user){
        WmcService service = getInstance().create(WmcService.class);
        return service.editPasswordProfile(user);
    }

    public static Call<CustomerProspectAdd> getCustomerProspectDetail(String id) {
        WmcService service = getInstance().create(WmcService.class);
        return service.getCustomerProspectDetail(id);
    }

    public static Call<MasterAboutUs> getMasterAboutUs() {
        WmcService service = getInstance().create(WmcService.class);
        return service.getMasterAbutUs();
    }

    public static Call<ArrayList<ActivitySalesmenIndex>> getActivitySalesIndex(String id) {
        WmcService service = getInstance().create(WmcService.class);
        return service.getActivitySalesIndex(id);
    }

    public static Call<ArrayList<StatusActivity>> getStatusActivity() {
        WmcService service = getInstance().create(WmcService.class);
        return service.getStatusActivity();
    }

    public static Call<ArrayList<TypeActivity>> getTypeActivity() {
        WmcService service = getInstance().create(WmcService.class);
        return service.getTypeActivity();
    }

    public static Call<String> insertActivity(ActivitySalesmenAdd activitySalesmenAdd){
        WmcService service = getInstance().create(WmcService.class);
        return service.insertActivity(activitySalesmenAdd);
    }

    public static Call<ArrayList<ActivitySalesmenIndex>> getActivityToday(String id) {
        WmcService service = getInstance().create(WmcService.class);
        return service.getActivityToday(id);
    }

    public static Call<ArrayList<ActivitySalesmenIndex>> getActivityTomorrow(String id) {
        WmcService service = getInstance().create(WmcService.class);
        return service.getActivityTomorrow(id);
    }

    public interface WmcService {

        @POST("api_login")
        Call<LoginUser> login(@Body LoginUser loginUser);

        @GET("api_customer_prospect")
        Call<ArrayList<CustomerProspectIndex>> getCustomerProspectIndex(@Query("id") String id);

        @GET("api_religion")
        Call<ArrayList<MasterReligion>> getReligion();

        @GET("api_poscode")
        Call<ArrayList<MasterPosCode>> getMasterPosCode();

        @GET("api_jenis_customer")
        Call<ArrayList<MasterJenisCustomer>> getMasterJenisCustomer();

        @GET("api_type_customer")
        Call<ArrayList<MasterTypeCustomer>> getMasterTypeCustomer();

        @GET("api_occupation")
        Call<ArrayList<MasterOccupation>> getMasterOccupation();

        @GET("api_lov_customer_prospect")
        Call<ArrayList<MasterLovCustomers>> getLovCustomerProspect();

        @POST("api_customer_prospect")
        Call<CustomerProspectAdd> insertCustomerProspect(@Body CustomerProspectAdd customerProspect);

        @Multipart
        @POST("api_customer_prospect")
        Call<CustomerProspectAdd> insertCustomerProspect(@Part List<MultipartBody.Part> params);

        @Multipart
        @PUT("api_customer_prospect/{id}")
        Call<CustomerProspectAdd> updateCustomerProspect(@Path("id") String id, @Part List<MultipartBody.Part> params);

        @GET("api_customer_prospect_detail")
        Call<CustomerProspectAdd> getCustomerProspectDetail(@Query("id") String id);

        @GET("api_master_about_us")
        Call<MasterAboutUs> getMasterAbutUs();

        @PUT("api_master_user")
        Call<MyProfile> editProfile(@Body MyProfile user);

        @PUT("api_master_userPassword")
        Call<MyProfile> editPasswordProfile(@Body MyProfile user);

        @GET("api_master_activity")
        Call<ArrayList<ActivitySalesmenIndex>> getActivitySalesIndex(@Query("salesman_id") String id);

        @GET("api_master_activity_status")
        Call<ArrayList<StatusActivity>> getStatusActivity();

        @GET("api_master_activity_type")
        Call<ArrayList<TypeActivity>> getTypeActivity();

        @POST("api_master_activity")
        Call<String> insertActivity(@Body ActivitySalesmenAdd activitySalesmenAdd);

        @GET("api_master_activity_today")
        Call<ArrayList<ActivitySalesmenIndex>> getActivityToday(@Query("salesman_id") String id);

        @GET("api_master_activity_tomorrow")
        Call<ArrayList<ActivitySalesmenIndex>> getActivityTomorrow(@Query("salesman_id") String id);
    }
}


