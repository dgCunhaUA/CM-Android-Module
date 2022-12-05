package pt.ua.cm.hw2.data

import pt.ua.cm.hw2.R

object CityData {
    fun getCityData(): ArrayList<City> {
        return arrayListOf(
            City(
                id = 1,
                nameResourceId = R.string.city2,
                globalId = 1010500,
            ),
            City(
                id = 2,
                nameResourceId = R.string.city1,
                globalId = 1182300,
            ),
        )
    }
}