package br.com.cvaccari.moneytransfer.listeners

import br.com.cvaccari.moneytransfer.data.remote.vo.SendMoneyRequestVO

interface OnConfirmedListener {

    fun onConfirmedClickListener(sendMoneyRequest: SendMoneyRequestVO)

}