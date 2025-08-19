package hr.tvz.android_menza

import com.google.gson.annotations.SerializedName

class UserAuthResponse(@SerializedName("access_token") var accessToken: String,
                       @SerializedName("refresh_token") var refreshToken: String) {
    override fun toString(): String {
        return "JwtToken(token='$accessToken', refreshToken='$refreshToken')"
    }
}