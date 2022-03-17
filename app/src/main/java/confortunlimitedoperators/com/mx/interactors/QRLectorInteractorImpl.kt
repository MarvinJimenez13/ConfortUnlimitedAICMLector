package confortunlimitedoperators.com.mx.interactors

import android.util.Log
import confortunlimitedoperators.com.mx.interfaces.qrlector.QRLectorInteractor
import confortunlimitedoperators.com.mx.interfaces.qrlector.QRLectorPresenter
import confortunlimitedoperators.com.mx.models.ResponseQRValidate
import confortunlimitedoperators.com.mx.services.WebServiceAPI
import confortunlimitedoperators.com.mx.utlis.SharedPreferencesConfortU
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QRLectorInteractorImpl(val qrLectorPresenter: QRLectorPresenter): QRLectorInteractor {

    var url: String? = null

    override fun qrValidate(content: String, user: String) {
        qrLectorPresenter.showProgress()
        val call = WebServiceAPI.createService.qrValidate(content)
        call.enqueue(object: Callback<ResponseQRValidate>{
            override fun onResponse(
                call: Call<ResponseQRValidate>,
                response: Response<ResponseQRValidate>
            ) {
                qrLectorPresenter.hideProgress()
                if(response.code() == 200){
                    Log.d("TAG1", "Entro")
                    if(response.body()!!.isValid){
                        if(user.equals(SharedPreferencesConfortU.USER_PAY))
                            url = "http://192.168.100.36/TaxiConfortUnlimited/pay/confirm?folio=$content"
                        else if(user.equals(SharedPreferencesConfortU.USER_MODERATOR))
                            url = "http://192.168.100.36/TaxiConfortUnlimited/mod/match?folio=$content"
                        else if(user.equals(SharedPreferencesConfortU.USER_DRIVER))
                            url = "http://192.168.100.36/TaxiConfortUnlimited/driver/travel?folio=$content"

                        qrLectorPresenter.toWeb(url!!, content)
                    }else
                        qrLectorPresenter.showError("El folio es incorrecto o no esta registrado")
                }else
                    qrLectorPresenter.showError("Ocurrio un error, intente de nuevo mas tarde.")
            }

            override fun onFailure(call: Call<ResponseQRValidate>, t: Throwable) {
                Log.d("TAG1", "Error:" + t.message.toString())
                qrLectorPresenter.hideProgress()
            }

        })
    }

}