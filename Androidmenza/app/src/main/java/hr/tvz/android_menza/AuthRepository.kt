package hr.tvz.android_menza

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuthRepository(val consumer: APIConsumer) {
    fun validateUsername(body: ValidateUsernameBody): Flow<RequestStatus<UniqueUsernameResponse>> = flow{
        emit(RequestStatus.Waiting)
        val response = consumer.validateUsername(body)
        if(response.isSuccessful){
            emit(RequestStatus.Success(response.body()!!))
        }else{
            emit(RequestStatus.Error(SimplifiedMessage.get(response.errorBody()!!.byteStream().reader().readText())))
        }
    }
}