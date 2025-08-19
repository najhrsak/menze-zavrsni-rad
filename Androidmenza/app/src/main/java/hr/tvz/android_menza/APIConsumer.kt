package hr.tvz.android_menza

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface APIConsumer {
    @POST("validate-username")
    fun validateUsername(@Body body: ValidateUsernameBody): Response<UniqueUsernameResponse>
}