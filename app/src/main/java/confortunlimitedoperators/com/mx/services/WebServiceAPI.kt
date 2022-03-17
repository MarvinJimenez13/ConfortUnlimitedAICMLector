package confortunlimitedoperators.com.mx.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WebServiceAPI {

    private const val BASE_URL = "http://192.168.100.36/TaxiConfortUnlimited/services/"

    private fun WebServiceAPI(): Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    val createService: APIConfortU by lazy {
        WebServiceAPI().create(APIConfortU::class.java)
    }

}