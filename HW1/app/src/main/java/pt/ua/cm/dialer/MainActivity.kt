package pt.ua.cm.dialer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import pt.ua.cm.dialer.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.i("onCreate Called")

        setContentView(R.layout.activity_main)

        val navController = this.findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this,navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return navController.navigateUp()
    }


    /*
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.i("onCreate Called")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.delButton.setOnClickListener{ deleteNumber() }
        binding.callButton.setOnClickListener{ dialPhoneNumber(binding.phoneNumber.text.toString()) }
        for (child in binding.numbersLayout.children) {
            if (child is Button) {
                child.setOnClickListener{ updatePhoneNumber(child.text as String) }
            }
        }
    }

    private fun updatePhoneNumber(number: String) {
        Timber.i("Pressed: %s", number)
        binding.phoneNumber.text = Editable.Factory.getInstance().newEditable(binding.phoneNumber.text.toString() + number)
    }

    private fun deleteNumber() {
        Timber.i("Deleting number")
        binding.phoneNumber.text = Editable.Factory.getInstance().newEditable(binding.phoneNumber.text.toString().dropLast(1))
    }

    private fun dialPhoneNumber(phoneNumber: String) {
        Timber.i("Calling: $phoneNumber")

        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        startActivity(intent)
    }


     */
}