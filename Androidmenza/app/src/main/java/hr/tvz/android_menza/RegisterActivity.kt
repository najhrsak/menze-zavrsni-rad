package hr.tvz.android_menza

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isNotEmpty
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import hr.tvz.android_menza.databinding.ActivityRegisterBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() , View.OnKeyListener, View.OnClickListener, View.OnFocusChangeListener {
    private lateinit var client : RestService
    private val usernameText: TextInputEditText
        get() = findViewById(R.id.usernameText)
    private val passwordText: TextInputEditText
    get() = findViewById(R.id.passwordText)
    private val confPasswordText: TextInputEditText
    get() = findViewById(R.id.confPasswordText)
    private val usernameTIL: TextInputLayout
    get() = findViewById(R.id.usernameTIL)
    private val passwordTIL: TextInputLayout
    get() = findViewById(R.id.passwordTIL)
    private val confPasswordTIL: TextInputLayout
    get() = findViewById(R.id.confPasswordTIL)
    private val registerLayoutMessage: EditText
    get() = findViewById(R.id.registerLayoutMessage)
    private val registerButton: Button
    get() = findViewById(R.id.registerButton)
/*    private val progressBar: RelativeLayout
    get() = findViewById(R.id.progressBar)*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        usernameText.onFocusChangeListener = this
        passwordText.onFocusChangeListener = this
        confPasswordText.onFocusChangeListener = this
        client = APIService().getService(RestService::class.java)
        val intent = Intent(this, MainActivity::class.java)
        registerButton.setOnClickListener {
            if(validateUsername() && validatePassword()) run {
                val registerRequest: RegisterRequest = RegisterRequest(
                    usernameText.text.toString(),
                    passwordText.text.toString()
                )
                client.register(registerRequest)
                    .enqueue(object : Callback<StudentUser> {
                        override fun onResponse(
                            call: Call<StudentUser>,
                            response: Response<StudentUser>
                        ) {
                            if (response.isSuccessful) {
                                startActivity(intent)
                            } else {
                                runOnUiThread {
                                    registerLayoutMessage.apply {
                                        setVisible(true)
                                        setText("Registration failed!")
                                    }
                                }
                            }
                        }

                        override fun onFailure(call: Call<StudentUser>, t: Throwable) {
                            println("Error: ${t.message}")
                        }

                    })
            }
        }
    }

    private fun validateUsername(shouldUpdateView: Boolean = true): Boolean{
        var errorMessage: String? = null
        val value: String = usernameText.text.toString()
        if(value.isEmpty()){
            errorMessage = "Potrebno je upisati korisnicko ime"
        }
        else{
            client.validateUsername(ValidateUsernameBody(usernameText.text!!.toString()))
                .enqueue(object : Callback<UniqueUsernameResponse> {
                    override fun onResponse(
                        call: Call<UniqueUsernameResponse>,
                        response: Response<UniqueUsernameResponse>
                    ) {
                        if (response.isSuccessful) {
                            runOnUiThread {
                                usernameTIL.apply {
                                    startIconDrawable = null
                                    isErrorEnabled = true
                                    error = "Korisnicko ime se vec koristi"
                                }
                            }
                        } else {
                            runOnUiThread {
                                usernameTIL.apply {
                                    setStartIconDrawable(R.drawable.baseline_check_circle_24)
                                    setStartIconTintList(ColorStateList.valueOf(Color.GREEN))
                                }
                            }
                        }
                    }

                    override fun onFailure(call: Call<UniqueUsernameResponse>, t: Throwable) {
                        println("Error: ${t.message}")
                    }

                })

        }
        if(errorMessage != null && shouldUpdateView){
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
        val confPass: String = confPasswordText.text.toString()

        if(pass.isEmpty()){
            errorMessage = "Potrebno je upisati lozinku"
        }
        if(errorMessage != null){
            passwordTIL.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }
        if(confPass.isEmpty())
        {
            errorMessage = "Potrebno je upisati lozinku"
        }
        if(errorMessage != null){
            confPasswordTIL.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }
        if(pass != confPass)
            errorMessage = "Lozinke se moraju poklapati"
        if(errorMessage != null){
            passwordTIL.apply {
                isErrorEnabled = true
                error = errorMessage
            }
            confPasswordTIL.apply {
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
                R.id.usernameText -> {
                    if(hasFocus){
                        if(usernameTIL.isErrorEnabled){
                            usernameTIL.isErrorEnabled = false;
                        }
                    }
                    else{
                        validateUsername(shouldUpdateView = true)
                    }
                }
                R.id.passwordText -> {
                    if(hasFocus){
                        if(passwordTIL.isErrorEnabled){
                            passwordTIL.isErrorEnabled = false;
                        }
                    }
                    else{
                        if(validatePassword() && confPasswordTIL.isNotEmpty()){
                            if(confPasswordTIL.isErrorEnabled){
                                confPasswordTIL.isErrorEnabled = false
                            }
                            confPasswordTIL.apply {
                                setStartIconDrawable(R.drawable.baseline_check_circle_24)
                                setStartIconTintList(ColorStateList.valueOf(Color.GREEN))
                            }
                        }
                    }
                }
                R.id.confPasswordText -> {
                    if(hasFocus){
                        if(confPasswordTIL.isErrorEnabled){
                            confPasswordTIL.isErrorEnabled = false;
                        }
                    }
                    else{
                        if(validatePassword()){
                            if(passwordTIL.isErrorEnabled){
                                passwordTIL.isErrorEnabled = false
                            }
                            confPasswordTIL.apply {
                                setStartIconDrawable(R.drawable.baseline_check_circle_24)
                                setStartIconTintList(ColorStateList.valueOf(Color.GREEN))
                            }                        }
                    }
                }
            }
        }
    }

}