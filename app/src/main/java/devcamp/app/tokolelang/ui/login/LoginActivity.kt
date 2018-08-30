package devcamp.app.tokolelang.ui.login

import devcamp.app.tokolelang.R
import devcamp.app.tokolelang.base.BaseActivity

/**
 * Created by isfaaghyth on 8/30/18.
 * github: @isfaaghyth
 */
class LoginActivity: BaseActivity<LoginPresenter>(), LoginView {

    override fun initPresenter(): LoginPresenter = LoginPresenter(this)
    override fun contentView(): Int = R.layout.activity_login

    override fun onCreated() {



    }


}