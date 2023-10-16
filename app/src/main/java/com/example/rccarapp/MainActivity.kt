package com.example.rccarapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.rccarapp.databinding.ActivityMainBinding
import java.io.IOException
import java.net.Socket
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private fun client(number: Int){
        Executors.newSingleThreadExecutor().execute {
            try {
                val client = Socket("192.168.18.139", 4080)
                client.outputStream.write(number.toInt())
                client.close()
            } catch (e: IOException) {
                e.printStackTrace();
            }
        }
    }

    private fun disconnect(){

        if( findNavController(R.id.nav_host_fragment_content_main).currentDestination?.id == R.id.SecondFragment) {
            val myToast = Toast.makeText(baseContext, getString(R.string.disconnect_button_toast_text), Toast.LENGTH_SHORT)
            myToast.show()
            findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
        else
        {
            val myToast = Toast.makeText(baseContext, getString(R.string.cannot_disconnect_button_toast_text), Toast.LENGTH_SHORT)
            myToast.show()
        }
    }


    private fun speed50(){

        if( findNavController(R.id.nav_host_fragment_content_main).currentDestination?.id == R.id.SecondFragment) {
            val myToast = Toast.makeText(baseContext, getString(R.string.speed50_button_toast_text), Toast.LENGTH_SHORT)
            myToast.show()

            client(66)
        }
        else
        {
            val myToast = Toast.makeText(baseContext, getString(R.string.cannot_disconnect_button_toast_text), Toast.LENGTH_SHORT)
            myToast.show()
        }
    }

    private fun speed75(){

        if( findNavController(R.id.nav_host_fragment_content_main).currentDestination?.id == R.id.SecondFragment) {
            val myToast = Toast.makeText(baseContext, getString(R.string.speed75_button_toast_text), Toast.LENGTH_SHORT)
            myToast.show()

            client(67)
        }
        else
        {
            val myToast = Toast.makeText(baseContext, getString(R.string.cannot_disconnect_button_toast_text), Toast.LENGTH_SHORT)
            myToast.show()
        }
    }

    private fun speed100(){

        if( findNavController(R.id.nav_host_fragment_content_main).currentDestination?.id == R.id.SecondFragment) {
            val myToast = Toast.makeText(baseContext, getString(R.string.speed100_button_toast_text), Toast.LENGTH_SHORT)
            myToast.show()

            client(68)
        }
        else
        {
            val myToast = Toast.makeText(baseContext, getString(R.string.cannot_disconnect_button_toast_text), Toast.LENGTH_SHORT)
            myToast.show()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            //R.id.action_settings -> true
            R.id.settings_disconnect -> {
                disconnect()
                true
            }
            R.id.settings_speed_50 -> {
                speed50()
                true
            }
            R.id.settings_speed_75 -> {
                speed75()
                true
            }
            R.id.settings_speed_100 -> {
                speed100()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}