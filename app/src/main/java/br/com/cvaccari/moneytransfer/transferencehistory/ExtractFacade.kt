package br.com.cvaccari.moneytransfer.transferencehistory

import br.com.cvaccari.moneytransfer.base.BaseFacade
import br.com.cvaccari.moneytransfer.data.remote.vo.GraphicItemVO
import br.com.cvaccari.moneytransfer.data.remote.vo.TransferItemVO
import io.reactivex.Observable
import java.util.*
import kotlin.Comparator

class ExtractFacade : BaseFacade() {

    fun generateGraphicData(transferList: List<TransferItemVO>): Observable<List<GraphicItemVO>> {
        return callOnMainThread(Observable.create {

            val graphicItens = mutableListOf<GraphicItemVO>()

            transferList.forEach { transferItem ->
                val shouldAdd = graphicItens.none {
                    transferItem.clientID == it.id
                }

                if (shouldAdd) {
                    graphicItens.add(GraphicItemVO(transferItem.clientID, transferItem.amount, transferItem.photo, transferItem.name))
                } else {
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
            graphicItens.forEach {
                it.amountNormalized = (it.amount.toFloat() / maxValue)
            }

            it.onNext(graphicItens.toList())
            it.onComplete()
        })
    }
}