package confortunlimitedoperators.com.mx.services

import confortunlimitedoperators.com.mx.models.Driver
import confortunlimitedoperators.com.mx.models.Moderator
import confortunlimitedoperators.com.mx.models.ResponseQRValidate
import confortunlimitedoperators.com.mx.models.Vendor
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface APIConfortU {

    @POST("Login.php?loginVendor=true")
    fun loginVendor(@Body vendor: Vendor): Call<Vendor>

    @POST("Login.php?loginDriver=true")
    fun loginDriver(@Body driver: Driver): Call<Driver>

    @POST("Login.php?loginModerator=true")
    fun loginModerator(@Body moderator: Moderator): Call<Moderator>

    @GET("QRValidate.php")
    fun qrValidate(@Query("folio") folio: String): Call<ResponseQRValidate>

}