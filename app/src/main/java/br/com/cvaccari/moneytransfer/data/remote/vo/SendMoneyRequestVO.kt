package br.com.cvaccari.moneytransfer.data.remote.vo

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class SendMoneyRequestVO(
    @SerializedName("clientID") val clientID: String,
    @SerializedName("token") val token: String,
    @SerializedName("value") val value: Double
) : Serializable