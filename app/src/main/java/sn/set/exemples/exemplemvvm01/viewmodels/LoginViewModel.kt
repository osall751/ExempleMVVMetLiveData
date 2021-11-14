package sn.set.exemples.exemplemvvm01.viewmodels

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import sn.set.exemples.exemplemvvm01.models.LoginUser


class LoginViewModel : ViewModel() {
    private val _loginUser = MutableLiveData<LoginUser>()
    val loginUser: MutableLiveData<LoginUser>
        get() = _loginUser

    private val _email = MutableLiveData<String>("Osall@univ-thies.sn")
    val email: MutableLiveData<String>
        get() = _email

    private val _password = MutableLiveData<String>("Test")
    val password: MutableLiveData<String>
        get() = _password

    fun login(v: View) {
        val user = LoginUser(email.value, password.value)
        loginUser.value = user
    }
}