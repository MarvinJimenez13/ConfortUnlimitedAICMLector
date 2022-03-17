package confortunlimitedoperators.com.mx.views

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Unbinder
import com.google.android.material.textfield.TextInputEditText
import confortunlimitedoperators.com.mx.R
import confortunlimitedoperators.com.mx.interfaces.login.LoginPresenter
import confortunlimitedoperators.com.mx.interfaces.login.LoginView
import confortunlimitedoperators.com.mx.models.Driver
import confortunlimitedoperators.com.mx.models.Moderator
import confortunlimitedoperators.com.mx.models.Vendor
import confortunlimitedoperators.com.mx.presenters.LoginPresenterImpl
import confortunlimitedoperators.com.mx.utlis.SharedPreferencesConfortU

class LoginOperatorActivity : AppCompatActivity(), LoginView {

    var user: String? = null
    var mUnbinder: Unbinder? = null
    var loginPresenter: LoginPresenter? = null
    var progressDialog: ProgressDialog? = null

    @BindView(R.id.tietEmail)
    lateinit var etEmail: TextInputEditText
    @BindView(R.id.tietPassword)
    lateinit var etPassword: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mUnbinder = ButterKnife.bind(this)

        loginPresenter = LoginPresenterImpl(this)
        user = SharedPreferencesConfortU.getInstance(this).getUser()
    }

    @OnClick(R.id.btnIngresar)
    fun onClick(){
        if(!etEmail.text.toString().isEmpty() && !etPassword.text.toString().isEmpty()){
            if(user.equals(SharedPreferencesConfortU.USER_PAY)){
                val vendor = Vendor(
                    name = "",
                    last_name = "",
                    email = etEmail.text.toString(),
                    password = etPassword.text.toString()
                )
                loginPresenter!!.loginVendor(vendor)
            }else if(user.equals(SharedPreferencesConfortU.USER_DRIVER)){
                val driver = Driver(
                    name = "",
                    last_name = "",
                    plate_number = "",
                    email = etEmail.text.toString(),
                    password = etPassword.text.toString()
                )
                loginPresenter!!.loginDriver(driver)
            }else if(user.equals(SharedPreferencesConfortU.USER_MODERATOR)){
                val moderator = Moderator(
                    name = "",
                    last_name = "",
                    email = etEmail.text.toString(),
                    password = etPassword.text.toString()
                )
                loginPresenter!!.loginModerator(moderator)
            }
        }else
            Toast.makeText(this, "Ingresa tus creenciales", Toast.LENGTH_SHORT).show()
    }

    override fun toQRLector() {
        startActivity(Intent(this, QRLector::class.java))
    }

    override fun showProgress() {
        progressDialog = ProgressDialog.show(this, "Iniciando Sesion", "Espere...")
    }

    override fun hideProgress() {
        progressDialog!!.dismiss()
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        mUnbinder!!.unbind()
        if(progressDialog != null){
            progressDialog!!.dismiss()
            progressDialog = null
        }
    }

}