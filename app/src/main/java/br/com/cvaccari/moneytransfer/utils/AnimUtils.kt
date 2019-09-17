package br.com.cvaccari.moneytransfer.utils

import android.animation.ValueAnimator
import android.view.View
import android.view.ViewPropertyAnimator
import android.view.animation.AccelerateDecelerateInterpolator

object AnimUtils  {

    fun getScaleAnim(view: View, amountNormalized: Float, position: Int): ViewPropertyAnimator {
        return view.animate().scaleY(200f * amountNormalized)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .setDuration((2000 - (amountNormalized * 200) - (position * 100)).toLong())
    }

    fun getValueAnimator(endValue: Int): ValueAnimator {
        val anim = ValueAnimator.ofInt(0, endValue)
        anim.duration = 2000
        return anim
    }

    fun getTranslateAnim(view: View, amountNormalized: Float, position: Int): ViewPropertyAnimator {
        return view.animate().translationY(-300f * amountNormalized)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .setDuration((2000 - (amountNormalized * 200) - (position * 100)).toLong())
    }
}