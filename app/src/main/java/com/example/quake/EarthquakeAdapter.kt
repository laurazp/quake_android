package com.example.quake

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quake.API.Models.Feature
import com.example.quake.databinding.CellBinding

class EarthquakeAdapter(private val features: List<Feature>, listener: RecyclerViewOnClickListener): RecyclerView.Adapter<EarthquakeAdapter.EarthquakeViewHolder>() {

    private var listener: RecyclerViewOnClickListener = listener

    //ViewHolder inner class
    inner class EarthquakeViewHolder(view: View, listener: RecyclerViewOnClickListener) : RecyclerView.ViewHolder(view) {

        private val binding = CellBinding.bind(view)
        private var listener: RecyclerViewOnClickListener = listener

        //Binding method
        fun bind(feature: Feature) {
            if (feature.properties.title != null) {
                if (feature.properties.place != null) {
                    binding.cellTitle.text = feature.properties.title
                } else {
                    binding.cellTitle.text = feature.properties.title + "Unknown"
                }
            } else {
                binding.cellTitle.text = "Unknown"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EarthquakeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return EarthquakeViewHolder(layoutInflater.inflate(R.layout.cell, parent, false), listener)
    }

    override fun onBindViewHolder(holder: EarthquakeViewHolder, position: Int) {
        val feature: Feature = features[position]
        holder.bind(feature)
        holder.itemView.setOnClickListener {
            this.listener.recyclerviewClick(position)
        }
    }

    override fun getItemCount(): Int {
        return features.size
    }
}