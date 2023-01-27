package com.example.quake

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quake.API.Models.Feature
import com.example.quake.databinding.CellBinding

class EarthquakeAdapter(private val features: List<Feature>): RecyclerView.Adapter<EarthquakeAdapter.EarthquakeViewHolder>() {

    //View holder inner class
    inner class EarthquakeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = CellBinding.bind(view)

        fun bind(feature: Feature) {
            binding.cellTitle.text =  feature.properties.title
            println("Binding!!!!")

            /*if (feature.properties.title != null) {
                binding.cellTitle.text = feature.properties.title
            } else {
                binding.cellTitle.text = "Unknown"
            }*/
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EarthquakeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return EarthquakeViewHolder(layoutInflater.inflate(R.layout.cell, parent, false))
    }

    override fun onBindViewHolder(holder: EarthquakeViewHolder, position: Int) {
        val feature: Feature = features[position]
        holder.bind(feature)
    }

    override fun getItemCount(): Int {
        return features.size
    }
}