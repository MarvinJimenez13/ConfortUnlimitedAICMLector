package confortunlimitedoperators.com.mx.interfaces.qrlector

interface QRLectorPresenter {

    fun showProgress()

    fun hideProgress()

    fun qrValidate(content: String, user: String)

    fun toWeb(url: String, folio: String)

    fun showError(message: String)

}