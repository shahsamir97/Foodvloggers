package com.samir.foodvloggers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.parse.ParseInstallation
import com.samir.foodvloggers.databinding.ActivityContentMainBinding

class ContentMain : AppCompatActivity() {

    private lateinit var binding: ActivityContentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_content_main)

        val navController = findNavController(R.id.fragment2)
        findViewById<BottomNavigationView>(R.id.bottom_navigation)
            .setupWithNavController(navController)


    }
}