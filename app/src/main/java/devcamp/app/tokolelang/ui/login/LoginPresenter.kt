package devcamp.app.tokolelang.ui.login

import devcamp.app.tokolelang.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by isfaaghyth on 8/30/18.
 * github: @isfaaghyth
 */
class LoginPresenter(view: LoginView): BasePresenter<LoginView>() {

    init { super.attachView(view) }

    fun doLogin(email: String, password: String) {
        view().showLoading()
        subscribe(routes.postUserLogin(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { res ->
                            run {
                                if (res.code == 200) {
                                    view().onLoginSuccess(res)
                                    view().hideLoading()
                                } else {
                                    requestError(res.code, res.message)
                                }
                            }
                        },
                        { err -> requestError(err) }
                ))
    }

}