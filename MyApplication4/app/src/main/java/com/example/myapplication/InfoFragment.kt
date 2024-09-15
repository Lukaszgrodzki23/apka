package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.myapplication.R.*

class InfoFragment : Fragment() {
    private var index: Int = 0
    private var indexSet = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            index = savedInstanceState.getInt("index")
            indexSet = true
        } else {
            indexSet = false
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layout.fragment_info, container, false)
    }

    override fun onStart() {
        super.onStart()
        val view = view

        if (view != null) {
            if (!indexSet) {
                index = arguments?.getString("index")?.toInt() ?: 0
            }
            val myActivity = activity as? InfoActivity
            val trail = Trail.trails[index]
            val nameView = view.findViewById<TextView>(R.id.nameView)
            val infoView = view.findViewById<TextView>(R.id.infoView)
            nameView.text = trail.getName()
            infoView.text = trail.getInfo()
            myActivity?.findViewById<ImageView>(R.id.top_image)?.setImageResource(trail.getImage())
            val fragmentManager = childFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val stepCounterFragment = StepCounterFragment()
            fragmentTransaction.replace(R.id.dynamic_fragment_container, stepCounterFragment)
            fragmentTransaction.commit()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("index", index)
        super.onSaveInstanceState(outState)
    }
}
