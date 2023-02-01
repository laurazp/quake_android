package com.example.quake

import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quake.API.APIService
import com.example.quake.API.Models.Feature
import com.example.quake.databinding.ActivityEarthquakesBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Earthquakes : AppCompatActivity() {

    private lateinit var binding: ActivityEarthquakesBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Set up Binding
        binding = ActivityEarthquakesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Set up NavBar
        val navHostFragment = supportFragmentManager.findFragmentById(com.example.quake.R.id.mainContainer) as NavHostFragment
        navController = navHostFragment.navController
        val bottomNavigationView = findViewById<BottomNavigationView>(com.example.quake.R.id.bottomNavigationView)
        setupWithNavController(bottomNavigationView, navController)
    }
}


