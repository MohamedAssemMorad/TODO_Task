package com.example.todo.presentation.todo

import com.example.todo.domain.model.todo.Todo

data class TodoListState(
    val isLoading: Boolean = false,
    val list: List<Todo>? = emptyList(),
    val error: String = ""
)
