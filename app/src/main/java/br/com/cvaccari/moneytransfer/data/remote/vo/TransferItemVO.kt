package br.com.cvaccari.moneytransfer.data.remote.vo

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class TransferItemVO(
    @SerializedName("name") val name: String,
    @SerializedName("ClienteId") val clientID: Int,
    @SerializedName("phone") val phone: String,
    @SerializedName("amount") val amount: Int,
    @SerializedName("photo") val photo: String? = ""
) : Serializable