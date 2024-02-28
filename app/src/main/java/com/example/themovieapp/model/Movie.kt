package com.example.themovieapp.model

import com.google.gson.annotations.SerializedName
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

data class Movie (
    @SerializedName("Search"       ) var Search       : ArrayList<Search> = arrayListOf(),
    @SerializedName("totalResults" ) var totalResults : String?           = null,
    @SerializedName("Response"     ) var Response     : String?           = null
)

data class Search (
    @SerializedName("Title"  ) var Title  : String? = null,
    @SerializedName("Year"   ) var Year   : String? = null,
    @SerializedName("imdbID" ) var imdbID : String? = null,
    @SerializedName("Type"   ) var Type   : String? = null,
    @SerializedName("Poster" ) var Poster : String? = null
)

const val BASE_URL = "https://www.omdbapi.com"

interface MovieApi{
    @GET("?s=home&apikey=49807df&")
    suspend fun getMovies(): Movie

    companion object {

        var moviesService: MovieApi? = null

        fun getInstance(): MovieApi {
            if(moviesService === null){
                moviesService = Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(MovieApi::class.java)
            }
            return moviesService!!
        }
    }
}