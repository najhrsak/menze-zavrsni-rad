package hr.tvz.android_menza

import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TokenRefresher() {
    fun checkAndRefreshToken(client : RestService){
        var resp = client.isTokenExpired(RefreshRequest(Token.accessToken.toString()))
        resp.enqueue(object: Callback<Int>{
            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                if(response.body() == 200){
                    return
                }
                else if(response.body()== 406){
                    refreshToken(client)
                }
            }

            override fun onFailure(call: Call<Int>, t: Throwable) {
                println(t.message);           }
        })
    }

    fun refreshToken(client : RestService){
        var resp = client.refresh(RefreshRequest(Token.refreshToken.toString()))
        resp.enqueue(object: Callback<UserAuthResponse>{
            override fun onResponse(
                call: Call<UserAuthResponse>,
                response: Response<UserAuthResponse>
            ) {
                if(response.isSuccessful) {
                    Token.accessToken = response.body()!!.accessToken
                    return
                }
                else{
                    logout()
                }
            }
            override fun onFailure(call: Call<UserAuthResponse>, t: Throwable) {
                println(t.message);
            }
        })
    }

    fun logout(){
        //TODO: logout
    }
}