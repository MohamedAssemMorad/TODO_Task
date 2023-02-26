package com.example.todo.presentation.todo

data class TodoState(
    val isLoading: Boolean = false,
    val success: Boolean = false,
    val error: String = ""
)
