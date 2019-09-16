package br.com.cvaccari.moneytransfer.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class SharedPrefsStorage(val context: Context) {

    companion object {
        const val USER_TOKEN = "FAVORITES"
    }

    lateinit var mSharedPreferences: SharedPreferences
    lateinit var mEditor: SharedPreferences.Editor

    fun saveUserToken(userToken: String) {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        mEditor = mSharedPreferences.edit()
        mEditor.clear()
        mEditor.putString(USER_TOKEN, userToken)
        mEditor.commit()
    }

    fun getUserToken(): String {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        return mSharedPreferences.getString(USER_TOKEN, "")
    }

}