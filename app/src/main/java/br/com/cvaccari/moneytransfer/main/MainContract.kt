package br.com.cvaccari.moneytransfer.main

import br.com.cvaccari.moneytransfer.base.BasePresenter

interface MainContract {

    interface View {
        fun showError(message: String?)
    }

    interface Presenter : BasePresenter<View> {
        fun getToken(name: String, email: String)
    }
}