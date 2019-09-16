package br.com.cvaccari.moneytransfer.listeners

import br.com.cvaccari.moneytransfer.data.remote.vo.ContactVO

interface OnClickListener {

    fun onItemClicked(contact: ContactVO)

}