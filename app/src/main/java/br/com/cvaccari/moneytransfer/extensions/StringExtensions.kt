package br.com.cvaccari.moneytransfer.extensions

import br.com.cvaccari.moneytransfer.utils.NumberMask

fun String.phoneNumberFormat(): String {
    return NumberMask.addMask(this)
}