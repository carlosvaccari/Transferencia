package br.com.cvaccari.moneytransfer.transferencehistory

import br.com.cvaccari.moneytransfer.base.BasePresenter
import br.com.cvaccari.moneytransfer.data.remote.vo.GraphicItemVO
import br.com.cvaccari.moneytransfer.data.remote.vo.TransferItemVO

interface ExtractContract {

    interface View {
        fun showTransferHistory(contactsList: List<TransferItemVO>)

        fun showError(message: String?)

        fun showGraphicData(graphicItens: List<GraphicItemVO>)

        fun showLoading(show: Boolean)
    }

    interface Presenter : BasePresenter<View> {
        fun getTransfers()
    }
}