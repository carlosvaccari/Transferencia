package br.com.cvaccari.moneytransfer.moneytransference

import br.com.cvaccari.moneytransfer.data.remote.DataFacade
import br.com.cvaccari.moneytransfer.data.remote.vo.SendMoneyRequestVO
import br.com.cvaccari.moneytransfer.utils.SharedPrefsStorage
import io.reactivex.disposables.Disposable


class MoneyTransferencePresenter(
    val dataFacade: DataFacade,
    val sharedPrefsStorage: SharedPrefsStorage
) : MoneyTransferenceContract.Presenter {

    private var mView: MoneyTransferenceContract.View? = null

    private var mSubscribe: Disposable? = null

    override fun getUserContacts() {
        mSubscribe = dataFacade.getUserContacts(sharedPrefsStorage.getUserToken())
            .doOnComplete { mView?.showLoading(false) }
            .subscribe({
                mView?.showUserContacts(it.contactsList)
            }, {
                mView?.showError(it.message ?: "Erro desconhecido")
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
                mView?.showError(it.message ?: "Erro desconhecido")
            })
    }

    override fun attachView(view: MoneyTransferenceContract.View) {
        mView = view
    }

    override fun detachView() {
        mSubscribe?.dispose()
        mView = null
    }
}