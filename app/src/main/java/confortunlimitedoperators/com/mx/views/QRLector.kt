package confortunlimitedoperators.com.mx.views

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import butterknife.ButterKnife
import butterknife.Unbinder
import com.google.zxing.integration.android.IntentIntegrator
import confortunlimitedoperators.com.mx.R
import confortunlimitedoperators.com.mx.interfaces.qrlector.QRLectorPresenter
import confortunlimitedoperators.com.mx.interfaces.qrlector.QRLectorView
import confortunlimitedoperators.com.mx.presenters.QRLectorPresenterImpl
import confortunlimitedoperators.com.mx.utlis.SharedPreferencesConfortU

class QRLector : AppCompatActivity(), QRLectorView {

    var mUnbinder: Unbinder? = null
    var progressBar: ProgressDialog? = null
    var qrLectorPresenter: QRLectorPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr)
        mUnbinder = ButterKnife.bind(this)

        qrLectorPresenter = QRLectorPresenterImpl(this)

        IntentIntegrator(this).initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null)
                Toast.makeText(this, "Cancelado", Toast.LENGTH_LONG).show()
            else //Validar que el QR este registrado
                qrLectorPresenter!!.qrValidate(result.contents, SharedPreferencesConfortU.getInstance(this).getUser())
        }else
            super.onActivityResult(requestCode, resultCode, data)
    }

    override fun showProgress() {
        progressBar = ProgressDialog.show(this, "Validando", "Espere...")
    }

    override fun hideProgress() {
        progressBar!!.dismiss()
    }

    override fun toWeb(url: String, folio: String) {
        Toast.makeText(this, "El folio escaneado es: " + folio, Toast.LENGTH_LONG).show()

        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        mUnbinder!!.unbind()
        if(progressBar != null){
            progressBar!!.dismiss()
            progressBar = null
        }
    }

}