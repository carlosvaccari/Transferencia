package br.com.cvaccari.moneytransfer.sendmoney

import br.com.cvaccari.moneytransfer.base.BasePresenter
import br.com.cvaccari.moneytransfer.data.remote.vo.ContactVO
import br.com.cvaccari.moneytransfer.data.remote.vo.SendMoneyRequestVO

interface SendMoneyContract {

    interface View {
        fun showUserContacts(contactsList: List<ContactVO>)

        fun showError(errorMessage: String?)

        fun showOperationInitialized()

        fun showSucess()

        fun showLoading(show: Boolean)
    }

    interface Presenter : BasePresenter<View> {
        fun getUserContacts()

        fun sendMoney(sendMoneyRequest: SendMoneyRequestVO)
    }
}