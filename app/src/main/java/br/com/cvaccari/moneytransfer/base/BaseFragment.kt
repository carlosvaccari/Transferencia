package br.com.cvaccari.moneytransfer.base

import android.os.Handler
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import br.com.cvaccari.moneytransfer.flowmanager.FlowManager

abstract class BaseFragment : Fragment() {

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {

        var animation = super.onCreateAnimation(transit, enter, nextAnim);

        if (animation == null && nextAnim != 0) {
            animation = AnimationUtils.loadAnimation(activity, nextAnim)
        }

        if (animation != null) {
            view?.setLayerType(View.LAYER_TYPE_HARDWARE, null)

            animation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(p0: Animation?) {
                }

                override fun onAnimationStart(p0: Animation?) {
                }

                override fun onAnimationEnd(p0: Animation?) {
                    view?.setLayerType(View.LAYER_TYPE_NONE, null)
                    //APENAS PARA DAR SENSAÇÂO DE CONEXÃO COM INTERNET
                    Handler().postDelayed(Runnable {
                        startFragment()
                    }, 2000)
                }
            })
        }

        return animation
    }

    abstract fun startFragment()

    fun currentFragment() : Fragment {
        fragmentManager?.apply {
            return fragments[0]
        }

        return Fragment()
    }

}