package br.com.cvaccari.moneytransfer.data.remote.vo

import java.io.Serializable

class GraphicItemVO(val id: Int,
                    var amount: Int,
                    val photo: String? = "",
                    val name: String,
                    var amountNormalized: Float = 0F) : Serializable