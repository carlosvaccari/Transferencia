package br.com.cvaccari.moneytransfer.base

interface BasePresenter<T> {

    fun attachView(view: T)

    fun detachView()

}
