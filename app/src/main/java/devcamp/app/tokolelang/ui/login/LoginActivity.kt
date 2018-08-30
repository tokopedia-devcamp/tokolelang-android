package devcamp.app.tokolelang.ui.login

import android.content.Intent
import com.github.rahatarmanahmed.cpv.CircularProgressView
import devcamp.app.tokolelang.R
import devcamp.app.tokolelang.base.BaseActivity
import devcamp.app.tokolelang.data.model.UserRepository
import devcamp.app.tokolelang.ui.main.MainActivity
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
    }

    private fun onLoginClicked() = btnLogin.setOnClickListener{
        val email = edtEmail.text.toString()
        val password = edtPassword.text.toString()
        if (!email.isEmpty()) {
            txtEmail.error = null
            txtEmail.isErrorEnabled = false
            if (!password.isEmpty()) {
                txtPassword.error = null
                txtPassword.isErrorEnabled = false
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

    override fun onLoginSuccess(result: UserRepository) {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}