package br.com.cvaccari.moneytransfer.main

import br.com.cvaccari.moneytransfer.base.BasePresenter
import br.com.cvaccari.moneytransfer.data.remote.DataFacade
import br.com.cvaccari.moneytransfer.utils.SharedPrefsStorage
import io.reactivex.disposables.Disposable

class MainPresenter(val dataFacade: DataFacade, val sharedPrefsStorage: SharedPrefsStorage) :
    BasePresenter<MainContract.View>, MainContract.Presenter {

    private var mView: MainContract.View? = null

    private var mSubscribe: Disposable? = null

    override fun getToken(name: String, email: String) {
        mSubscribe = dataFacade.generateToken(name, email)
            .subscribe({
                sharedPrefsStorage.saveUserToken(it.token)
            }, {
                mView?.showError(it.message)
            })
    }

    override fun attachView(view: MainContract.View) {
        mView = view
    }

    override fun detachView() {
        mSubscribe?.dispose()
        mView = null
    }
}