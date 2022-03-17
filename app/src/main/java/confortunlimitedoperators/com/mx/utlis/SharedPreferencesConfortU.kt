package confortunlimitedoperators.com.mx.utlis

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesConfortU(context: Context) {

    var prefs: SharedPreferences? = null
    val PREFS_NAME = "CONFORT_UNLIMITED"
    val SHARED_NAME = "USER"

    companion object{
        private var instance: SharedPreferencesConfortU? = null
        val USER_PAY = "PAY"
        val USER_MODERATOR = "MOD"
        val USER_DRIVER = "DRIVER"

        fun getInstance(context: Context): SharedPreferencesConfortU {
            if(instance == null)
                instance = SharedPreferencesConfortU(context)

            return instance as SharedPreferencesConfortU
        }
    }

    init {
        prefs = context.getSharedPreferences(PREFS_NAME, 0)
    }

    fun saveUser(user: String){
        prefs!!.edit().putString(SHARED_NAME, user).apply()
    }

    fun getUser(): String{
        val user: String? = prefs!!.getString(SHARED_NAME, "")
        return user!!
    }

}