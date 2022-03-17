package confortunlimitedoperators.com.mx.interfaces.qrlector

interface QRLectorView {

    fun showProgress()

    fun hideProgress()

    fun toWeb(url: String, folio: String)

    fun showError(message: String)

}