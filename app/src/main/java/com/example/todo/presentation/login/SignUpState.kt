package com.example.todo.presentation.login

import com.example.todo.domain.model.user.User

data class SignUpState(
    val isLoading: Boolean = false,
    val success: Boolean = false,
    val error: String = ""
)
