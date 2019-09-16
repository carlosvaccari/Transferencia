package br.com.cvaccari.moneytransfer.data.remote.vo

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ContactVO(@SerializedName("id") val id: Int,
                @SerializedName("name") val name: String,
                @SerializedName("phone")val phone: String,
                @SerializedName("photo") val photo: String? = "") : Serializable