package pt.ua.cm.hw2.data

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json


data class Weather(
    @SerializedName("precipitaProb"  ) var precipitaProb  : String? = null,
    @SerializedName("tMin"           ) var tMin           : String? = null,
    @SerializedName("tMax"           ) var tMax           : String? = null,
    @SerializedName("predWindDir"    ) var predWindDir    : String? = null,
    @SerializedName("idWeatherType"  ) var idWeatherType  : Int?    = null,
    @SerializedName("classWindSpeed" ) var classWindSpeed : Int?    = null,
    @SerializedName("longitude"      ) var longitude      : String? = null,
    @SerializedName("forecastDate"   ) var forecastDate   : String? = null,
    @SerializedName("latitude"       ) var latitude       : String? = null
)