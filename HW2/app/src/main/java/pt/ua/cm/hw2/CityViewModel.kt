package pt.ua.cm.hw2

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import pt.ua.cm.hw2.data.City
import pt.ua.cm.hw2.data.CityData
import pt.ua.cm.hw2.data.Weather
import pt.ua.cm.hw2.data.WeatherGroup
import pt.ua.cm.hw2.network.WeatherApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CityViewModel : ViewModel() {

    private var _currentCity: MutableLiveData<City> = MutableLiveData<City>()
    val currentCity: LiveData<City>
        get() = _currentCity

    private var _cityData: ArrayList<City> = ArrayList()
    val cityData: ArrayList<City>
        get() = _cityData

    private val _response = MutableLiveData<WeatherGroup>()
    val response: LiveData<WeatherGroup>
        get() = _response

    init {
        _cityData = CityData.getCityData()
        _currentCity.value = _cityData[0]
    }

    fun updateCurrentCity(city: City) {
        _currentCity.value = city
        getCityWeather(city.globalId)
    }

    /**
     * Sets the value of the status LiveData to the Weather API status.
     */
    private fun getCityWeather(globalId: Long) {
        WeatherApi.retrofitService.getWeather(globalId).enqueue(
            object: Callback<WeatherGroup> {
                override fun onResponse(call: Call<WeatherGroup>, response: Response<WeatherGroup>) {
                    _response.value = response.body()
                    Log.i(null, _response.toString())
                }

                override fun onFailure(call: Call<WeatherGroup>, t: Throwable) {
                    Log.i(null, t.toString())
                }
            })
    }
}