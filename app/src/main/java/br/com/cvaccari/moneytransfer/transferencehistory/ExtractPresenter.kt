package br.com.cvaccari.moneytransfer.transferencehistory

import br.com.cvaccari.moneytransfer.data.remote.DataFacade
import br.com.cvaccari.moneytransfer.data.remote.vo.TransferItemVO
import br.com.cvaccari.moneytransfer.data.remote.vo.TransferRequestVO
import br.com.cvaccari.moneytransfer.utils.SharedPrefsStorage
import io.reactivex.disposables.Disposable

class ExtractPresenter(
    val dataFacade: DataFacade,
    val sharedPrefsStorage: SharedPrefsStorage,
    val extractFacade: ExtractFacade
) : ExtractContract.Presenter {

    private var mView: ExtractContract.View? = null

    private var mSubscribe: Disposable? = null

    override fun getTransfers() {
        mSubscribe = dataFacade.getTransfers(TransferRequestVO(sharedPrefsStorage.getUserToken()))
            .doOnComplete { mView?.showLoading(false) }
            .subscribe({
                generateGraphicData(it.transferList)
                mView?.showTransferHistory(it.transferList)
            }, {
                mView?.showError(it.message)
            })
    }

    private fun generateGraphicData(transferList: List<TransferItemVO>) {
        mSubscribe = extractFacade.generateGraphicData(transferList)
            .subscribe({
                mView?.showGraphicData(it)
            }, {
                mView?.showError(it.message)
            })
    }

    override fun attachView(view: ExtractContract.View) {
        mView = view
    }

    override fun detachView() {
        mView = null
        mSubscribe?.dispose()
    }
}