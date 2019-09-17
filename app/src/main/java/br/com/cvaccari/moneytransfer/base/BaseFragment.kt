package br.com.cvaccari.moneytransfer.base

import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import br.com.cvaccari.moneytransfer.R

abstract class BaseFragment : Fragment() {

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {

        var animation = super.onCreateAnimation(transit, enter, nextAnim)

        if (animation == null && nextAnim != 0) {
            animation = AnimationUtils.loadAnimation(activity, nextAnim)
        }

        animation?.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(p0: Animation?) {
            }

            override fun onAnimationStart(p0: Animation?) {
            }

            override fun onAnimationEnd(p0: Animation?) {
                if(currentFragment() == getFragmentFromStack()){
                    (getFragmentFromStack() as BaseFragment).startFragment()
                }
            }
        })

        return animation
    }

    abstract fun startFragment()

    abstract fun currentFragment() : Fragment

    fun getFragmentFromStack(): Fragment {
        return fragmentManager?.findFragmentById(R.id.fragment_content) ?: Fragment()
    }

}