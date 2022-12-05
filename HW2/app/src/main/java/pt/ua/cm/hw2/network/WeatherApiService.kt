package pt.ua.cm.hw2.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


private const val BASE_URL = "https://api.ipma.pt/"
    //"https://android-kotlin-fun-mars-server.appspot.com"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface WeatherApiService {
    @GET("open-data/forecast/meteorology/cities/daily/{globalId}.json")
    fun getWeather(@Path("globalId") globalId: Long):
            Call<String>
}

object WeatherApi {
    val retrofitService : WeatherApiService by lazy {
        retrofit.create(WeatherApiService::class.java) }
}
