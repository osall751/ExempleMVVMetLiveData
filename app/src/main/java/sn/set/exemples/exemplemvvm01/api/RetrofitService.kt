package sn.set.exemples.exemplemvvm01.api

import retrofit2.Response
import retrofit2.http.*
import sn.set.exemples.exemplemvvm01.models.LoginUser
import sn.set.exemples.exemplemvvm01.models.RendezVous
import sn.set.exemples.exemplemvvm01.models.RendezVousItem
import sn.set.exemples.exemplemvvm01.models.StrapiUser

interface RetrofitService {
    @Headers("Content-Type:application/json;charset=UFT-8")
    @POST("auth/local/")
    suspend fun login(@Body user: LoginUser): Response<StrapiUser>

    @GET("/rvs")
    suspend fun getAllRendezVous(): Response<RendezVous>

    @Headers("Content-Type:application/json;charset=UFT-8")
    @POST("rvs")
    suspend fun addNewRendezVous(@Body rv: RendezVousItem): Response<RendezVousItem>

    @Headers("Content-Type:application/json;charset=UFT-8")
    @PUT("rvs/{id}")
    suspend fun upsateRendezVousByID(@Path("id") id: Int,@Body rv: RendezVousItem): Response<RendezVousItem>

    @Headers("Content-Type:application/json;charset=UFT-8")
    @DELETE("rvs/{id}")
    suspend fun deleteRendezVousByID(@Path("id") id: Int): Response<RendezVousItem>
}