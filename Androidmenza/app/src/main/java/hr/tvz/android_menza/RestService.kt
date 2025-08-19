package hr.tvz.android_menza

import okhttp3.Response
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.UUID

interface RestService {
    @GET("menze/nazivi")
    fun getMenze(): Call<MutableList<Menza>>

    @GET("menze/menza/{naziv}")
    fun getMenzaByNaziv(@Path("naziv") naziv:String): Call<Menza>

    @GET("menze/menza/posjecenost/{naziv}")
    fun getPosjecenost(@Path("naziv") naziv:String): Call<Double>

    @POST("menza-app/login")
    fun login(@Body loginRequest: LoginRequest): Call<UserAuthResponse>
    @POST("menza-app/refresh")
    fun refresh(@Body refreshRequest: RefreshRequest): Call<UserAuthResponse>
    @POST("menza-app/is-token-expired")
    fun isTokenExpired(@Body refreshRequest: RefreshRequest): Call<Int>

    @POST("menza-app/signup")
    fun register(@Body registerRequest: RegisterRequest): Call<StudentUser>

    @POST("menza-app/validate-username")
    fun validateUsername(@Body body: ValidateUsernameBody): Call<UniqueUsernameResponse>

    @GET("menze/guzva/{naziv}")
    fun getGuzva(@Path("naziv") naziv: String): Call<Int>
}