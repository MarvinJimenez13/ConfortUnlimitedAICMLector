package confortunlimitedoperators.com.mx.presenters

import confortunlimitedoperators.com.mx.interactors.QRLectorInteractorImpl
import confortunlimitedoperators.com.mx.interfaces.qrlector.QRLectorInteractor
import confortunlimitedoperators.com.mx.interfaces.qrlector.QRLectorPresenter
import confortunlimitedoperators.com.mx.interfaces.qrlector.QRLectorView

class QRLectorPresenterImpl(val qrLectorView: QRLectorView): QRLectorPresenter {

    val qrLectorInteractor: QRLectorInteractor = QRLectorInteractorImpl(this)

    override fun showProgress() {
        if(qrLectorView != null)
            qrLectorView.showProgress()
    }

    override fun hideProgress() {
        if(qrLectorView != null)
            qrLectorView.hideProgress()
    }

    override fun qrValidate(content: String, user: String) {
        qrLectorInteractor.qrValidate(content, user)
    }

    override fun toWeb(url: String, folio: String) {
        if(qrLectorView != null)
            qrLectorView.toWeb(url, folio)
    }

    override fun showError(message: String) {
        if(qrLectorView != null)
            qrLectorView.showError(message)
    }

}