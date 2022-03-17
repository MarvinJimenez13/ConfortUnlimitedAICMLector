package confortunlimitedoperators.com.mx.interfaces.login

import confortunlimitedoperators.com.mx.models.Driver
import confortunlimitedoperators.com.mx.models.Moderator
import confortunlimitedoperators.com.mx.models.Vendor

interface LoginInteractor {

    fun loginVendor(vendor: Vendor)

    fun loginModerator(moderator: Moderator)

    fun loginDriver(driver: Driver)

}