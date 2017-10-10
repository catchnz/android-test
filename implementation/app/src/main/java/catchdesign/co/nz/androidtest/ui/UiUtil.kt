package catchdesign.co.nz.androidtest.ui

import android.animation.Animator
import android.view.View

class UiUtil {
    companion object {

        fun fadeOutView(view: View?) {
            view!!.animate().alpha(0f)
                    .setDuration(1000)
                    .setListener(object : SimpleAnimatorListener() {
                        override fun onAnimationEnd(animation: Animator) {
                            view.visibility = View.GONE
                        }
                    }).start()
        }


        fun fadeInView(view: View?) {
            view!!.animate().alpha(1f)
                    .setListener(object : SimpleAnimatorListener() {
                        override fun onAnimationStart(animation: Animator) {
                            view.visibility = View.VISIBLE
                            view.alpha = 0f
                        }
                    }).start()
        }
    }
}

open class SimpleAnimatorListener : Animator.AnimatorListener {

    override fun onAnimationStart(animation: Animator) {}

    override fun onAnimationEnd(animation: Animator) {}

    override fun onAnimationCancel(animation: Animator) {}

    override fun onAnimationRepeat(animation: Animator) {}
}


