package pt.ua.cm.dialer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.core.view.children
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import pt.ua.cm.dialer.databinding.FragmentDialerBinding
import pt.ua.cm.dialer.model.Contact
import timber.log.Timber


class DialerFragment : Fragment() {

    private lateinit var binding: FragmentDialerBinding

    private val args: DialerFragmentArgs by navArgs()
    private var speedDial : HashMap<String, String>
            = HashMap()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        Timber.i("onCreateView Called")
        Timber.i(args.toString())
        Timber.i(speedDial.toString())

        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_dialer,container,false)

        if (args.speedDial != null) {
            speedDial = args.speedDial as HashMap<String, String>
        }

        binding.delButton.setOnClickListener{ deleteNumber() }
        binding.callButton.setOnClickListener{ dialPhoneNumber(binding.phoneNumber.text.toString()) }
        for (child in binding.numbersLayout.children) {
            if (child is Button) {
                child.setOnClickListener{ updatePhoneNumber(child.text as String) }
            }
        }

        for (child in binding.speedDialLayout.children) {
            if (child is Button) {
                child.setOnLongClickListener {
                    val name = child.text.toString()
                    val phoneNumber = binding.phoneNumber.text.toString()
                    val contact = Contact(name, phoneNumber)

                    val bundle = bundleOf("Contact" to contact, "SpeedDial" to speedDial)
                    view?.findNavController()
                        ?.navigate(R.id.action_dialerFragment_to_speedDialDetails, bundle)
                    true
                }
            }
        }

        if (args.contact != null) {
            speedDial[args.contact!!.name] = args.contact!!.phoneNumber
        }

        var i = 0
        val speedDialBtnList = binding.speedDialLayout.children.toList()
        speedDial.forEach { (key, value) ->
            val btn :Button = speedDialBtnList[i] as Button
            btn.text = key
            btn.setOnClickListener{ speedDialContact(value) }
            i++
        }

        return binding.root
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

    private fun speedDialContact(phoneNumber: String) {
        binding.phoneNumber.text = Editable.Factory.getInstance().newEditable(phoneNumber)
    }

}