package sn.set.exemples.exemplemvvm01.views

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import sn.set.exemples.exemplemvvm01.R
import sn.set.exemples.exemplemvvm01.api.RetrofitClient
import sn.set.exemples.exemplemvvm01.api.RetrofitService
import sn.set.exemples.exemplemvvm01.databinding.ActivityLoginBinding
import sn.set.exemples.exemplemvvm01.models.LoginUser
import sn.set.exemples.exemplemvvm01.viewmodels.LoginViewModel
import java.util.*

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewmodel: LoginViewModel by viewModels()

    private lateinit var loginService: RetrofitService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        initViews()
        loginService = RetrofitClient.getClient()
//        runBlocking {
//            getAllRendezVous()
//        }
    }

//    private suspend fun getAllRendezVous() {
//        supervisorScope {
//            launch {
//                val response = loginService.getAllRendezVous()
//                if (response.isSuccessful) {
//                    val rvsResponse = response.body()!!
//                    Log.i("### Osall ", rvsResponse.toString())
////                    txtView.text = rvsResponse.toString()
//                } else {
//                    Log.i("### Osall ", response.errorBody().toString())
//                }
//            }
//        }
//    }

    private fun initViews() {
        binding.loginViewModel = viewmodel
        binding.lifecycleOwner = this
        viewmodel.loginUser.observe(this,
            { loginUser ->
                if (TextUtils.isEmpty(Objects.requireNonNull(loginUser).identifier)) {
                    binding.editTextTextEmailAddress.error = "La saisie de l'adresse mail est obligatoire !!!"
                    binding.editTextTextEmailAddress.requestFocus()
                } else if (!Patterns.EMAIL_ADDRESS.matcher(loginUser.identifier).matches()) {
                    binding.editTextTextEmailAddress.error = "Saisir une adresse mail valide !!!"
                    binding.editTextTextEmailAddress.requestFocus()
                } else if (TextUtils.isEmpty(Objects.requireNonNull(loginUser).password)) {
                    binding.editTextTextPassword.error ="La saisie d'un mot de passe est obligatoire !!!"
                    binding.editTextTextPassword.requestFocus()
                } else {
                    Toast.makeText(this,"${loginUser.identifier} / ${loginUser.password}",Toast.LENGTH_LONG).show()
                    runBlocking {
                        signIn(loginUser.identifier!!, loginUser.password!!)
                    }
                }
            })
    }

    private suspend fun signIn(email: String, password: String) {
        supervisorScope {
            launch {
                val response = loginService.login(LoginUser(email, password))
                if (response.isSuccessful) {
                    val rvsResponse = response.body()!!
                    Log.i("### Osall ", rvsResponse.toString())
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    intent.putExtra("jwt",rvsResponse.jwt)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@LoginActivity, "Erreur Connexion ${response.errorBody().toString()}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}