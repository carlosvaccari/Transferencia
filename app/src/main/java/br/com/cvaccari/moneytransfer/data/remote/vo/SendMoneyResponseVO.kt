package br.com.cvaccari.moneytransfer.data.remote.vo

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class SendMoneyResponseVO(@SerializedName("result") val sentMoney: Boolean) : Serializable