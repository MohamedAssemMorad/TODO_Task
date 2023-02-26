package com.example.todo.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.domain.model.user.User
import com.example.todo.domain.usecase.loginusecase.CreateUserUseCase
import com.example.todo.domain.usecase.loginusecase.GetUserUseCase
import com.example.todo.utils.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val createUserUseCase: CreateUserUseCase,
    private val getUserUseCase: GetUserUseCase
) :
    ViewModel() {

    private val signUpValue = MutableStateFlow((SignUpState()))
    var _signUpValue: StateFlow<SignUpState> = signUpValue

    private val loginValue = MutableStateFlow(LoginState())
    var _loginValue: StateFlow<LoginState> = loginValue


    fun createUser(user: User) = viewModelScope.launch {
        createUserUseCase(user).collect {
            when (it) {
                is ResponseState.Success -> {
                    signUpValue.value = SignUpState(success = true)
                }
                is ResponseState.Loading -> {
                    signUpValue.value = SignUpState(isLoading = true)
                }
                is ResponseState.Error -> {
                    signUpValue.value =
                        SignUpState(error = it.message ?: "unExpected Error Occurred")
                }
            }

        }
    }

    fun getUser(userName: String) = viewModelScope.launch {
        getUserUseCase(userName).collect {
            when (it) {
                is ResponseState.Success -> {
                    loginValue.value = LoginState(user = it.data)
                }
                is ResponseState.Loading -> {
                    loginValue.value = LoginState(isLoading = true)
                }
                is ResponseState.Error -> {
                    loginValue.value =
                        LoginState(error = it.message ?: "unExpected Error Occurred")
                }
            }

        }
    }

}