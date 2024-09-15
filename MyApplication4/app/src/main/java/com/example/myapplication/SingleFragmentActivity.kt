package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


abstract class SingleFragmentActivity : AppCompatActivity() {
    protected abstract fun createFragment(): Fragment?
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.animation_activity_fragment)
        val fm = supportFragmentManager
        var fragment = fm.findFragmentById(R.id.animation_fragment_container)
        if (fragment == null) {
            fragment = createFragment()
            fm.beginTransaction()
                .add(R.id.animation_fragment_container, fragment!!)
                .commit()
        }
    }
}