package hr.tvz.android_menza

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RegisterActivityViewModel(val authRepository: AuthRepository, val application: Application): ViewModel() {
    private var isLoading: MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply {
        value= false
    }
    private var errorMessage: MutableLiveData<HashMap<String, String>> = MutableLiveData()
    private var isUnique: MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply {
        value = false
    }
    private var user: MutableLiveData<StudentUser> = MutableLiveData()

    fun getIsLoading(): LiveData<Boolean> = isLoading
    fun getErrorMessage(): LiveData<HashMap<String,String>> = errorMessage
    fun getIsUnique(): LiveData<Boolean> = isUnique
    fun getUser(): LiveData<StudentUser> = user

    fun validateUsername(body: ValidateUsernameBody){
        viewModelScope.launch {
            authRepository.validateUsername(body).collect {
                when(it){
                    is RequestStatus.Waiting -> {
                        isLoading.value = true

                    }
                    is RequestStatus.Success ->{
                        isLoading.value = false
                        isUnique.value = it.data.isUnique
                    }
                    is RequestStatus.Error ->{
                        isLoading.value = false
                        errorMessage.value = it.message
                    }
                }
            }

        }

    }
}
