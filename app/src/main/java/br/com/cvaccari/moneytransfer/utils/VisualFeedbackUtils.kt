package br.com.cvaccari.moneytransfer.utils

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.google.android.material.snackbar.Snackbar

object VisualFeedbackUtils {

    fun showSnackbar(message: String, view: View?) {
        view?.apply {
            Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()
        }
    }

    fun hideKeyboard(context: Activity?) {
        context?.currentFocus?.apply {
            val inputMethodManager = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
        }
    }
}