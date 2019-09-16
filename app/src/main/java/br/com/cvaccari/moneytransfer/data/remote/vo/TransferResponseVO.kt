package br.com.cvaccari.moneytransfer.data.remote.vo

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class TransferResponseVO(@SerializedName("transferList") val transferList: List<TransferItemVO>) : Serializable