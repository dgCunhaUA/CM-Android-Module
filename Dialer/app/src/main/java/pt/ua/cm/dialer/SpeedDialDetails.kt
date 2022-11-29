package pt.ua.cm.dialer

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import pt.ua.cm.dialer.databinding.FragmentSpeedDialDetailsBinding
import pt.ua.cm.dialer.model.Contact
import timber.log.Timber

class SpeedDialDetails : Fragment() {

    private lateinit var binding: FragmentSpeedDialDetailsBinding
    private val args: SpeedDialDetailsArgs by navArgs()

    private lateinit var currentName: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        Timber.i("onCreateView Called")
        Timber.i(args.toString())

        currentName = args.contact.name

        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_speed_dial_details,container,false)

        binding.saveContact.setOnClickListener{saveContact(binding.contactName.text.toString(), binding.phoneNumber.text.toString())}

        updateContactFields(args)

        return binding.root
    }

    private fun updateContactFields(args: SpeedDialDetailsArgs) {
        if(args.contact.name != "")
            binding.contactName.text = Editable.Factory.getInstance().newEditable(args.contact.name)
        else
            binding.contactName.text = Editable.Factory.getInstance().newEditable("")

        if(args.contact.phoneNumber == "" && args.speedDial.containsKey(currentName))
            binding.phoneNumber.text = Editable.Factory.getInstance().newEditable(args.speedDial[currentName].toString())
        else
            binding.phoneNumber.text = Editable.Factory.getInstance().newEditable(args.contact.phoneNumber)
    }

    private fun saveContact(name :String, phoneNumber :String) {
        val contact = Contact(name, phoneNumber)

        if(args.speedDial.containsKey(currentName))
            args.speedDial.remove(currentName)

        args.speedDial[name] = phoneNumber

        val bundle = bundleOf("Contact" to contact, "SpeedDial" to args.speedDial)
        view?.findNavController()
            ?.navigate(R.id.action_speedDialDetails_to_dialerFragment, bundle)
    }
}