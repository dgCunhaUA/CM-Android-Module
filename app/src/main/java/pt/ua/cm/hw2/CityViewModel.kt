package pt.ua.cm.hw2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pt.ua.cm.hw2.data.City
import pt.ua.cm.hw2.data.CityData

class CityViewModel : ViewModel() {

    private var _currentCity: MutableLiveData<City> = MutableLiveData<City>()
    val currentCity: LiveData<City>
        get() = _currentCity

    private var _cityData: ArrayList<City> = ArrayList()
    val cityData: ArrayList<City>
        get() = _cityData

    init {
        // Initialize the city data.
        _cityData = CityData.getCityData()
        _currentCity.value = _cityData[0]
    }

    fun updateCurrentCity(city: City) {
        _currentCity.value = city
    }
}