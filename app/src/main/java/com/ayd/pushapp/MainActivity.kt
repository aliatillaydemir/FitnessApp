package com.ayd.pushapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.ayd.pushapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }

    override fun onBackPressed() {
        val navController = findNavController(R.id.fragmentContainerView)

        when(navController.currentDestination?.id){
            R.id.mainFragment -> {
                finish()
            }
            else -> {
                onBackPressedDispatcher.onBackPressed()
            }
        }
    }

}