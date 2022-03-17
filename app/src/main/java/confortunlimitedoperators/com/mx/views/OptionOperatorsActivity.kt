package confortunlimitedoperators.com.mx.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Unbinder
import confortunlimitedoperators.com.mx.R
import confortunlimitedoperators.com.mx.utlis.SharedPreferencesConfortU

class OptionOperatorsActivity : AppCompatActivity() {

    var mUnbinder: Unbinder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mUnbinder = ButterKnife.bind(this)
    }

    @OnClick(R.id.btnSoyVendedor, R.id.btnSoyModerador, R.id.btnSoyConductor)
    fun onClick(view: View){
        when(view.id){
            R.id.btnSoyVendedor ->{
                SharedPreferencesConfortU.getInstance(this).saveUser(SharedPreferencesConfortU.USER_PAY)
                startActivity(Intent(this, LoginOperatorActivity::class.java))
            }
            R.id.btnSoyModerador ->{
                SharedPreferencesConfortU.getInstance(this).saveUser(SharedPreferencesConfortU.USER_MODERATOR)
                startActivity(Intent(this, LoginOperatorActivity::class.java))
            }
            R.id.btnSoyConductor ->{
                SharedPreferencesConfortU.getInstance(this).saveUser(SharedPreferencesConfortU.USER_DRIVER)
                startActivity(Intent(this, LoginOperatorActivity::class.java))
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mUnbinder!!.unbind()
    }

}