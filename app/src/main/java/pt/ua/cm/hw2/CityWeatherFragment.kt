package pt.ua.cm.hw2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import pt.ua.cm.hw2.databinding.FragmentCityWeatherBinding

class CityWeatherFragment : Fragment() {

    private val cityViewModel: CityViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentCityWeatherBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCityWeatherBinding.bind(view)

        // Attach an observer on the currentSport to update the UI automatically when the data
        // changes.
        cityViewModel.currentCity.observe(this.viewLifecycleOwner) {
            binding.cityNameDetail.text = getString(it.nameResourceId)
        }
    }

}