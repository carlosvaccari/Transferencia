package br.com.cvaccari.moneytransfer.data.remote.vo

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class TokenResponseVO(@SerializedName("token") val token: String) : Serializable