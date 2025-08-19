/*
package hr.tvz.android_menza

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.security.InvalidParameterException

class RegisterActivityViewModelFactory(val authRepository: AuthRepository, val application: Application): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(RegisterActivityViewModel::class.java)){
            return RegisterActivityViewModel(authRepository, application) as T
        }
        throw InvalidParameterException("Unable to construct RegisterActivityViewModel")

    }
}*/
