package br.com.cvaccari.moneytransfer.sendmoney

import br.com.cvaccari.moneytransfer.data.remote.DataFacade
import br.com.cvaccari.moneytransfer.data.remote.vo.SendMoneyRequestVO
import br.com.cvaccari.moneytransfer.utils.SharedPrefsStorage
import io.reactivex.disposables.Disposable


class SendMoneyPresenter(
    val dataFacade: DataFacade,
    val sharedPrefsStorage: SharedPrefsStorage
) : SendMoneyContract.Presenter {

    private var mView: SendMoneyContract.View? = null

    private var mSubscribe: Disposable? = null

    override fun getUserContacts() {
        mSubscribe = dataFacade.getUserContacts(sharedPrefsStorage.getUserToken())
            .doOnComplete { mView?.showLoading(false) }
            .subscribe({
                mView?.showUserContacts(it.contactsList)
            }, {
                mView?.showError(it.message)
            })
    }

    override fun sendMoney(sendMoneyRequest: SendMoneyRequestVO) {
        mSubscribe = dataFacade.sendMoney(sendMoneyRequest)
            .doOnSubscribe {
                mView?.showOperationInitialized()
            }
            .subscribe({
                mView?.showSucess()
            }, {
                mView?.showError(it.message)
            })
    }

    override fun attachView(view: SendMoneyContract.View) {
        mView = view
    }

    override fun detachView() {
        mSubscribe?.dispose()
        mView = null
    }
}