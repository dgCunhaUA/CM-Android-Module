package pt.ua.cm.hw2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import pt.ua.cm.hw2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("MainActivity", "OnCreate called")

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}