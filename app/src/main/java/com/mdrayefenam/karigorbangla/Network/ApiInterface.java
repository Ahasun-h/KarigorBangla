package com.mdrayefenam.karigorbangla.Network;

import com.mdrayefenam.karigorbangla.Responce.LoginModel.LoginResponce;
import com.mdrayefenam.karigorbangla.Responce.NotificationModel.NotificationResponce;
import com.mdrayefenam.karigorbangla.Responce.SignUpModel.SinUpResponce;
import com.mdrayefenam.karigorbangla.ServiceProvider.DeletServiceProviderPendingJobOfferModel.ServiceProviderPendingJobOfferDeletResponce;
import com.mdrayefenam.karigorbangla.ServiceProvider.ImageSliderModel.ImageSliderResponce;
import com.mdrayefenam.karigorbangla.ServiceProvider.ProviderConfirmJobListModel.ProviderConfirmJobListResponce;
import com.mdrayefenam.karigorbangla.ServiceProvider.ServiceConfirmModel.ServiceConfirmResponce;
import com.mdrayefenam.karigorbangla.ServiceProvider.ServiceProviderArriveStatusChangeModel.ServiceProviderArriveStatusChangeResmpoce;
import com.mdrayefenam.karigorbangla.ServiceProvider.ServiceProviderInformationModel.ServiceProviderInformationResponce;
import com.mdrayefenam.karigorbangla.ServiceProvider.ServiceTakerJobOfferModel.ServiceProviderJobOfferResponce;
import com.mdrayefenam.karigorbangla.ServiceProvider.ShowServiceProviderJobHistoryModel.ShowServiceProviderJobHistoryResponce;
import com.mdrayefenam.karigorbangla.ServiceTaker.AddJobHistoryModel.AddJobHistoryRespoce;
import com.mdrayefenam.karigorbangla.ServiceTaker.ChageJobCompleteStatusModel.JobCompleteStatusResmpoce;
import com.mdrayefenam.karigorbangla.ServiceTaker.InvoiceModel.InvoiceResponce;
import com.mdrayefenam.karigorbangla.ServiceTaker.ServiceProviderConfirmJobModel.ServiceProviderConfirmJobResmpoce;
import com.mdrayefenam.karigorbangla.ServiceTaker.ServiceProviderLocationModel.ServiceProviderLocationForServiceTakerResponce;
import com.mdrayefenam.karigorbangla.ServiceTaker.ServiceProviderPendingJobOfferModel.ShowServiceTakerPendingJobOfferResmpoce;
import com.mdrayefenam.karigorbangla.ServiceTaker.ServiceTakerInterestModel.ServiceTakerInterestResponce;
import com.mdrayefenam.karigorbangla.ServiceTaker.ServiceTakerProfileUpdateModel.ServiceTakerProfileUpdateResponce;
import com.mdrayefenam.karigorbangla.ServiceTaker.ShowTakerJobProbiderArrivedModel.ShowTakerJobProbiderArrivedConfirmResmpoce;
import com.mdrayefenam.karigorbangla.ServiceTaker.showServiceTakerJobHistoryModel.ShowServiceTakerJobHistoryResponce;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {


    @FormUrlEncoded
    @POST("auth/user_login")
    Call<LoginResponce> userLogin(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("auth/user_register")
    Call<SinUpResponce> userRegister(@Field("name") String name,
                                     @Field("email") String email ,
                                     @Field("mobile") String mobile ,
                                     @Field("gender") String gender,
                                     @Field("address") String address,
                                     @Field("password") String password,
                                     @Field("user_type") String user_type,
                                     @Field("user_profile_photo") String user_profile_photo);

    @FormUrlEncoded
    @POST("service_provider/save_info")
    Call<ServiceProviderInformationResponce> saveCategoryInfo(@Field("category") String category,
                                                              @Field("hourly_rate") String hourly_rate ,
                                                              @Field("latitude") String latitude,
                                                              @Field("longitude") String longitude,
                                                              @Field("user_id") String user_id);


    @Headers({"Accept: application/json"})
    @GET("iamge_slider/show_iamge")
    Call<ImageSliderResponce> imageSlider();


    @FormUrlEncoded
    @POST("service_provider/show_location")
    Call<ServiceProviderLocationForServiceTakerResponce> ServiceProviderLocationForServiceTakeData(@Field("longitude") String longitude,
                                                                                                   @Field("lattitude") String lattitude);


//    @POST("service_provider/show_location")
//    Call<ServiceProviderLocationForServiceTakerResponce> ServiceProviderLocationForServiceTakeData(@Query("long") String longitude,
//                                                                                                   @Query("latt") String lattitude);

    @FormUrlEncoded
    @POST("auth/profile_update")
    Call<ServiceTakerProfileUpdateResponce> serviceTakerProfileUpdateResponce(@Field("name") String name,
                                                                              @Field("email") String email ,
                                                                              @Field("gender") String gender,
                                                                              @Field("address") String address,
                                                                              @Field("user_profile_photo") String user_profile_photo);


    @FormUrlEncoded
    @POST("interest/add_interest")
    Call<ServiceTakerInterestResponce> serviceTakerInterestResponce(@Field("provider_id") String provider_id,
                                                                    @Field("provider_name") String provider_name ,
                                                                    @Field("mobile") String mobile,
                                                                    @Field("service_taker_id") String service_taker_id,
                                                                    @Field("service_taker_name") String service_taker_name,
                                                                    @Field("service_taker_email") String service_taker_email,
                                                                    @Field("service_taker_numbre") String service_taker_numbre,
                                                                    @Field("service_taker_address") String service_taker_address,
                                                                    @Field("service_taker_location_longitude") String service_taker_location_longitude,
                                                                    @Field("service_taker_location_latitude") String service_taker_location_latitude,
                                                                    @Field("service_taker_profile_image") String service_taker_profile_image,
                                                                    @Field("service_day") String service_day,
                                                                    @Field("service_time") String service_time,
                                                                    @Field("service_address") String service_address);



    @Headers({"Accept: application/json"})
    @GET("interest/show_interest/{id}")
    Call<ServiceProviderJobOfferResponce> serviceProviderJobOfferResponce(@Path( "id" ) String id);

//    @Headers({"Accept: application/json"})
//    @GET("interest/show_service_provider_pending_job_offer/{id}")
//    Call<ServiceProviderPendingJobOfferResponce> serviceProviderPendingJobOfferResponce(@Path( "id" ) String id);

    @Headers({"Accept: application/json"})
    @GET("interest/show_service_Taker_pending_job_offer/{id}")
    Call<ShowServiceTakerPendingJobOfferResmpoce> showServiceTakerPendingJobOfferResmpoce(@Path( "id" ) String id);

    @FormUrlEncoded
    @POST("interest/change_my_job_panding_status/{id}")
    Call<ServiceProviderPendingJobOfferDeletResponce> serviceProviderPendingJobOfferDeletResponce(@Path("id") String id, @Field( "status" ) int status);


    @FormUrlEncoded
    @POST("confirm/add_job_confirm")
    Call<ServiceConfirmResponce> serviceConfirmResponce(@Field( "provider_id" ) String provider_id,
                                                        @Field( "provider_profile_image" ) String provider_profile_image,
                                                        @Field( "provider_name" ) String provider_name,
                                                        @Field( "provider_longitude" ) String provider_longitude,
                                                        @Field( "provider_latitude" ) String provider_latitude,
                                                        @Field( "provider_mobile" ) String provider_mobile,

                                                        @Field( "service_taker_id" ) String service_taker_id,
                                                        @Field( "service_taker_name" ) String service_taker_name,
                                                        @Field( "service_taker_email" ) String service_taker_email,
                                                        @Field( "service_taker_numbre" ) String service_taker_numbre,
                                                        @Field( "service_taker_address" ) String service_taker_address,
                                                        @Field( "service_taker_location_longitude" ) String service_taker_location_longitude,
                                                        @Field( "service_taker_location_latitude" ) String service_taker_location_latitude,
                                                        @Field( "service_taker_profile_image" ) String service_taker_profile_image,

                                                        @Field( "service_day" ) String service_day,
                                                        @Field( "service_time" ) String service_time,
                                                        @Field( "service_address" ) String service_address);





    @Headers({"Accept: application/json"})
    @GET("confirm/show_provider_job_confirm/{id}")
    Call<ProviderConfirmJobListResponce> providerConfirmJobListResponce(@Path( "id" ) String id);

    @FormUrlEncoded
    @POST("confirm/provider_arrived_status/{id}")
    Call<ServiceProviderArriveStatusChangeResmpoce> serviceProviderArriveStatusChangeResmpoce(@Path( "id" ) int id,
                                                                                              @Field("provider_arrived_status") int provider_arrived_status);


    @Headers({"Accept: application/json"})
    @GET("confirm/show_user_job_confirm/{id}")
    Call<ServiceProviderConfirmJobResmpoce> serviceProviderConfirmJobResmpoce(@Path( "id" ) String id);


    @Headers({"Accept: application/json"})
    @GET("confirm/show_taker_job_probider_arrived_confirm/{id}")
    Call<ShowTakerJobProbiderArrivedConfirmResmpoce> showTakerJobProbiderArrivedConfirmResmpoce(@Path( "id" ) String id);



    @FormUrlEncoded
    @POST("confirm/job_complete_status/{id}")
    Call<JobCompleteStatusResmpoce> jobCompleteStatusResmpoce(@Path( "id" ) int id,
                                                              @Field("status") int status);

    @FormUrlEncoded
    @POST("history/add_job_history")
    Call<AddJobHistoryRespoce> addJobHistoryRespoce(@Field("provider_id") String provider_id,
                                                    @Field("provider_profile_image") String provider_profile_image,
                                                    @Field("provider_name") String provider_name,
                                                    @Field("provider_mobile") String provider_mobile,
                                                    @Field("service_taker_id") String service_taker_id,
                                                    @Field("service_taker_name") String service_taker_name,
                                                    @Field("service_taker_email") String service_taker_email,
                                                    @Field("service_taker_numbre") String service_taker_numbre,
                                                    @Field("service_taker_address") String service_taker_address,
                                                    @Field("service_taker_profile_image") String service_taker_profile_image,
                                                    @Field("service_day") String service_day,
                                                    @Field("service_time") String service_time,
                                                    @Field("service_address") String service_address,
                                                    @Field("time") String time);


    @Headers({"Accept: application/json"})
    @GET("history/show_service_taker_job_history/{id}")
    Call<ShowServiceTakerJobHistoryResponce> showServiceTakerJobHistoryResponce(@Path( "id" ) String id);


    @Headers({"Accept: application/json"})
    @GET("history/show_service_provider_job_history/{id}")
    Call<ShowServiceProviderJobHistoryResponce> showServiceProviderJobHistoryResponce(@Path( "id" ) String id);


    @Headers({"Accept: application/json"})
    @GET("notification/show_notification")
    Call<NotificationResponce> notificationData();

    @Headers({"Accept: application/json"})
    @GET("service_provider/show_info/{id}")
    Call<InvoiceResponce> invoiceResponce(@Path("id") String id);


}
