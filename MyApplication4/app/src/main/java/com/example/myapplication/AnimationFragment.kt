package com.example.myapplication

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import androidx.fragment.app.Fragment


class AnimationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_animation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startAnimation()
    }
    private fun startAnimation() {
        val icon = view?.findViewById<ImageView>(R.id.logo);
        val rotateAnimator = ObjectAnimator.ofFloat(icon, "rotation", 0f, 360f)
        rotateAnimator.duration = 2000
        rotateAnimator.interpolator = DecelerateInterpolator()

        rotateAnimator.start()
    }

    companion object {
        fun newInstance(): AnimationFragment {
            return AnimationFragment()
        }
    }
}