package br.com.cvaccari.moneytransfer.extensions

import android.text.TextUtils
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

fun View.expand() {
    var matchParentMeasureSpec =
        View.MeasureSpec.makeMeasureSpec((getParent() as View).getWidth(), View.MeasureSpec.EXACTLY);
    var wrapContentMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
    measure(matchParentMeasureSpec, wrapContentMeasureSpec);
    var targetHeight = getMeasuredHeight();

    // Older versions of android (pre API 21) cancel animations for views with a height of 0.
    getLayoutParams().height = 1;
    setVisibility(View.VISIBLE);

    var animation = object: Animation() {
        override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
            layoutParams.height = if (interpolatedTime.equals(1)) {
                ViewGroup.LayoutParams.WRAP_CONTENT
            } else {
                (targetHeight * interpolatedTime).toInt()
            }
            requestLayout()
        }

        override fun willChangeBounds(): Boolean {
            return super.willChangeBounds()
        }
    }

    // Expansion speed of 1dp/ms
    animation.duration = (targetHeight / getContext().getResources().getDisplayMetrics().density).toLong();
    startAnimation(animation);
}