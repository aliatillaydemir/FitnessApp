package com.ayd.pushapp

import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.ayd.pushapp.data.database.AppDatabase
import com.ayd.pushapp.databinding.ActivityMainBinding
import com.ayd.pushapp.feature.mainfragments.SportFragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private lateinit var sharedPreferences: SharedPreferences
    private val DARK_MODE_KEY = "dark_mode_key"

    override fun onCreate(savedInstanceState: Bundle?) {
        //supportActionBar?.hide()

        // Retrieve the last selected theme from SharedPreferences
        sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
        val isDarkMode = sharedPreferences.getBoolean(DARK_MODE_KEY, false)

        // Apply the selected theme
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.toolbar)

        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.fragmentContainerView
        ) as NavHostFragment

        val navController = navHostFragment.navController

        val destinationActions = mapOf(
            R.id.mainFragment to {binding.toolbar.visibility = View.VISIBLE},
            R.id.viewPagerFragment to {binding.toolbar.visibility = View.GONE}
        )

        navController.addOnDestinationChangedListener { _, destination, _ ->
            destinationActions[destination.id]?.invoke()
        }

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete -> {
                // Handle the delete action here
                //deleteTable()
                showDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showDialog() {

        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.custom_dialog_layout)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val yesButton: TextView = dialog.findViewById(R.id.yesButton)
        val noButton: TextView = dialog.findViewById(R.id.noButton)

        yesButton.setOnClickListener {
            deleteTable()
            Toast.makeText(this, "deleted all progress", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        noButton.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun deleteTable() {
        val db = AppDatabase.getInstance(this)
        GlobalScope.launch {
            db.dayDataDao().deleteAllDayData()
        }
    }

    override fun onBackPressed() {
        val navController = findNavController(R.id.fragmentContainerView)

        when(navController.currentDestination?.id){
            R.id.mainFragment -> {
                finish()
            }
            R.id.congratsFragment -> {
                navController.navigate(
                    R.id.mainFragment, null, NavOptions.Builder()
                        .setPopUpTo(R.id.viewPagerFragment, true)
                        .build()
                )
            }
            else -> {
                onBackPressedDispatcher.onBackPressed()
            }
        }
    }

}