package com.musalasoft.weatherapp.api

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.*

interface ApiService {


//    @POST("ca/login")
//    fun loginUser(@Header("Content-Type") contentType : String,
//                  @Body loginPost: LoginBody): Observable<LoginResponse>
//
//    @GET("logout")
//    fun logout(@Header("Authorization") token: String): Observable<ResponseBody>
//
//    @PUT("changeemail")
//    fun changeEmail(@Header("Authorization") token: String,
//                    @Header("Content-Type") contentType : String,
//                    @Body changeEmailBody: ChangeEmailBody): Observable<ChangeEmailResponse>
//
//
//
//    @PUT("changepassword")
//    fun changePassword(@Header("Authorization") token: String,
//                       @Header("Content-Type") contentType : String,
//                       @Body changePassword: ChangePasswordBody): Observable<ChangePasswordResponse>
//
//
//    @PUT("changePhoneNumber")
//    fun changePhoneNumber(@Header("Authorization") token: String,
//                          @Header("Content-Type") contentType : String,
//                          @Body changePhoneNumberBody: ChangePhoneNumberBody): Observable<ChangePhoneNumberResponse>
//
//
//    @GET("countries")
//    fun getCountriesPrefixes(@Header("Content-Type") contentType : String,
//                             @Header("Authorization") token: String): Observable<List<NumberPrefixesResponse>>
//
//    @GET("ca/customer")
//    fun getCustomer(@Header("Authorization") token: String): Observable<CustomerResponse>
//
//
//    @GET("privacypolicy")
//    fun getPrivacyPolicy(@Header("Authorization") token: String, @Query("lang") lang: String): Observable<List<TermsResponse>>
//
//
//
//    @GET("termsconditions")
//    fun getTermsAndCondition(@Header("Authorization") token: String, @Query("lang") lang: String): Observable<List<TermsResponse>>
//
//
//    @POST("ca/feedback")
//    fun sendFeedback(@Header("Authorization") token : String,
//                     @Body feedbackBody: FeedbackBody): Observable<ResponseBody>
//
//
//
//    @GET("ca/customer-profile")
//    fun getCustomerProfile(@Header("Content-Type") contentType : String,
//                           @Header("Authorization") token: String): Observable<CustomerProfileResponse>
//
//
//    @POST("ca/customer-profile")
//    fun updateCustomerProfile(@Header("Content-Type") contentType : String,
//                              @Header("Authorization") token: String,
//                              @Body customerProfileBody: CustomerProfileBody): Observable<CustomerProfileResponse>
//
//
//    @GET("ca/job-order-request")
//    fun getJobOrders(@Header("Authorization") token: String, @Query("view_date") viewDate: String): Observable<List<JobOrdersResponse>>
//
//
//
//    @GET("ca/container-types")
//    fun getContainerTypes(@Header("Authorization") token: String): Observable<List<ContainerTypeResponse>>
//
//
//
//    @POST("ca/work-order-request")
//    fun createWorkOrderRequest(@Header("Content-Type") contentType : String,
//                               @Header("Authorization") token: String,
//                               @Body workOrderRequestBody: CreateWorkOrderBody): Observable<CreateWorkOrderResponse>
//
//    @PUT("ca/work-order-request/{workOrderId}")
//    fun updateWorkOrderRequest(@Header("Authorization") token: String,
//                                @Header("Content-Type") contentType : String,
//                               @Path("workOrderId") workOrderId: Int,
//                               @Body workOrderRequestBody: CreateWorkOrderBody): Observable<CreateWorkOrderResponse>
//
//
//    @PUT("ca/job-order-request/material-pickup")
//    fun createPickupMaterial(@Header("Content-Type") contentType : String,
//                             @Header("Authorization") token: String,
//                             @Body createPickupMaterialBody: CreatePickupMaterialBody): Observable<ResponseBody>
//
//
//
//
//
//    @POST("ca/containers-count")
//    fun getContainersCount(@Header("Content-Type") contentType : String,
//                           @Header("Authorization") token: String,
//                           @Body getContainersCountBody: ContainersCountBody): Observable<ContainersCountResponse>
//
//
//
//    @POST("ca/containers-in-location")
//    fun getContainersInLocation(@Header("Content-Type") contentType : String,
//                           @Header("Authorization") token: String,
//                           @Body getContainersInLocationBody: ContainersInLocationBody): Observable<ContainersInLocationResponse>
//
//
//    @POST("ca/job-order-request/pull-container")
//    fun createPullContainer(@Header("Content-Type") contentType : String,
//                             @Header("Authorization") token: String,
//                             @Body createPullContainerBody: CreatePullContainerBody): Observable<ResponseBody>
//
//
//
//
//    @POST("ca/job-order-request/confirm")
//    fun confirmJobOrderRequest(@Header("Content-Type") contentType : String,
//                               @Header("Authorization") token: String,
//                               @Body confirmJobOrderRequestBody: ConfirmJobOrderRequestBody): Observable<ResponseBody>
//
//
//    @GET("ca/item-categories")
//    fun getItemCategories(@Header("Authorization") token: String): Observable<Categories>
//
//
//    @PUT("ca/job-order-request/item-pickup")
//    fun createItemPickup(@Header("Content-Type") contentType : String,
//                                @Header("Authorization") token: String,
//                                @Body createItemPickupBody: CreateItemPickupBody): Observable<ResponseBody>
//
//
//    @GET("ca/category/{id}/materials")
//    fun getActiveMaterials(@Header("Authorization") token: String,
//                           @Path("id") id: Int): Observable<List<ActiveMaterialsResponse>>
//
//
//    @GET("ca/notification")
//    fun getAllNotifications(@Header("Authorization") token: String): Observable<NotificationsResponse>
//
//
//    @PUT("ca/update-firebase-token")
//    fun updateFirebaseToken(@Header("Authorization") token: String,
//                            @Header("Content-Type") contentType : String,
//                            @Body updateFirebaseTokenBody: UpdateFirebaseTokenBody): Observable<ResponseBody>
//
//    @GET("ca/map/job-orders/{id}")
//    fun getOngoingJobOrderForMap(@Header("Authorization") token: String,
//                           @Path("id") id: Int): Observable<JobOrderLocationForMapResponse>
//
//
//    @PUT("ca/user/profile")
//    fun editUserAccount(@Header("Authorization") token: String,
//                            @Header("Content-Type") contentType : String,
//                            @Body editUserAccountBody: EditUserAccountBody): Observable<ResponseBody>
//
//
//    @GET("ca/user/profile")
//    fun getUserAccount(@Header("Authorization") token: String,
//                       @Header("Content-Type") contentType : String): Observable<UserAccountResponse>
//
//
//    @POST("ca/job-order-request")
//    fun createJobOrder(@Header("Content-Type") contentType : String,
//                               @Header("Authorization") token: String,
//                               @Body createJobOrderBody: CreateJobOrderBody): Observable<CreateJobOrderResponse>
//
//    @POST("ca/job-order-request/{joborderid}/material")
//    fun submitSelectedCategoryAndMaterials(@Header("Authorization") token: String,
//                                           @Header("Content-Type") contentType : String,
//                                           @Path("joborderid") joborderid: Int,
//                                           @Body submitSelectedCategoryAndMaterialsBody: SumbitSelectedCategoryAndMaterialsBody): Observable<CreateJobOrderResponse>
//
//    @POST("ca/job-order-request/{joborderid}/item")
//    fun createItem(@Header("Authorization") token: String,
//                   @Header("Content-Type") contentType : String,
//                   @Path("joborderid") joborderid: Int,
//                   @Body submitSelectedCategorySubCatAndItemsBody: SubmitSelectedCategorySubCatAndItemsBody): Observable<CreateItemPickupPopupResponse>
//
//    @PUT("ca/job-order-request/{joborderid}/item/{joborderitemid}")
//    fun updateItem(@Header("Authorization") token: String,
//                   @Header("Content-Type") contentType : String,
//                   @Path("joborderid") joborderid: Int,
//                   @Path("joborderitemid") joborderitemid: Int,
//                   @Body updateSelectedCategorySubCatAndItemsBody: SubmitSelectedCategorySubCatAndItemsBody): Observable<ResponseBody>
//
//    @GET("ca/map/job-orders")
//    fun getAllOngoingJobOrders(@Header("Authorization") token: String): Observable<List<AllOngoingJobOrdersResponse>>
//
//    @PUT("ca/sendResetPassword")
//    fun forgotPassword(@Body forgotPasswordBody: ForgotPasswordBody): Observable<ResponseBody>
//
//    @DELETE("ca/job-order-request/{id}")
//    fun deleteJobOrder(@Header("Authorization") token: String,
//                       @Header("Content-Type") contentType : String,
//                       @Path("id") joborderid: Int): Observable<ResponseBody>
//
//
//    @POST("ca/job-order-request/deliver-container")
//    fun createDeliverContainer(@Header("Content-Type") contentType : String,
//                            @Header("Authorization") token: String,
//                            @Body createDeliverContainerBody: CreateDeliverContainerBody): Observable<ResponseBody>



}