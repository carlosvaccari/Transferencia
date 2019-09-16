package br.com.cvaccari.moneytransfer.transferencehistory

import br.com.cvaccari.moneytransfer.data.remote.DataFacade
import br.com.cvaccari.moneytransfer.data.remote.vo.GraphicItemVO
import br.com.cvaccari.moneytransfer.data.remote.vo.TransferItemVO
import br.com.cvaccari.moneytransfer.data.remote.vo.TransferRequestVO
import br.com.cvaccari.moneytransfer.utils.SharedPrefsStorage
import io.reactivex.disposables.Disposable
import java.util.*
import kotlin.Comparator

class ExtractPresenter(val dataFacade: DataFacade,
                       val sharedPrefsStorage: SharedPrefsStorage) : ExtractContract.Presenter {

    private var mView: ExtractContract.View? = null

    private var mSubscribe: Disposable? = null

    override fun getTransfers() {
        dataFacade.getTransfers(TransferRequestVO(sharedPrefsStorage.getUserToken()))
            .doOnComplete { mView?.showLoading(false) }
            .subscribe({
                generateGraphicData(it.transferList)
                mView?.showTransferHistory(it.transferList)
            }, {
                mView?.showError(it.message)
            })
    }

    private fun generateGraphicData(transferList: List<TransferItemVO>) {
        val graphicItens = mutableListOf<GraphicItemVO>()
        transferList.forEach { transferItem ->
            val shouldAdd = graphicItens.none {
                transferItem.clientID == it.id
            }

            if (shouldAdd) {
                graphicItens.add(GraphicItemVO(transferItem.clientID, transferItem.amount, transferItem.photo, transferItem.name))
            } else {
                //TODO usar sort primeiro
                graphicItens.forEach {
                    if(it.id == transferItem.clientID) {
                        it.amount += transferItem.amount
                    }
                }
            }
        }

        Collections.sort(graphicItens, object : Comparator<GraphicItemVO>{
            override fun compare(item1: GraphicItemVO, item2: GraphicItemVO): Int {
                return Integer.compare(item2.amount, item1.amount)
            }
        })

        val maxValue = graphicItens[0].amount
//        (it.amount / 100) * 100 / maxValue
        graphicItens.forEach {
            it.amountNormalized = (it.amount.toFloat() / maxValue)
//            it.amountNormalized = (it.amount * 100 / maxValue).toFloat() / 100
        }

        mView?.showGraphicData(graphicItens)

    }

    override fun attachView(view: ExtractContract.View) {
        mView = view
    }

    override fun detachView() {
        mView = null
        mSubscribe?.dispose()
    }
}