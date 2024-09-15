package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CaptionedImagesAdapter(
    private val captions: Array<String>,
    private val imageIds: Array<Int>
) : RecyclerView.Adapter<CaptionedImagesAdapter.ViewHolder>() {

    private var listener: Listener? = null

    interface Listener {
        fun onClick(position: Int)
    }

    fun setListener(listener: Listener) {
        this.listener = listener
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView = view.findViewById<ImageView>(R.id.info_image)
        val textView = view.findViewById<TextView>(R.id.info_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_captioned_image, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = captions[position]
        holder.imageView.setImageResource(imageIds[position])
        holder.imageView.setOnClickListener {
            if (listener != null) {
                listener?.onClick(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return captions.size
    }
}