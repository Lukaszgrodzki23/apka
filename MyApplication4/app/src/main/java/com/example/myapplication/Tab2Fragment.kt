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


class Tab2Fragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val cocktailRecycler = inflater.inflate(R.layout.fragment_tab2, container, false) as RecyclerView

        cocktailRecycler.layoutManager = GridLayoutManager(context, 2)
        val hardTrails = Trail.trails.filter { it.getLevel() == "hard" }
        val hardTrailNames = hardTrails.map { it.getName() }.toTypedArray()
        val hardTrailImages = hardTrails.map { it.getImage() }.toTypedArray()

        val adapter = CaptionedImagesAdapter(hardTrailNames, hardTrailImages)
        adapter.setListener(object : CaptionedImagesAdapter.Listener {
            override fun onClick(position: Int) {
                val intent = Intent(requireActivity(), InfoActivity::class.java)
                val updated_position = position + 4
                intent.putExtra("index", updated_position.toString())
                startActivity(intent)
            }
        })

        cocktailRecycler.adapter = adapter

        return cocktailRecycler
    }


}

