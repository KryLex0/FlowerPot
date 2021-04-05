package com.example.flowerpot

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //implement_bdd()
        get_bdd()


        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_options))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)







    }



    private fun get_bdd(){
        val temperature = 28.30
        val humidite = 90.00
        val luminosite = 190

        val temp = AppDatabase.get(application).donneeDao().getAllTemperature()
        Log.d("test123", temp.toString())
    }

    private fun implement_bdd(){
        val temperature = arrayOf(8.00, 10.5, 12.70, 13.20, 15.80, 16.00)
        val humidite = arrayOf(10.00, 20.00, 40.00, 35.00, 60.00, 50.00)
        val luminosite = arrayOf(100, 150, 125, 139, 170, 159)

        var i = 0
        while(i < 6){
            AppDatabase.get(application).donneeDao().insertDonnee(Donnee(temperature[i], humidite[i], luminosite[i]))
            i+=1
        }

        Log.d("test123", AppDatabase.get(application).donneeDao().getAll().toString())
    }
}