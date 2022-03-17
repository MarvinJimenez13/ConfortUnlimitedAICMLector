package confortunlimitedoperators.com.mx.presenters

import confortunlimitedoperators.com.mx.interactors.LoginInteractorImpl
import confortunlimitedoperators.com.mx.interfaces.login.LoginInteractor
import confortunlimitedoperators.com.mx.interfaces.login.LoginPresenter
import confortunlimitedoperators.com.mx.interfaces.login.LoginView
import confortunlimitedoperators.com.mx.models.Driver
import confortunlimitedoperators.com.mx.models.Moderator
import confortunlimitedoperators.com.mx.models.Vendor

class LoginPresenterImpl(val loginView: LoginView): LoginPresenter {

    private val loginInteractor: LoginInteractor = LoginInteractorImpl(this)

    override fun toQRLector() {
        if(loginView != null)
            loginView.toQRLector()
    }

    override fun loginVendor(vendor: Vendor) {
        loginInteractor.loginVendor(vendor)
    }

    override fun loginModerator(moderator: Moderator) {
        loginInteractor.loginModerator(moderator)
    }

    override fun loginDriver(driver: Driver) {
        loginInteractor.loginDriver(driver)
    }

    override fun showProgress() {
        if(loginView != null)
            loginView!!.showProgress()
    }

    override fun hideProgress() {
        if(loginView != null)
            loginView.hideProgress()
    }

    override fun showError(message: String) {
        if(loginView != null)
            loginView.showError(message)
    }

}