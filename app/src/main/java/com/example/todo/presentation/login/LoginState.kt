package com.example.todo.presentation.login

import com.example.todo.domain.model.user.User

data class LoginState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val error: String = ""
)
