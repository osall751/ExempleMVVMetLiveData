package sn.set.exemples.exemplemvvm01.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import sn.set.exemples.exemplemvvm01.models.LoginUser
import sn.set.exemples.exemplemvvm01.models.StrapiUser
import sn.set.exemples.testretrofit_2.models.RendezVous

interface RetrofitService {
    @Headers("Content-Type:application/json;charset=UFT-8")
    @POST("auth/local/")
    suspend fun login(
        @Body user: LoginUser
    ): Response<StrapiUser>

    @GET("/rvs")
    suspend fun getAllRendezVous(): Response<RendezVous>
}