package confortunlimitedoperators.com.mx.interactors

import android.util.Log
import confortunlimitedoperators.com.mx.interfaces.login.LoginInteractor
import confortunlimitedoperators.com.mx.interfaces.login.LoginPresenter
import confortunlimitedoperators.com.mx.models.Driver
import confortunlimitedoperators.com.mx.models.Moderator
import confortunlimitedoperators.com.mx.models.Vendor
import confortunlimitedoperators.com.mx.services.WebServiceAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginInteractorImpl(val loginPresenter: LoginPresenter): LoginInteractor {

    override fun loginVendor(vendor: Vendor) {
        loginPresenter.showProgress()
        CoroutineScope(Dispatchers.IO).launch {
            val call = WebServiceAPI.createService.loginVendor(vendor)
            call.enqueue(object: Callback<Vendor> {
                override fun onResponse(call: Call<Vendor>, response: Response<Vendor>) {
                    loginPresenter.hideProgress()
                    if(response.code() == 200){
                        if(response?.body() != null){
                            Log.d("TAG", response.body()!!.email)
                            loginPresenter.toQRLector()
                        }
                    }else if(response.code() == 204)
                        loginPresenter.showError("Revisa las credenciales ingresadas.")
                    else if(response.code() == 500)
                        loginPresenter.showError("Ocurrio un error, intente de nuevo mas tarde.")
                }

                override fun onFailure(call: Call<Vendor>, t: Throwable) {
                    loginPresenter.hideProgress()
                    loginPresenter.showError("Ocurrio un error, intente de nuevo mas tarde.")
                }

            })
        }
    }

    override fun loginModerator(moderator: Moderator) {
        loginPresenter.showProgress()
        CoroutineScope(Dispatchers.IO).launch {
            val call = WebServiceAPI.createService.loginModerator(moderator)
            call.enqueue(object: Callback<Moderator>{
                override fun onResponse(call: Call<Moderator>, response: Response<Moderator>) {
                    loginPresenter.hideProgress()
                    if(response.code() == 200){
                        if(response?.body() != null){
                            Log.d("TAG", response.body()!!.email)
                            loginPresenter.toQRLector()
                        }
                    }else if(response.code() == 204)
                        loginPresenter.showError("Revisa las credenciales ingresadas.")
                    else if(response.code() == 500)
                        loginPresenter.showError("Ocurrio un error, intente de nuevo mas tarde.")
                }

                override fun onFailure(call: Call<Moderator>, t: Throwable) {
                    loginPresenter.hideProgress()
                    loginPresenter.showError("Ocurrio un error, intente de nuevo mas tarde.")
                }
            })
        }
    }

    override fun loginDriver(driver: Driver) {
        loginPresenter.showProgress()
        CoroutineScope(Dispatchers.IO).launch {
            val call = WebServiceAPI.createService.loginDriver(driver)
            call.enqueue(object: Callback<Driver>{
                override fun onResponse(call: Call<Driver>, response: Response<Driver>) {
                    loginPresenter.hideProgress()
                    if(response.code() == 200){
                        if(response?.body() != null){
                            Log.d("TAG", response.body()!!.email)
                            loginPresenter.toQRLector()
                        }
                    }else if(response.code() == 204)
                        loginPresenter.showError("Revisa las credenciales ingresadas.")
                    else if(response.code() == 500)
                        loginPresenter.showError("Ocurrio un error, intente de nuevo mas tarde.")
                }

                override fun onFailure(call: Call<Driver>, t: Throwable) {
                    loginPresenter.hideProgress()
                    loginPresenter.showError("Ocurrio un error, intente de nuevo mas tarde.")
                }
            })
        }
    }

}