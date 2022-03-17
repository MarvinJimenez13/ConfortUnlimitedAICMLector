package confortunlimitedoperators.com.mx.interfaces.login

interface LoginView {

    fun toQRLector()

    fun showProgress()

    fun hideProgress()

    fun showError(message: String)

}