package com.example.quake.NavigationBar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.quake.R
import kotlinx.android.synthetic.main.fragment_map.view.*

class MapFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_map, container, false)

        //TODO: Añadir aquí código para mostrar en el fragment
        //val viewPager = activity?.findViewById<ViewPager2>(R.id.nav_graph)

        /*view.button.setOnClickListener {
            println("Click en Return")
            //viewPager?.currentItem = 0
        }*/

        return view
    }
}