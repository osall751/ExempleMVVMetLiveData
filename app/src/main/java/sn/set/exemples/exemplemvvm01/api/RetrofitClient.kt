package sn.set.exemples.exemplemvvm01.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {
        private val BASE_URL = "https://arcane-earth-34008.herokuapp.com/"
        //   private val BASE_URL = "http://192.168.1.133:1337/"
        //   private val BASE_URL="https://59df-154-125-222-35.ngrok.io/"

        fun getClient(): RetrofitService {
            val retrofit: Retrofit = Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(RetrofitService::class.java)
        }
    }
}