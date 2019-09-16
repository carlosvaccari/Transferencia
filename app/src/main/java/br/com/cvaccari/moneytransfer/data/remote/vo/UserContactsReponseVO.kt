package br.com.cvaccari.moneytransfer.data.remote.vo

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class UserContactsReponseVO(@SerializedName("contacts") val contactsList: List<ContactVO>) : Serializable