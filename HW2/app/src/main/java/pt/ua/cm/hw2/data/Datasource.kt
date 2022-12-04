package pt.ua.cm.hw2.data

import pt.ua.cm.hw2.R

class Datasource {

    fun loadCities(): List<City> {
        return listOf<City>(
            City(R.string.city1),
            City(R.string.city2),
        )
    }
}