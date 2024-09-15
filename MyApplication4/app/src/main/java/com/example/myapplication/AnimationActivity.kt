package com.example.myapplication
import android.content.Intent
import android.os.Handler
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment


class AnimationActivity : SingleFragmentActivity() {
    private var handler = Handler()
    override fun createFragment(): Fragment {
        handler.postDelayed(mLaunchTask, 4000)
        return AnimationFragment.newInstance()
    }

    private val mLaunchTask = Runnable {
        val intent = Intent(
            applicationContext,
            MainActivity::class.java
        )
        startActivity(intent)
    }
}