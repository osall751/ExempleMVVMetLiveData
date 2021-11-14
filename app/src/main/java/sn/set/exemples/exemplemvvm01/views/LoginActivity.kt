package sn.set.exemples.exemplemvvm01.views

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import sn.set.exemples.exemplemvvm01.R
import sn.set.exemples.exemplemvvm01.databinding.ActivityLoginBinding
import sn.set.exemples.exemplemvvm01.viewmodels.LoginViewModel
import java.util.*

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewmodel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        initViews()
    }

    private fun initViews() {
        binding.loginViewModel = viewmodel
        binding.lifecycleOwner = this
        viewmodel.loginUser.observe(this,
            { loginUser ->
                if (TextUtils.isEmpty(Objects.requireNonNull(loginUser).email)) {
                    binding.editTextTextEmailAddress.error = "La saisie de l'adresse mail est obligatoire !!!"
                    binding.editTextTextEmailAddress.requestFocus()
                } else if (!Patterns.EMAIL_ADDRESS.matcher(loginUser.email).matches()) {
                    binding.editTextTextEmailAddress.error = "Saisir une adresse mail valide !!!"
                    binding.editTextTextEmailAddress.requestFocus()
                } else if (TextUtils.isEmpty(Objects.requireNonNull(loginUser).password)) {
                    binding.editTextTextPassword.error = "La saisie d'un mot de passe est obligatoire !!!"
                    binding.editTextTextPassword.requestFocus()
                }else {
                    Toast.makeText(this, "${loginUser.email} / ${loginUser.password}", Toast.LENGTH_LONG).show()
                }
            })
    }
}