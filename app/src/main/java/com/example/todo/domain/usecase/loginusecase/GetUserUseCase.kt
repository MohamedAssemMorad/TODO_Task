package com.example.todo.domain.usecase.loginusecase

import com.example.todo.domain.model.user.User
import com.example.todo.domain.repository.UserRepository
import com.example.todo.utils.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val userRepository: UserRepository) {

    operator fun invoke(userName: String): Flow<ResponseState<User>> = flow {
        try {
            emit(ResponseState.Loading<User>())
            val user = userRepository.getUserDB(userName)
            emit(ResponseState.Success<User>(user))
        } catch (e: HttpException) {
            emit(ResponseState.Error<User>(e.localizedMessage ?: "Unexpected error"))
        } catch (e: IOException) {
            emit(ResponseState.Error<User>("error Occurred"))
        }
    }
}