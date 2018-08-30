package devcamp.app.tokolelang.ui.login

import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import com.github.rahatarmanahmed.cpv.CircularProgressView
import devcamp.app.tokolelang.R
import devcamp.app.tokolelang.base.BaseActivity
import devcamp.app.tokolelang.data.model.UserRepository
import devcamp.app.tokolelang.ui.main.MainActivity
import devcamp.app.tokolelang.utils.RakEntries
import io.isfaaghyth.rak.Rak
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.toolbar.view.*

/**
 * Created by isfaaghyth on 8/30/18.
 * github: @isfaaghyth
 */
class LoginActivity: BaseActivity<LoginPresenter>(), LoginView {

    override fun initPresenter(): LoginPresenter = LoginPresenter(this)
    override fun contentView(): Int = R.layout.activity_login
    override fun loader(): CircularProgressView = header.progressBar

    override fun onCreated() {
        onLoginClicked()
        errorWatcher()
        if (Rak.isExist("login")) {
            if (Rak.grab("login")) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }

    private fun onLoginClicked() = btnLogin.setOnClickListener{
        val email = edtEmail.text.toString()
        val password = edtPassword.text.toString()
        if (!email.isEmpty()) {
            if (!password.isEmpty()) {
                presenter.doLogin(email, password)
            } else {
                txtPassword.error = getString(R.string.password_empty)
                txtPassword.isErrorEnabled = true
            }
        } else {
            txtEmail.error = getString(R.string.email_empty)
            txtEmail.isErrorEnabled = true
        }
    }

    private fun errorWatcher() {
        edtEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                txtEmail.error = null
                txtEmail.isErrorEnabled = false
            }
        })
        edtPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                txtPassword.error = null
                txtPassword.isErrorEnabled = false
            }
        })
    }

    override fun onLoginSuccess(result: UserRepository) {
        Rak.entry("login", true)
        Rak.entry("userId", result.data.id)
        Rak.entry("name", result.data.name)
        Rak.entry("email", result.data.email)
        Rak.entry("avatar", result.data.avatar)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}