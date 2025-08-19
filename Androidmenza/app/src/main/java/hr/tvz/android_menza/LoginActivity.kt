package hr.tvz.android_menza

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() , View.OnKeyListener, View.OnClickListener, View.OnFocusChangeListener {
    private lateinit var client : RestService
    lateinit var userAuthResponse: UserAuthResponse
    private val usernameText: TextInputEditText
    get() = findViewById(R.id.lUsernameText)
    private val passwordText: TextInputEditText
    get() = findViewById(R.id.lPasswordText)
    private val usernameTIL: TextInputLayout
    get() = findViewById(R.id.lUsernameTIL)
    private val passwordTIL: TextInputLayout
    get() = findViewById(R.id.lPasswordTIL)
    private val loginButton: Button
    get() = findViewById(R.id.loginButton)
    private val loginLayoutMessage: EditText
    get() = findViewById(R.id.loginLayoutMessage)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        usernameText.onFocusChangeListener = this
        passwordText.onFocusChangeListener = this
        client = APIService().getService(RestService::class.java)
        val intent = Intent(this, MenzeActivity::class.java)
        loginButton.setOnClickListener {
            if(validateUsername() && validatePassword()) run {
                val loginRequest: LoginRequest = LoginRequest(
                    usernameText.text.toString(),
                    passwordText.text.toString()
                )
                client.login(loginRequest)
                    .enqueue(object : Callback<UserAuthResponse> {
                        override fun onResponse(
                            call: Call<UserAuthResponse>,
                            response: Response<UserAuthResponse>
                        ) {
                            if (response.isSuccessful) {
                                Token.accessToken = response.body()!!.accessToken
                                Token.refreshToken = response.body()!!.refreshToken
                                startActivity(intent)
                            } else {
                                runOnUiThread {
                                    loginLayoutMessage.apply {
                                        setText("Krivo korisnicko ime ili lozinka")
                                    }
                                }
                            }
                        }

                        override fun onFailure(call: Call<UserAuthResponse>, t: Throwable) {
                            println("Error: ${t.message}")
                        }

                    })
            }
        }
    }

    private fun validateUsername(): Boolean{
        var errorMessage: String? = null
        val value: String = usernameText.text.toString()
        if(value.isEmpty()){
            errorMessage = "Potrebno je upisati korisnicko ime"
        }
        if(errorMessage != null){
            usernameTIL.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }
        return errorMessage == null
    }

    private fun validatePassword(): Boolean{
        var errorMessage: String? = null
        val pass: String = passwordText.text.toString()

        if(pass.isEmpty()){
            errorMessage = "Potrebno je upisati lozinku"
        }
        if(errorMessage != null){
            passwordTIL.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }
        return errorMessage == null
    }

    override fun onKey(view: View?, event: Int, keyEvent: KeyEvent): Boolean {
        TODO("Not yet implemented")
    }

    override fun onClick(view: View?) {
        TODO("Not yet implemented")
    }

    override fun onFocusChange(view: View?, hasFocus: Boolean) {
        if(view != null){
            when(view.id){
                R.id.lUsernameText -> {
                    if(hasFocus){
                        if(usernameTIL.isErrorEnabled){
                           usernameTIL.isErrorEnabled = false;
                        }
                    }
                    else{
                        validateUsername()
                    }
                }
                R.id.lPasswordText -> {
                    if(hasFocus){
                        if(passwordTIL.isErrorEnabled){
                           passwordTIL.isErrorEnabled = false;
                        }
                    }
                    else{
                        validatePassword()
                    }
                }
            }
        }
    }
}