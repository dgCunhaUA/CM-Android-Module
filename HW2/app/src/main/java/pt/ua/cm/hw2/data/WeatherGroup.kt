package pt.ua.cm.hw2.data

import com.google.gson.annotations.SerializedName


data class WeatherGroup (
    @SerializedName("owner"         ) var owner         : String?         = null,
    @SerializedName("country"       ) var country       : String?         = null,
    @SerializedName("data"          ) var data          : List<Weather> = arrayListOf(),
    @SerializedName("globalIdLocal" ) var globalIdLocal : Int?            = null,
    @SerializedName("dataUpdate"    ) var dataUpdate    : String?         = null
)
