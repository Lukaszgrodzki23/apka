package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.example.myapplication.R.*
import com.google.android.material.floatingactionbutton.FloatingActionButton

class InfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById<View>(R.id.toolbar_detail) as androidx.appcompat.widget.Toolbar
        setSupportActionBar(toolbar)
        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            Toast.makeText(this, "FAB Clicked!", Toast.LENGTH_SHORT).show()
        }
        val index = intent?.extras?.getString("index")
        val bundle = Bundle()
        bundle.putString("index", index)
        (supportFragmentManager.findFragmentById(id.info_fragment) as? InfoFragment)?.setArguments(
            bundle
        )
    }
}