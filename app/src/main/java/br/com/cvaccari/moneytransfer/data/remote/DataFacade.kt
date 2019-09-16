package br.com.cvaccari.moneytransfer.data.remote

import br.com.cvaccari.moneytransfer.base.BaseFacade
import br.com.cvaccari.moneytransfer.data.remote.vo.*
import io.reactivex.Observable

class DataFacade : BaseFacade(){

    fun generateToken(name: String, email: String): Observable<TokenResponseVO> {
        return callOnMainThread(RetrofitClient.getClient().generateToken(name, email))
    }

    fun sendMoney(request: SendMoneyRequestVO): Observable<SendMoneyResponseVO> {
        return callOnMainThread(RetrofitClient.getClient().sendMoney(request))
    }

    fun getTransfers(request: TransferRequestVO): Observable<TransferResponseVO> {
        return callOnMainThread(RetrofitClient.getClient().getTransfers(request.token))
    }

    fun getUserContacts(userToken: String): Observable<UserContactsReponseVO> {
        return callOnMainThread(RetrofitClient.getClient().getUserContacts(userToken))

    }

}