package br.com.cvaccari.moneytransfer.extensions

import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.EditText
import android.widget.TextView
import br.com.cvaccari.moneytransfer.utils.SimpleTextWatcher
import java.text.NumberFormat
import java.util.*

fun TextView.textToString(): String {
    return this.text.toString()
}

fun TextView.handleVisibility() {
    if (this.text.toString().isNullOrEmpty()) {
        this.visibility = View.GONE
    } else {
        this.visibility = View.VISIBLE
    }
}

fun EditText.setCurrencyMask() {
    this.setText("R$ 0,00")
    this.addTextChangedListener(object : SimpleTextWatcher() {
        override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
            super.onTextChanged(s, p1, p2, p3)
            val typed = onlyDigits(s.toString())
            if (!TextUtils.isEmpty(typed)) {
                removeTextChangedListener(this)
                val converted = java.lang.Double.parseDouble(typed) / 100
                var convertedString = NumberFormat
                    .getCurrencyInstance(Locale("pt", "BR"))
                    .format(converted)
                setText(convertedString)
                setSelection(convertedString.length)
                addTextChangedListener(this)
            }
        }
    })
}

fun TextView.setCurrencyMask() {
    this.text = "R$ 0,00"
    this.addTextChangedListener(object : SimpleTextWatcher() {
        override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
            super.onTextChanged(s, p1, p2, p3)
            val typed = onlyDigits(s.toString())
            if (!TextUtils.isEmpty(typed)) {
                removeTextChangedListener(this)
                val converted = java.lang.Double.parseDouble(typed) / 100
                val convertedString = NumberFormat
                    .getCurrencyInstance(Locale("pt", "BR"))
                    .format(converted)
                text = convertedString
                addTextChangedListener(this)
            }
        }
    })
}

fun TextView.formatToCurrency() {
    text = NumberFormat
        .getCurrencyInstance(Locale("pt", "BR"))
        .format(java.lang.Double.parseDouble(onlyDigits(textToString())) / 100)
}

fun TextView.onlyNumbersToDouble(): Double {
    return onlyDigits(this.textToString()).toDouble()
}

private fun onlyDigits(textValue: String): String {
    return textValue.replace("[^\\d]".toRegex(), "")
}