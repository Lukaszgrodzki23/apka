package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class Tab1Fragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val cocktailRecycler = inflater.inflate(R.layout.fragment_tab1, container, false) as RecyclerView

        cocktailRecycler.layoutManager = GridLayoutManager(context, 2)
        val easyTrails = Trail.trails.filter { it.getLevel() == "easy" }
        val easyTrailNames = easyTrails.map { it.getName() }.toTypedArray()
        val easyTrailImages = easyTrails.map { it.getImage() }.toTypedArray()

        val adapter = CaptionedImagesAdapter(easyTrailNames, easyTrailImages)
        adapter.setListener(object : CaptionedImagesAdapter.Listener {
            override fun onClick(position: Int) {
                val intent = Intent(requireActivity(), InfoActivity::class.java)
                intent.putExtra("index", position.toString())
                startActivity(intent)
            }
        })

        cocktailRecycler.adapter = adapter

        return cocktailRecycler
    }


}

